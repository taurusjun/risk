package com.mtech.risk.dataio.model

class RuleCompiledScript(
    val id: Int,
    val ruleUUID:String,
    val language:String,
    val dialect:String,
    val script:String,
    val version: Int
    ){
}