package com.mtech.risk.dataio.model

class RuleGroup(
    val id: Int,
    val uuid:String,
    val ruleUuid:String,
    val logicCode:String
    ){
    lateinit var ruleConditions: List<RuleCondition>
}