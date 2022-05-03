package com.mtech.risk.dataio.model

data class StrategyNodeWithConnectPojo(
    val id: Int,
    val uuid: String,
    val code: String,
    val description: String?,
    val type: String,
    val weight: Int,
    val ruleUuid: String?,
    val result: String?,
    val strategyUuid: String,
) {
    lateinit var connects: List<StrategyNodeConnectWithNodeDetailsPojo>
}
