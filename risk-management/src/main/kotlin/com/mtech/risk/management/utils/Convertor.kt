package com.mtech.risk.management.utils

import com.mtech.risk.dataio.model.Rule
import com.mtech.risk.dataio.model.RuleCondition
import com.mtech.risk.dataio.model.RuleGroup
import com.mtech.risk.management.bff.model.RuleConditionVO
import com.mtech.risk.management.bff.model.RuleGroupVO
import com.mtech.risk.management.bff.model.RuleVO
import com.mtech.risk.plugin.model.*

class Convertor {
    companion object{
        /**
         * domain model to UI VO
         */
        fun convertToUIVO(rule: Rule): RuleVO {
            val ruleVO = RuleVO()
            ruleVO.setUuid(rule.uuid)
            ruleVO.setName(rule.name)
            ruleVO.setCode(rule.code)
            ruleVO.setCategoryId(rule.categoryId)
            ruleVO.setDescription(rule.description)
            ruleVO.setStatus(rule.status)
            ruleVO.setVersion(rule.version)
            ruleVO.setRuleGroups(mutableListOf())
            //convert to model
            val ruleGroupList = rule.ruleGroups
            for (rulGrp in ruleGroupList) {
                /// rule group
                var ruleGroupVO = RuleGroupVO()
                ruleGroupVO.setUuid(rulGrp.uuid)
                ruleGroupVO.setLogicCode(rulGrp.logicCode)
                ruleGroupVO.setRuleConditions(mutableListOf())

                ruleVO.getRuleGroups().add(ruleGroupVO)
                for (ruleGrpCond in rulGrp.ruleConditions) {
                    /// rule condition
                    val ruleConditionVO = RuleConditionVO()
                    ruleConditionVO.setUuid(ruleGrpCond.uuid)
                    ruleConditionVO.setLogicCode(ruleGrpCond.logicCode)
                    ruleConditionVO.setLeftId(ruleGrpCond.leftId)
                    ruleConditionVO.setOperatorCode(ruleGrpCond.operatorCode)
                    ruleConditionVO.setRightValue(ruleGrpCond.rightValue)

                    ruleGroupVO.getRuleConditions().add(ruleConditionVO)
                }
            }
            return ruleVO
        }

        /**
         * UI VO to domain model
         */
        fun convertToDomainModel(ruleVO: RuleVO): Rule {
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

        /**
         * convert rule to executor rule model
         */
        fun convertRuleToRuleObject(rule:Rule): RuleObject {
            val ruleObj: RuleObject = RuleObject()
            ruleObj.uuid = rule.uuid
            ruleObj.code = rule.code
            ruleObj.name = rule.name
            ruleObj.status = rule.status
            for (ruleGroup: RuleGroup in rule.ruleGroups){
                val ruleGroupObject: RuleGroupObject = convertRuleGroupToRuleGroupObject(ruleGroup)
                ruleObj.ruleGroupList.add(ruleGroupObject)
            }
            return  ruleObj
        }

        private fun convertRuleGroupToRuleGroupObject(ruleGroup: RuleGroup): RuleGroupObject {
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
}