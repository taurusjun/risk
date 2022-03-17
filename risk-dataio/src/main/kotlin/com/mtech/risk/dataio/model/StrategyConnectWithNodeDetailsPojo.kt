package com.mtech.risk.dataio.model

data class StrategyConnectWithNodeDetailsPojo(
    val id: Int,
    val uuid: String,
    val fromNodeUuid: String,
    val fromNodeCode: String,
    val toNodeUuid: String,
    val toNodeCode: String,
    val logic: String,
){
    lateinit var fromNode: StrategyNodePojo
    lateinit var toNode: StrategyNodePojo
}
