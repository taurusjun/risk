package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.StrategyDAO
import com.mtech.risk.dataio.model.*
import com.mtech.risk.dataio.utils.Convertor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StrategyService(@Autowired private val strategyDAO: StrategyDAO) {
    /**
     * StrategyOuterModel
     */
    fun getStrategyOuterModelByStrategyUUID(strategyUUID: String): StrategyOuterModel? {
        val strategyWithNodesAndConnectPojo = strategyDAO.getSingleStrategyWithNodesAndConnectByUUID(strategyUUID)?:return null
        return Convertor.convertStrategyWithNodeAndConnectPojo2StrategyOuterModel(strategyWithNodesAndConnectPojo)
    }

    /**
     * All StrategyInnerDetail
     */
    fun getAllStrategyInnerDetail(): List<StrategyInnerDetail>? {
        val strategyWithNodesAndConnectPojoList = strategyDAO.getAllStrategyWithNodesAndConnectByUUID()?:return null
        val strategyInnerDetailList= mutableListOf<StrategyInnerDetail>()
        for(strategyWithNodesAndConnectPojo in strategyWithNodesAndConnectPojoList){
            val strategyInnerDetail = Convertor.convertStrategyWithNodeAndConnectPojo2StrategyInnerDetail(strategyWithNodesAndConnectPojo)
            strategyInnerDetailList.add(strategyInnerDetail)
        }
        return strategyInnerDetailList
    }

    /**
     * StrategyInnerDetail
     */
    fun getStrategyInnerDetailByStrategyUUID(strategyUUID: String): StrategyInnerDetail? {
        val strategyWithNodesAndConnectPojo = strategyDAO.getSingleStrategyWithNodesAndConnectByUUID(strategyUUID)?:return null
        return Convertor.convertStrategyWithNodeAndConnectPojo2StrategyInnerDetail(strategyWithNodesAndConnectPojo)
    }

    fun getStrategyNodesByStrategyUUID(strategyUUID: String): List<StrategyNode>? {
        val strategyNodePojoList = strategyDAO.getStrategyNodesByStrategyUUID(strategyUUID)?:return null
        return Convertor.convertStrategyNodePojoList2StrategyNodeList(strategyNodePojoList);
    }

    fun getAllStrategyNodes(): List<StrategyNode>? {
        val strategyNodePojoList = strategyDAO.getAllStrategyNodes()?:return null
        return Convertor.convertStrategyNodePojoList2StrategyNodeList(strategyNodePojoList);
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