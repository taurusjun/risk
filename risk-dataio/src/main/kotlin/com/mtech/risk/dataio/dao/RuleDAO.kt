package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.*
import org.apache.ibatis.annotations.*

@Mapper
interface RuleDAO {
    @Select("select * from rule")
    @Results(value = [
        Result(property = "ruleGroups", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleGroupByRuleUuid")
        )
    ])
    fun getAllRules():List<Rule>

    @Select("select * from rule where uuid=#{uuid}")
    @Results(value = [
        Result(property = "ruleGroups", column = "uuid",
               many = Many(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleGroupByRuleUuid")
        )
    ])
    fun getRuleByUuid(uuid:String):Rule

    @Select("select * from rule_group where uuid=#{uuid}")
    fun getRuleGroupByUuid(uuid:String):RuleGroup

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
        Result(property = "ruleConditionOperator", column = "operator_uuid",
        one = One(select = "com.mtech.risk.dataio.dao.RuleDAO.getRuleConditionOperatorByUuid")
        )
    ])
    fun getRuleConditionByRuleGroupUuid(ruleGroupUuid:String):RuleCondition?

    @Select("select * from rule_condition_element where id=#{id}")
    fun getRuleConditionElementById(id:String):RuleConditionElement?

    @Select("select * from rule_condition_operator where uuid=#{uuid}")
    fun getRuleConditionOperatorByUuid(uuid:String):RuleConditionOperator?

    @Select("select * rule_compiled_script where rule_uuid = #{ruleUUID} and language=#{language} and  dialect=#{dialect}")
    fun findRuleCompiledScriptWithRuleUUIDAndLang(ruleUUID: String, lang:String, dialect:String): RuleCompiledScript

    @Insert("insert into rule_compiled_script(rule_uuid, language, dialect, script, version) values (#{ruleUUID}, #{language}, #{dialect}, #{script}, #{version})")
    fun insertRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)

    @Update("update rule_compiled_script set script=#{script}, version=#{version} where id=#{id}")
    fun updateRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)

    @Select("select * from rule_compiled_script")
    fun findAllRuleCompiledScripts():List<RuleCompiledScript>
}