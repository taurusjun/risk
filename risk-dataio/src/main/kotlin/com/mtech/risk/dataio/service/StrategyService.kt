package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.StrategyDAO
import com.mtech.risk.dataio.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StrategyService(@Autowired private val strategyDAO: StrategyDAO) {
    fun getStrategyNodesByStrategyUUID(strategyUUID: String): List<StrategyNode>? {
        val strategyNodePojoList = strategyDAO.getStrategyNodesByStrategyUUID(strategyUUID)?:return null
        val strategyNodeList = mutableListOf<StrategyNode>()
        for (strategyNodePojo:StrategyNodePojo in strategyNodePojoList){
            val type = strategyNodePojo.type
            when(type){
                //start node
                StrategyNodeTypeEnum.start.name -> {
                    val strategyNode = StrategyStartNode(
                        strategyNodePojo.id,
                        strategyNodePojo.uuid,
                        strategyNodePojo.code,
                        strategyNodePojo.description,
                        StrategyNodeTypeEnum.start,
                        strategyNodePojo.strategyUuid
                    )
                    strategyNodeList.add(strategyNode)
                }
                //common node
                StrategyNodeTypeEnum.common.name -> {
                    val strategyNode = StrategyCommonNode(
                        strategyNodePojo.id,
                        strategyNodePojo.uuid,
                        strategyNodePojo.code,
                        strategyNodePojo.description,
                        StrategyNodeTypeEnum.common,
                        strategyNodePojo.strategyUuid,
                        strategyNodePojo.weight,
                        strategyNodePojo.ruleUuid
                    )
                    strategyNodeList.add(strategyNode)
                }
                //result node
                StrategyNodeTypeEnum.result.name -> {
                    if(strategyNodePojo.result==null){
                        throw RuntimeException("Strategy Result node's result field could not be null")
                    }
                    val strategyNode = StrategyResultNode(
                        strategyNodePojo.id,
                        strategyNodePojo.uuid,
                        strategyNodePojo.code,
                        strategyNodePojo.description,
                        StrategyNodeTypeEnum.result,
                        strategyNodePojo.strategyUuid,
                        StrategyResultEnum.valueOf(strategyNodePojo.result)
                    )
                    strategyNodeList.add(strategyNode)
                }
            }
        }
        return strategyNodeList;
    }

    fun getStrategyConnectsByFromNodeUUID(fromNodeUUIDSet:Set<String>):List<StrategyConnect> =
        strategyDAO.selectStrategyConnectsByFromNodeUUID(fromNodeUUIDSet)
}