package com.mtech.risk.dataio.model

data class StrategyConnectPojo(
    val fromStrategyUuid: String,
    val toStrategyUuid: String,
    val nodeConnectUuid: String,
){
    lateinit var fromStrategy: StrategyPojo
    lateinit var toStrategy: StrategyPojo
    lateinit var nodeConnect: StrategyNodeConnectPojo
}
