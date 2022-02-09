package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.RuleDAO
import com.mtech.risk.dataio.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class RuleService(@Autowired private val ruleDAO: RuleDAO) {

    fun getAllRules(): List<Rule>? =
        ruleDAO.getAllRules()

    fun getRule(uuid:String): Rule? =
        ruleDAO.getRuleByUuid(uuid)

    fun getRuleGroupByUUID(uuid:String): RuleGroup? =
        ruleDAO.getRuleGroupByUuid(uuid)

    fun getRuleGroupByRuleUUID(ruleUuid:String): List<RuleGroup>? =
        ruleDAO.getRuleGroupByRuleUuid(ruleUuid)

    fun getRuleConditionByRuleGroupUUID(ruleGroupUuid:String): RuleCondition? =
        ruleDAO.getRuleConditionByRuleGroupUuid(ruleGroupUuid)

    fun getRuleConditionElementById(id:String): RuleConditionElement? =
        ruleDAO.getRuleConditionElementById(id)

    fun getRuleConditionOperatorByUuid(uuid:String): RuleConditionOperator? =
        ruleDAO.getRuleConditionOperatorByUuid(uuid)

    fun getRuleCompiledScriptByRuleUUIDAndLang(uuid:String, lang:String, dialect:String):RuleCompiledScript?=
        ruleDAO.findRuleCompiledScriptWithRuleUUIDAndLang(uuid, lang, dialect)

    fun getRuleCompiledScriptByRuleUUIDWithDefaultLang(uuid:String):RuleCompiledScript?=
        ruleDAO.findRuleCompiledScriptWithRuleUUIDAndLang(uuid, "java", "MVEL")

    fun insertRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)=
        ruleDAO.insertRuleCompiledScript(ruleCompiledScript)

    fun updateRuleCompiledScript(ruleCompiledScript: RuleCompiledScript)=
        ruleDAO.updateRuleCompiledScript(ruleCompiledScript)

    fun getAllRuleCompiledScripts():List<RuleCompiledScript>?=
        ruleDAO.findAllRuleCompiledScripts()
}