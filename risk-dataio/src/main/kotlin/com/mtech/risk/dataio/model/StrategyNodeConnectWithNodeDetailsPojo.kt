package com.mtech.risk.dataio.model

data class StrategyNodeConnectWithNodeDetailsPojo(
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
