package com.mtech.risk.base.model

data class Event(
    val code:String,
    val name:String,
    val properties:Map<String, String>
)
