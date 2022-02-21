package com.mtech.risk.dataio.model

class RuleCondition(
    val id: Int,
    val uuid:String,
    val ruleGroupUuid:String,
    val logicCode:String,
    val leftId: Int,
    val operatorCode:String,
    val rightValue:String
    ){
    lateinit var ruleConditionLeftElement: RuleConditionElement
    lateinit var ruleConditionOperator: RuleConditionOperator
}