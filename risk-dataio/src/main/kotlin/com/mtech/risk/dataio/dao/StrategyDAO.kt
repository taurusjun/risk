package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.*
import org.apache.ibatis.annotations.*

@Mapper
interface StrategyDAO {

    @Select("select id, uuid, code, description from strategy where uuid=#{uuid}")
    @Results(value = [
        Result(property = "connects", column = "uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getStrategyConnectsByUUID")
        ),
    ])
    fun getStrategyWithConnectsByUUID(uuid: String): StrategyWithConnectsPojo?

    @Select("SELECT frm.strategy_uuid from_strategy, to.strategy_uuid to_strategy, connect.uuid connect_uuid FROM strategy_node_connect connect " +
            "inner join STRATEGY_NODE frm on frm.uuid = connect.from_node_uuid " +
            "inner join STRATEGY_NODE to on to.uuid = connect.to_node_uuid " +
            "where frm.strategy_uuid= #{uuid} and frm.strategy_uuid<>to.strategy_uuid")
    @Results(value = [
        Result(property = "fromStrategy", column = "from_strategy",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyByUUID")
        ),
        Result(property = "toStrategy", column = "to_strategy",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyByUUID")
        ),
        Result(property = "nodeConnect", column = "connect_uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyNodeConnectByUuid")
        )
    ])
    fun getStrategyConnectsByUUID(uuid: String): List<StrategyConnectPojo>?

    @Select("select id, uuid, code, description from strategy")
    @Results(value = [
        Result(property = "startNode", column = "uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStartNodeByStrategyUuid")
        ),
        Result(property = "nodes", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.StrategyDAO.getStrategyNodesAndConnectByStrategyUUID")
        )
    ])
    fun getAllStrategyWithNodesAndConnectByUUID(): List<StrategyWithNodesAndConnectPojo>?

    @Select("select id, uuid, code, description from strategy where uuid=#{uuid}")
    @Results(value = [
        Result(property = "startNode", column = "uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStartNodeByStrategyUuid")
        ),
        Result(property = "nodes", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.StrategyDAO.getStrategyNodesAndConnectByStrategyUUID")
        )
    ])
    fun getSingleStrategyWithNodesAndConnectByUUID(uuid: String): StrategyWithNodesAndConnectPojo?

    @Select("select id, uuid, code, description from strategy")
    fun getAllStrategy(): List<StrategyPojo>?

    @Select("select id, uuid, code, description from strategy where uuid=#{uuid}")
    fun getSingleStrategyByUUID(uuid: String): StrategyPojo?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where strategy_uuid=#{strategyUUID}")
    @Results(value = [
        Result(property = "connects", column = "uuid",
            many = Many(select = "com.mtech.risk.dataio.dao.StrategyDAO.getStrategyNodeConnectWithNodeDetailsListByFromNodeUuid")
        )
    ])
    fun getStrategyNodesAndConnectByStrategyUUID(strategyUUID: String): List<StrategyNodeWithConnectPojo>?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid from strategy_node where type='start' and strategy_uuid=#{strategyUUID}")
    fun getSingleStartNodeByStrategyUuid(strategyUUID: String): StrategyNodePojo?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where uuid=#{uuid}")
    fun getSingleStrategyNodesByUuid(uuid: String): StrategyNodePojo?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node where strategy_uuid=#{strategyUUID}")
    fun getStrategyNodesByStrategyUUID(strategyUUID: String): List<StrategyNodePojo>?

    @Select("select id, uuid, code, description, type, weight, rule_uuid, result, strategy_uuid  from strategy_node")
    fun getAllStrategyNodes(): List<StrategyNodePojo>?

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_node_connect where from_node_uuid=#{fromNodeUUID}")
    @Results(value = [
        Result(property = "fromNode", column = "from_node_uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyNodesByUuid")
        ),
        Result(property = "toNode", column = "to_node_uuid",
            one = One(select = "com.mtech.risk.dataio.dao.StrategyDAO.getSingleStrategyNodesByUuid")
        ),
    ])
    fun getStrategyNodeConnectWithNodeDetailsListByFromNodeUuid(fromNodeUUID: String): List<StrategyNodeConnectWithNodeDetailsPojo>

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_node_connect where uuid=#{uuid}")
    fun getSingleStrategyNodeConnectByUuid(uuid: String): StrategyNodeConnectPojo?

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_node_connect where from_node_uuid=#{fromNodeUUID}")
    fun getStrategyNodeConnectListByFromNodeUuid(fromNodeUUID: String): List<StrategyNodeConnectPojo>

    @Select("select id, uuid, from_node_uuid, from_node_code, to_node_uuid, to_node_code, logic from strategy_node_connect")
    fun getAllStrategyNodeConnects(): List<StrategyNodeConnectPojo>?

    @Select("<script>"  +
            "select id, uuid, from_node_uuid, to_node_uuid, logic  from strategy_node_connect where from_node_uuid in " +
            "<foreach collection='set' item='item' open='(' separator=',' close=')'> " +
            "#{item}" +
            "</foreach>" +
            "</script>"
    )
    fun selectStrategyNodeConnectsByFromNodeUUID(@Param("set") fromNodeUUIDSet:Set<String>):List<StrategyNodeConnectPojo>
}