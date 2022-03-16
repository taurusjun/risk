package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.StrategyConnect
import com.mtech.risk.dataio.model.StrategyNodePojo
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface StrategyDAO {
    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where strategy_uuid=#{strategyUUID}")
    fun getStrategyNodesByStrategyUUID(strategyUUID: String): List<StrategyNodePojo>?

    @Select("select id, uuid, from_node_uuid, to_node_uuid, logic from strategy_connect")
    fun getAllStrategyConnects(strategyUUID: String): List<StrategyNodePojo>?

    @Select("<script>"  +
            "select id, uuid, from_node_uuid, to_node_uuid, logic  from strategy_connect where from_node_uuid in " +
            "<foreach collection='set' item='item' open='(' separator=',' close=')'> " +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    fun selectStrategyConnectsByFromNodeUUID(@Param("set") fromNodeUUIDSet:Set<String>):List<StrategyConnect>
}