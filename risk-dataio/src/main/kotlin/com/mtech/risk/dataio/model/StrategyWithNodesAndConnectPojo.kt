package com.mtech.risk.dataio.model

data class StrategyWithNodesAndConnectPojo(
    val id: Int,
    val uuid: String,
    val code: String,
    val description: String?,
    val startNodeUuid: String,
){
    lateinit var startNode: StrategyNodePojo
    lateinit var nodes: MutableList<StrategyNodeWithConnectPojo>
}
