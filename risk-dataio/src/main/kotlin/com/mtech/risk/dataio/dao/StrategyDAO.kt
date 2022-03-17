package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.*
import org.apache.ibatis.annotations.*

@Mapper
interface StrategyDAO {
    @Select("select id, uuid, code, description, start_node_uuid from strategy where uuid=#{uuid}")
    @Results(value = [
        Result(property = "startNode", column = "start_node_uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyNodesByUuid")
        ),
        Result(property = "nodes", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.StrategyDAO.getStrategyNodesAndConnectByStrategyUUID")
        )
    ])
    fun getSingleStrategyWithNodesAndConnectByUUID(uuid: String): StrategyWithNodesAndConnectPojo?

    @Select("select id, uuid, description, start_node_uuid from strategy")
    fun getAllStrategy(): List<StrategyPojo>?

    @Select("select id, uuid, description, start_node_uuid from strategy where uuid=#{uuid}")
    fun getSingleStrategyByUUID(uuid: String): StrategyPojo?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where strategy_uuid=#{strategyUUID}")
    @Results(value = [
        Result(property = "connects", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.StrategyDAO.getStrategyConnectWithNodeDetailsListByFromNodeUuid")
        )
    ])
    fun getStrategyNodesAndConnectByStrategyUUID(strategyUUID: String): List<StrategyNodeWithConnectPojo>?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where uuid=#{uuid}")
    fun getSingleStrategyNodesByUuid(uuid: String): StrategyNodePojo?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where strategy_uuid=#{strategyUUID}")
    fun getStrategyNodesByStrategyUUID(strategyUUID: String): List<StrategyNodePojo>?

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_connect where from_node_uuid=#{fromNodeUUID}")
    @Results(value = [
        Result(property = "fromNode", column = "from_node_uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyNodesByUuid")
        ),
        Result(property = "toNode", column = "to_node_uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyNodesByUuid")
        ),
    ])
    fun getStrategyConnectWithNodeDetailsListByFromNodeUuid(fromNodeUUID: String): List<StrategyConnectWithNodeDetailsPojo>

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_connect where from_node_uuid=#{fromNodeUUID}")
    fun getStrategyConnectListByFromNodeUuid(fromNodeUUID: String): List<StrategyConnectPojo>

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_connect")
    fun getAllStrategyConnects(): List<StrategyConnectPojo>?

    @Select("<script>"  +
            "select id, uuid, from_node_uuid, to_node_uuid, logic  from strategy_connect where from_node_uuid in " +
            "<foreach collection='set' item='item' open='(' separator=',' close=')'> " +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    fun selectStrategyConnectsByFromNodeUUID(@Param("set") fromNodeUUIDSet:Set<String>):List<StrategyConnectPojo>
}