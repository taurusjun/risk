package com.mtech.risk.dataio.model

class RuleCondition (
    val id:Integer,
    val uuid:String,
    val ruleGroupUuid:String,
    val logicCode:String,
    val leftId:Integer,
    val operatorUuid:String,
    val rightValue:String
    ){
    lateinit var ruleConditionLeftElement: RuleConditionElement
    lateinit var ruleConditionOperator: RuleConditionOperator
}