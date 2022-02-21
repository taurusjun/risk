package com.mtech.risk.management.service

import com.mtech.risk.base.model.Event
import com.mtech.risk.base.model.EventContext
import com.mtech.risk.dataio.model.Rule
import com.mtech.risk.dataio.model.RuleCondition
import com.mtech.risk.dataio.model.RuleGroup
import com.mtech.risk.dataio.service.RuleService
import com.mtech.risk.management.bff.model.RuleVO
import com.mtech.risk.plugin.model.*
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
open class RuleDataMngService(@Autowired private val ruleService: RuleService, @Autowired private val riskRuleScriptExecutor: RiskRuleScriptExecutor) {
        fun ruleUpdate(ruleVO: RuleVO) {
            val ruleGroup = RuleGroup(0,"111-222-333","96668402-87fc-11ec-a8a3-0242ac120002","and")
            val ruleCondition1 = RuleCondition(0,"111-222-333-1",ruleGroup.uuid,"and", 2, "40d2786e-87fe-11ec-a8a3-0242ac120002", "99")
            val ruleCondition2 = RuleCondition(0,"111-222-333-2",ruleGroup.uuid,"and", 1, "40d2786e-87fe-11ec-a8a3-0242ac120002", "88")
            ruleGroup.ruleConditions= listOf(ruleCondition1, ruleCondition2)
//            ruleService.insertNewRuleGroupCascade(ruleGroup)
        }

        fun mockExe(uuid:String):String? {
            val mockEvent = Event(
                "payment",
                "支付",
                ConcurrentHashMap()
            );
            val eventContext = EventContext(
                mockEvent,
                ConcurrentHashMap()
            );
            riskRuleScriptExecutor.execute(eventContext, uuid)
            return "finish"
        }

        fun compileScript(uuid:String):String?{
        val rule: Rule? = ruleService.getRule(uuid)
        if(rule!=null){
            val ruleObj: RuleObject = convertRule(rule)
            val script = riskRuleScriptExecutor.compile(ruleObj);
//            val ruleCompiledScript = RuleCompiledScript(
//                0, ruleObj.uuid, "java", "MVEL",script,1
//            );
//            ruleService.insertRuleCompiledScript(ruleCompiledScript)
            return  script;
        }else{
            return null;
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