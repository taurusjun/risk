package com.mtech.risk.management.utils

import com.mtech.risk.dataio.model.*
import com.mtech.risk.management.bff.model.*
import com.mtech.risk.plugin.model.*

class Convertor {
    companion object{
        /**
         * rule logic domain model to rule logic UI VO
         */
        fun convertRuleLogicToUIVO(ruleLogic: RuleLogic): RuleLogicVO {
            val ruleVO = RuleLogicVO()
            ruleVO.setUuid(ruleLogic.uuid)
            ruleVO.setName(ruleLogic.name)
            ruleVO.setCode(ruleLogic.code)
            ruleVO.setCategoryId(ruleLogic.categoryId)
            ruleVO.setDescription(ruleLogic.description)
            ruleVO.setStatus(ruleLogic.status)
            ruleVO.setVersion(ruleLogic.version)
            ruleVO.setRuleGroups(mutableListOf())
            //convert to model
            val ruleGroupList = ruleLogic.ruleGroups
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
         * UI Rule Logic VO to Rule Logic domain model
         */
        fun convertVOToRuleLogic(ruleLogicVO: RuleLogicVO): RuleLogic {
            val rule = RuleLogic(
                0,
                ruleLogicVO.uuid,
                ruleLogicVO.name,
                ruleLogicVO.code,
                ruleLogicVO.categoryId,
                ruleLogicVO.description,
                ruleLogicVO.status,
                ruleLogicVO.version
            )
            rule.ruleGroups = mutableListOf()
            //convert to model
            val ruleGroupVOList = ruleLogicVO.ruleGroups
            for (rulGrpVO in ruleGroupVOList) {
                /// rule group
                var ruleGroup = RuleGroup(0, rulGrpVO.uuid, ruleLogicVO.uuid, rulGrpVO.getLogicCode())
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

        //////////////////////////////////////

        /**
         * rule action domain model to rule action UI VO
         */
        fun convertRuleActionToUIVO(uuid:String, ruleActionList: List<RuleAction>?): RuleWithActionsVO {
            val ruleWithActionsVO = RuleWithActionsVO()
            ruleWithActionsVO.setUuid(uuid)
            if (ruleActionList != null) {
                for (ruleAction: RuleAction in ruleActionList){
                    val ruleActionVO = convertRuleActionToVO(ruleAction)
                    ruleWithActionsVO.getRuleActionVOList().add(ruleActionVO)
                }
            }

            return  ruleWithActionsVO
        }

        fun convertRuleActionToVO(ruleAction: RuleAction): RuleActionVO {
            val ruleActionVO = RuleActionVO()
            ruleActionVO.setUuid(ruleAction.uuid)
            ruleActionVO.setFlag(ruleAction.flag)
            ruleActionVO.setActionCode(ruleAction.actionCode)
            ruleActionVO.setParamsValue(ruleAction.paramsValue)
            ruleActionVO.setExtraMap(ruleAction.extraMap)
            return ruleActionVO
        }

        /**
         * convert rule to rule vo
         */
        fun convertRuleToVO(rule: Rule): RuleVO {
            val ruleVO = RuleVO()
            ruleVO.setUuid(rule.uuid)
            ruleVO.setName(rule.name)
            ruleVO.setCode(rule.code)
            ruleVO.setCategoryId(rule.categoryId)
            ruleVO.setDescription(rule.description)
            ruleVO.setStatus(rule.status)
            ruleVO.setVersion(rule.version)
            return ruleVO
        }

        /**
         * convert rule to executor rule model
         */
        fun convertRuleToRuleObject(rule: RuleComplete): RuleObject {
            val ruleObj = RuleObject()
            ruleObj.uuid = rule.uuid
            ruleObj.code = rule.code
            ruleObj.name = rule.name
            ruleObj.status = rule.status
            for (ruleGroup: RuleGroup in rule.ruleGroups){
                val ruleGroupObject: RuleGroupObject = convertRuleGroupToRuleGroupObject(ruleGroup)
                ruleObj.ruleGroupList.add(ruleGroupObject)
            }
            for(ruleAction: RuleAction in rule.ruleActions){
                val ruleActionObject = convertRuleActionToRuleActionObject(ruleAction)
                ruleObj.ruleActionList.add(ruleActionObject)
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

        private fun convertRuleActionToRuleActionObject(ruleAction: RuleAction): RuleActionObject {
            val ruleActionObject = RuleActionObject();
            ruleActionObject.uuid = ruleAction.uuid
            ruleActionObject.flag = ruleAction.flag
            ruleActionObject.ruleUUID = ruleAction.ruleUUID
            ruleActionObject.actionCode = ruleAction.actionCode
            ruleActionObject.paramsValue = ruleAction.paramsValue
            ruleActionObject.extraMap = ruleAction.extraMap
            return ruleActionObject
        }
    }
}