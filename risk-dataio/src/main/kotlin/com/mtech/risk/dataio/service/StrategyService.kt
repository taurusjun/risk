package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.StrategyDAO
import com.mtech.risk.dataio.model.*
import com.mtech.risk.dataio.utils.Convertor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StrategyService(@Autowired private val strategyDAO: StrategyDAO) {
    fun getStrategyOuterModelByStrategyUUID(strategyUUID: String): StrategyOuterModel? {
        val strategyWithNodesAndConnectPojo = strategyDAO.getSingleStrategyWithNodesAndConnectByUUID(strategyUUID)?:return null
        return Convertor.convertStrategyWithNodeAndConnectPojo2StrategyOuterModel(strategyWithNodesAndConnectPojo)
    }

    fun getStrategyInnerDetailByStrategyUUID(strategyUUID: String): StrategyInnerDetail? {
        val strategyWithNodesAndConnectPojo = strategyDAO.getSingleStrategyWithNodesAndConnectByUUID(strategyUUID)?:return null
        return Convertor.convertStrategyWithNodeAndConnectPojo2StrategyInnerDetail(strategyWithNodesAndConnectPojo)
    }

    fun getStrategyNodesByStrategyUUID(strategyUUID: String): List<StrategyNode>? {
        val strategyNodePojoList = strategyDAO.getStrategyNodesByStrategyUUID(strategyUUID)?:return null
        val strategyNodeList = mutableListOf<StrategyNode>()
        for (strategyNodePojo:StrategyNodePojo in strategyNodePojoList){
            val strategyNode = Convertor.convertStrategyNodePojo2StrategyNode(strategyNodePojo)
            strategyNodeList.add(strategyNode)
        }
        return strategyNodeList;
    }

    fun getStrategyNodeConnectsPojoByFromNodeUUID(fromNodeUUIDSet:Set<String>):List<StrategyNodeConnectPojo> =
        strategyDAO.selectStrategyNodeConnectsByFromNodeUUID(fromNodeUUIDSet)

    fun getStrategyNodesAndConnectPojoByStrategyUUID(strategyUUID: String): List<StrategyNodeWithConnectPojo>? =
        strategyDAO.getStrategyNodesAndConnectByStrategyUUID(strategyUUID)

    fun getSingleStrategyWithNodesAndConnectPojoByUUID(strategyUUID: String): StrategyWithNodesAndConnectPojo? =
        strategyDAO.getSingleStrategyWithNodesAndConnectByUUID(strategyUUID)

    fun getStrategyConnectsByUUID(strategyUUID: String): List<StrategyConnectPojo>? =
        strategyDAO.getStrategyConnectsByUUID(strategyUUID)

    fun getStrategyWithConnectsByUUID(strategyUUID: String): StrategyWithConnectsPojo? =
        strategyDAO.getStrategyWithConnectsByUUID(strategyUUID)
}