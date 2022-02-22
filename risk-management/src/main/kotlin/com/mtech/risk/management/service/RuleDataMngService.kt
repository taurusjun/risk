package com.mtech.risk.management.service

import com.mtech.risk.base.model.Event
import com.mtech.risk.base.model.EventContext
import com.mtech.risk.dataio.model.Rule
import com.mtech.risk.dataio.model.RuleCondition
import com.mtech.risk.dataio.model.RuleGroup
import com.mtech.risk.dataio.service.RuleService
import com.mtech.risk.management.bff.model.RuleConditionVO
import com.mtech.risk.management.bff.model.RuleGroupVO
import com.mtech.risk.management.bff.model.RuleVO
import com.mtech.risk.plugin.model.*
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
open class RuleDataMngService(@Autowired private val ruleService: RuleService,
                              @Autowired private val riskRuleScriptExecutor: RiskRuleScriptExecutor,
                              @Autowired private val transactionTemplate: TransactionTemplate) {
    /**
     * Cascade rule update
     */
    fun ruleUpdate(ruleVO: RuleVO) {
        val rule = convertToDomainModel(ruleVO)
        ///////
        transactionTemplate.execute {
            var currentVersion = ruleService.getRuleVersion(ruleVO.uuid)
            if(ruleVO.version==null || ruleVO.version != currentVersion){
                throw RuntimeException("Rule version not capable: current version is $currentVersion, update version is ${ruleVO.version}")
            }
            rule.version++
            ruleService.updateRuleCascade(rule)
        }
    }

    /**
     * Cascade rule insert
     */
    fun ruleInsert(ruleVO: RuleVO) {
        val rule = convertToDomainModel(ruleVO)

        ///////
        transactionTemplate.execute {
            ruleService.insertNewRuleCascade(rule)
        }
    }

    /**
     * UI VO to domain model
     */
    private fun convertToDomainModel(ruleVO: RuleVO): Rule {
        val rule = Rule(
            0,
            ruleVO.uuid,
            ruleVO.name,
            ruleVO.code,
            ruleVO.categoryId,
            ruleVO.description,
            ruleVO.status,
            ruleVO.version
        )
        rule.ruleGroups = mutableListOf()
        //convert to model
        val ruleGroupVOList = ruleVO.ruleGroups
        for (rulGrpVO in ruleGroupVOList) {
            /// rule group
            var ruleGroup = RuleGroup(0, rulGrpVO.uuid, ruleVO.uuid, rulGrpVO.getLogicCode())
            ruleGroup.ruleConditions = mutableListOf()
            rule.ruleGroups.add(ruleGroup)
            for (ruleGrpCondVO in rulGrpVO.ruleConditions) {
                /// rule condition
                val ruleCondition = RuleCondition(
                    0,
                    ruleGrpCondVO.uuid,
                    rulGrpVO.uuid,
                    ruleGrpCondVO.logicCode,
                    ruleGrpCondVO.leftId,
                    ruleGrpCondVO.operatorCode,
                    ruleGrpCondVO.rightValue
                )
                ruleGroup.ruleConditions.add(ruleCondition)
            }
        }
        return rule
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