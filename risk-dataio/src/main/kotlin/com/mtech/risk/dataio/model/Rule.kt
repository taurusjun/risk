package com.mtech.risk.dataio.model

class Rule (
    val id:Integer,
    val uuid:String,
    val name:String,
    val code:String,
    val categoryId:Integer,
    val description:String,
    val status:String,
    val version:Integer,
    ){
    lateinit var ruleGroups: List<RuleGroup>
}