package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.RuleDAO
import com.mtech.risk.dataio.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import java.util.*


@Service
open class RuleService(@Autowired private val ruleDAO: RuleDAO, @Autowired private val transactionTemplate: TransactionTemplate) {
    /**
     * insert rule cascade
     */
    fun insertNewRuleCascade(rule: RuleLogic){
        rule.uuid = UUID.randomUUID().toString()
        rule.version = 1
        ruleDAO.insertRuleLogic(rule)
        for (ruleGrp in rule.ruleGroups) {
            this.insertNewRuleGroupCascade(ruleGrp, rule.uuid!!)
        }
    }

    /**
     * update rule cascade
     */
    fun updateRuleCascade(rule: RuleLogic){
        if(rule.uuid==null){
            throw RuntimeException("uuid is null where update rule cascade, rule = $rule")
        }
        ruleDAO.updateRuleLogic(rule)
        //update & insert
        val uuidSet = mutableSetOf<String>()
        for(ruleGrp in rule.ruleGroups){
            if(ruleGrp.uuid !=null){
                this.updateRuleGroupCascade(ruleGrp)
            }else{
                this.insertNewRuleGroupCascade(ruleGrp, rule.uuid!!)
            }
            uuidSet.add(ruleGrp.uuid!!)
        }
        //delete
        this.deleteMutiRuleGroup(rule.uuid!!, uuidSet)
    }

    /**
     * insert rule group cascade
     */
    fun insertNewRuleGroupCascade(ruleGroup: RuleGroup, ruleUUID:String):String{
        ruleGroup.uuid = UUID.randomUUID().toString()
        ruleGroup.ruleUuid = ruleUUID
        ruleDAO.insertRuleGroup(ruleGroup);
        for( ruleCond in ruleGroup.ruleConditions){
            this.insertNewRuleCondition(ruleCond, ruleGroup.uuid!!)
        }
        return ruleGroup.uuid!!
    }

    /**
     * update rule group cascade
     */
    fun updateRuleGroupCascade(ruleGroup: RuleGroup){
        if(ruleGroup.uuid==null || ruleGroup.ruleUuid ==null){
            throw RuntimeException("uuid or ruleUuid is null where update rule group cascade, ruleGroup = $ruleGroup")
        }
        ruleDAO.updateRuleGroup(ruleGroup)
        //update & insert
        val uuidSet = mutableSetOf<String>()
        for( ruleCond in ruleGroup.ruleConditions){
            //no uuid then insert
            if(ruleCond.uuid!=null){
                this.updateRuleCondition(ruleCond);
            }else{
                this.insertNewRuleCondition(ruleCond, ruleGroup.uuid!!)
            }
            uuidSet.add(ruleCond.uuid!!)
        }
        //delete
        this.deleteMutiRuleCondition(ruleGroup.uuid!!, uuidSet)
    }

    /**
     * delete rule group NOT in the notDeleteUuidSet
     */
    fun deleteMutiRuleGroup(ruleUuid: String, notDeleteUuidSet:Set<String>):Boolean{
        return ruleDAO.deleteMutiRuleGroup(ruleUuid, notDeleteUuidSet)
    }

    fun insertNewRuleCondition(ruleCondition: RuleCondition, ruleGroupUuid: String):String{
        ruleCondition.uuid = UUID.randomUUID().toString()
        ruleCondition.ruleGroupUuid = ruleGroupUuid
        ruleDAO.insertRuleCondition(ruleCondition)
        return ruleCondition.uuid!!
    }

    fun updateRuleCondition(ruleCondition: RuleCondition):Boolean{
        return ruleDAO.updateRuleCondition(ruleCondition)
    }

    /**
     * delete rule condition NOT in the notDeleteUuidSet
     */
    fun deleteMutiRuleCondition(ruleGroupUuid: String, notDeleteUuidSet:Set<String>):Boolean{
        return ruleDAO.deleteMutiRuleCondition(ruleGroupUuid, notDeleteUuidSet)
    }

    fun getRuleVersion(uuid:String): Int =
        ruleDAO.getRuleVersionByUuid(uuid)

    fun getAllRules(): List<Rule>? =
        ruleDAO.getAllRules()

    fun getAllRuleLogics(): List<RuleLogic>? =
        ruleDAO.getAllRuleLogics()

    fun getRuleLogic(uuid:String): RuleLogic? =
        ruleDAO.getRuleLogicByUuid(uuid)

    fun getCompleteRule(uuid:String): RuleComplete? =
        ruleDAO.getCompleteRuleByUuid(uuid)

    fun getRuleGroupByUUID(uuid:String): RuleGroup? =
        ruleDAO.getRuleGroupByUuid(uuid)

    fun getRuleGroupsByRuleUUID(ruleUuid:String): List<RuleGroup>? =
        ruleDAO.getRuleGroupByRuleUuid(ruleUuid)

    fun getRuleConditionByRuleGroupUUID(ruleGroupUuid:String): RuleCondition? =
        ruleDAO.getRuleConditionByRuleGroupUuid(ruleGroupUuid)

    fun getAllRuleConditionElements(): List<RuleConditionElement> =
        ruleDAO.getAllRuleConditionElements()

    fun getAllRuleConditionOperators(): List<RuleConditionOperator> =
        ruleDAO.getAllRuleConditionOperators()

    fun getRuleConditionElementById(id:String): RuleConditionElement? =
        ruleDAO.getRuleConditionElementById(id)

    fun getRuleActionListByUUID(uuid:String): List<RuleAction>? =
        ruleDAO.getRuleActionListByRuleUUID(uuid)

    fun getRuleCompiledScriptByRuleUUIDAndLang(uuid:String, lang:String, dialect:String):RuleCompiledScript?=
        ruleDAO.findRuleCompiledScriptWithRuleUUIDAndLang(uuid, lang, dialect)

    fun getRuleCompiledScriptByRuleUUIDWithDefaultLang(uuid:String):RuleCompiledScript?=
        ruleDAO.findRuleCompiledScriptWithRuleUUIDAndLang(uuid, "java", "MVEL")

    fun insertRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)=
        ruleDAO.insertRuleCompiledScript(ruleCompiledScript)

    fun updateRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)=
        ruleDAO.updateRuleCompiledScript(ruleCompiledScript)

//    @Transactional
    open fun getAllRuleCompiledScripts():List<RuleCompiledScript>?=
        ruleDAO.findAllRuleCompiledScripts()
}