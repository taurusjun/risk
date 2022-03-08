package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.*
import org.apache.ibatis.annotations.*

@Mapper
interface RuleDAO {
    @Select("select * from rule")
    fun getAllRules():List<Rule>

    @Select("select * from rule")
    @Results(value = [
        Result(property = "ruleGroups", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleGroupByRuleUuid")
        )
    ])
    fun getAllRuleLogics():List<RuleLogic>

    @Select("select version from rule where uuid=#{uuid}")
    fun getRuleVersionByUuid(uuid:String):Int

    @Select("select * from rule where uuid=#{uuid}")
    @Results(value = [
        Result(property = "ruleGroups", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleGroupByRuleUuid")
        ),
    ])
    fun getRuleLogicByUuid(uuid:String):RuleLogic

    /**
     * Full rule model, contains logic and action
     */
    @Select("select * from rule where uuid=#{uuid}")
    @Results(value = [
        Result(property = "ruleGroups", column = "uuid",
               many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleGroupByRuleUuid")
        ),
        Result(property = "ruleActions", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleActionListByRuleUUID")
        )
    ])
    fun getCompleteRuleByUuid(uuid:String):RuleComplete

    @Insert("INSERT INTO rule ( uuid, name, code, categoryId, description, status, version) VALUES ( #{uuid}, #{name}, #{code}, #{categoryId}, #{description}, #{status}, #{version})")
    fun insertRuleLogic(rule: RuleLogic)

    @Update("update rule set name = #{name}, code = #{code}, categoryId = #{categoryId}, description = #{description}, status = #{status}, version = #{version} where uuid = #{uuid}")
    fun updateRuleLogic(rule: RuleLogic)

    @Select("select * from rule_group where uuid=#{uuid}")
    fun getRuleGroupByUuid(uuid:String):RuleGroup

    @Insert("INSERT INTO rule_group ( uuid, rule_uuid, logic_code) VALUES ( #{uuid}, #{ruleUuid}, #{logicCode})")
    fun insertRuleGroup(ruleGroup: RuleGroup)

    @Update("update rule_group set rule_uuid = #{ruleUuid}, logic_code = #{logicCode} where uuid = #{uuid}")
    fun updateRuleGroup(ruleGroup: RuleGroup)

    @Delete("delete from rule_group where uuid=#{uuid}")
    fun deleteRuleGroup(uuid:String):Boolean

    @Delete("<script>"  +
            "delete from rule_group where rule_uuid=#{ruleUuid} and uuid not in " +
                "<foreach collection='set' item='item' open='(' separator=',' close=')'> " +
                    "#{item}" +
                "</foreach>" +
            "</script>"
    )
    fun deleteMutiRuleGroup(@Param("ruleUuid") ruleUuid:String, @Param("set") ruleUuidSet:Set<String>):Boolean

    @Select("select * from rule_group where rule_uuid=#{ruleUuid}")
    @Results(value = [
        Result(property = "ruleConditions", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleConditionByRuleGroupUuid")
        )
    ])
    fun getRuleGroupByRuleUuid(ruleUuid:String):List<RuleGroup>?

    @Select("select * from rule_condition where rule_group_uuid=#{ruleGroupUuid}")
    @Results(value = [
        Result(property = "ruleConditionLeftElement", column = "left_id",
            one = One(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleConditionElementById")
        ),
        Result(property = "ruleConditionOperator", column = "operator_code",
        one = One(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleConditionOperatorByCode")
        )
    ])
    fun getRuleConditionByRuleGroupUuid(ruleGroupUuid:String):RuleCondition?

    @Select("select * from rule_condition_element")
    fun getAllRuleConditionElements():List<RuleConditionElement>

    @Select("select * from rule_condition_element where id=#{id}")
    fun getRuleConditionElementById(id:String):RuleConditionElement?

    @Select("select * from rule_condition_operator")
    fun getAllRuleConditionOperators():List<RuleConditionOperator>

    @Select("select * from rule_condition_operator where code=#{code}")
    fun getRuleConditionOperatorByCode(uuid:String):RuleConditionOperator?

    @Insert("INSERT INTO rule_condition ( uuid, rule_group_uuid, logic_code, left_id, operator_code, right_value) VALUES ( #{uuid}, #{ruleGroupUuid}, #{logicCode}, #{leftId}, #{operatorCode}, #{rightValue})")
    fun insertRuleCondition(ruleCondition: RuleCondition):Boolean

    @Update("update rule_condition set rule_group_uuid = #{ruleGroupUuid}, logic_code = #{logicCode}, left_id = #{leftId}, operator_code = #{operatorCode}, right_value = #{rightValue} where uuid = #{uuid}")
    fun updateRuleCondition(ruleCondition: RuleCondition):Boolean

    @Delete("<script>"  +
            "delete from rule_condition where rule_group_uuid=#{ruleGroupUuid} and uuid not in " +
                "<foreach collection='set' item='item' open='(' separator=',' close=')'> " +
                    "#{item}" +
                "</foreach>" +
            "</script>"
    )
    fun deleteMutiRuleCondition(@Param("ruleGroupUuid") ruleGroupUuid:String, @Param("set") uuidSet:Set<String>):Boolean

    @Select("select * rule_compiled_script where rule_uuid = #{ruleUUID} and language=#{language} and  dialect=#{dialect}")
    fun findRuleCompiledScriptWithRuleUUIDAndLang(ruleUUID: String, lang:String, dialect:String): RuleCompiledScript

    @Insert("insert into rule_compiled_script(rule_uuid, language, dialect, script, version) values (#{ruleUUID}, #{language}, #{dialect}, #{script}, #{version})")
    fun insertRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)

    @Update("update rule_compiled_script set script=#{script}, language=#{language}, dialect=#{dialect}, version=#{version} where rule_uuid=#{ruleUUID}")
    fun updateRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)

    @Select("select * from rule_compiled_script")
    fun findAllRuleCompiledScripts():List<RuleCompiledScript>

    @Select("select * from rule_action where rule_uuid=#{ruleUUID}")
    fun getRuleActionListByRuleUUID(ruleUUID:String):List<RuleAction>?

    @Insert("INSERT INTO rule_action ( uuid, rule_uuid, flag, action_code, params_value, extra_map) VALUES ( #{uuid}, #{flag}, #{ruleUUID}, #{actionCode}, #{paramsValue}, #{extraMap})")
    fun insertRuleAction(ruleAction: RuleAction):Boolean

    @Update("update rule_action set rule_uuid = #{ruleUUID}, flag=#{flag}, action_code = #{actionCode}, params_value = #{paramsValue}, extra_map = #{extraMap} where uuid = #{uuid}")
    fun updateRuleAction(ruleAction: RuleAction):Boolean
}