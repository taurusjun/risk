package com.mtech.risk.dataio.model

data class RuleCondition(
    val id: Int,
    var uuid:String?,
    var ruleGroupUuid:String?,
    val logicCode:String,
    val leftId: Int,
    val operatorCode:String,
    val rightValue:String
    ){
    lateinit var ruleConditionLeftElement: RuleConditionElement
    lateinit var ruleConditionOperator: RuleConditionOperator
}