package com.mtech.risk.dataio.model

data class RuleConditionOperator (
    val id:Integer,
    val code:String,
    val name:String,
    val compareType:String,
    val description:String,
    ){
}