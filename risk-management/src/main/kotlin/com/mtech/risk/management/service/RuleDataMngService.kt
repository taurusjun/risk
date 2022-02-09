package com.mtech.risk.management.service

import com.mtech.risk.dataio.model.Rule
import com.mtech.risk.dataio.model.RuleCondition
import com.mtech.risk.dataio.model.RuleGroup
import com.mtech.risk.dataio.service.RuleService
import com.mtech.risk.plugin.model.*
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class RuleDataMngService(@Autowired private val ruleService: RuleService, @Autowired private val riskRuleScriptExecutor: RiskRuleScriptExecutor) {
    fun compileScript(uuid:String){
        val rule: Rule? = ruleService.getRule(uuid)
        if(rule!=null){
            val ruleObj: RuleObject = convertRule(rule)
            riskRuleScriptExecutor.compile(ruleObj);
        }
    }

    private fun convertRule(rule:Rule):RuleObject{
        val ruleObj: RuleObject = RuleObject()
        ruleObj.uuid = rule.uuid
        ruleObj.code = rule.code
        ruleObj.name = rule.name
        ruleObj.status = rule.status
        for (ruleGroup: RuleGroup in rule.ruleGroups){
            val ruleGroupObject:RuleGroupObject = convertRuleGroup(ruleGroup)
            ruleObj.ruleGroupList.add(ruleGroupObject)
        }
        return  ruleObj
    }

    private fun convertRuleGroup(ruleGroup: RuleGroup):RuleGroupObject{
        val ruleGroupObject: RuleGroupObject = RuleGroupObject()
        ruleGroupObject.uuid = ruleGroup.uuid
        ruleGroupObject.logicCode = ruleGroup.logicCode
        for(ruleCondition: RuleCondition in ruleGroup.ruleConditions){
            val ruleConditionObject = RuleConditionObject()
            ruleConditionObject.uuid = ruleCondition.uuid;
            ruleConditionObject.logicCode = ruleCondition.logicCode;
            //leftNode
            val ruleConditionElementObject = RuleConditionElementObject()
            ruleConditionElementObject.code = ruleCondition.ruleConditionLeftElement.code
            ruleConditionElementObject.identifyType = ruleCondition.ruleConditionLeftElement.identifyType
            ruleConditionElementObject.returnType = ruleCondition.ruleConditionLeftElement.returnType
            ruleConditionObject.leftNode = ruleConditionElementObject;
            //operator
            val ruleConditionOperatorObject = RuleConditionOperatorObject()
            ruleConditionOperatorObject.uuid = ruleCondition.ruleConditionOperator.uuid
            ruleConditionOperatorObject.code = ruleCondition.ruleConditionOperator.code
            ruleConditionOperatorObject.compareType = ruleCondition.ruleConditionOperator.compareType
            ruleConditionObject.operator = ruleConditionOperatorObject;
            //rightValue
            ruleConditionObject.rightValue = ruleCondition.rightValue;
            //add to list
            ruleGroupObject.ruleConditionList.add(ruleConditionObject)
        }
        return ruleGroupObject
    }
}