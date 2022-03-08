package com.mtech.risk.dataio.model

data class RuleAction (
    val id:Integer,
    val uuid:String,
    val flag:String,
    val ruleUUID:String,
    val actionCode:String,
    val paramsValue:String?,
    val extraMap:String?
    ){
}