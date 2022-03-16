package com.mtech.risk.dataio.model

data class StrategyConnect(
    val id: Int,
    val uuid: String,
    val fromNodeUuid: String,
    val toNodeUuid: String,
    val logic: String,
)
