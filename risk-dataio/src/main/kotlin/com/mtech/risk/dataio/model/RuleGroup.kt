package com.mtech.risk.dataio.model

data class RuleGroup(
    val id: Int,
    var uuid:String?,
    var ruleUuid:String?,
    val logicCode:String
    ){
    lateinit var ruleConditions: MutableList<RuleCondition>
}