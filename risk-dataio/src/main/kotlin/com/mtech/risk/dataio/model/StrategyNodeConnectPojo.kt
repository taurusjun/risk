package com.mtech.risk.dataio.model

data class StrategyNodeConnectPojo(
    val id: Int,
    val uuid: String,
    val fromNodeUuid: String,
    val fromNodeCode: String,
    val toNodeUuid: String,
    val toNodeCode: String,
    val logic: String,
)
