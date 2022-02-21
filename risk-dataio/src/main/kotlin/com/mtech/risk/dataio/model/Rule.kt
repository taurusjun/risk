package com.mtech.risk.dataio.model

class Rule (
    val id:Int,
    val uuid:String,
    val name:String,
    val code:String,
    val categoryId:Int,
    val description:String,
    val status:String,
    val version:Int,
    ){
    lateinit var ruleGroups: List<RuleGroup>
}