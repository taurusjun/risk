package com.mtech.risk.dataio.model

data class StrategyWithConnectsPojo(
    val id: Int,
    val uuid: String,
    val code: String,
    val description: String?,
){
    lateinit var connects: List<StrategyConnectPojo>
}
