package com.mtech.risk.dataio.model

data class RuleComplete (
    val id:Int,
    var uuid:String?,
    val name:String,
    val code:String,
    val categoryId:Int,
    val description:String,
    val status:String,
    var version:Int,
    ){
    lateinit var ruleGroups: MutableList<RuleGroup>
    lateinit var ruleActions: MutableList<RuleAction>
}