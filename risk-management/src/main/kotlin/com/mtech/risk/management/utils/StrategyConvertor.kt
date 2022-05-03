package com.mtech.risk.management.utils

import com.mtech.risk.dataio.model.StrategyNode
import com.mtech.risk.dataio.model.StrategyNodesConnect
import com.mtech.risk.management.bff.model.StrategyNodeConnectVO
import com.mtech.risk.management.bff.model.StrategyNodeVO

class StrategyConvertor {
    companion object{
        /**
         * StrategyNode
         */
        fun convertStrategyNodeToVO(strategyNode: StrategyNode): StrategyNodeVO {
            val strategyNodeVO = StrategyNodeVO()
            strategyNodeVO.uuid = strategyNode.uuid
            strategyNodeVO.code = strategyNode.code
            strategyNodeVO.description = strategyNode.description
            strategyNodeVO.type = strategyNode.type.name
            strategyNodeVO.strategyUuid = strategyNode.strategyUuid

            return  strategyNodeVO
        }

        /**
         * StrategyNodeConnect
         */
        fun convertStrategyNodeConnectToVO(strategyNodesConnect: StrategyNodesConnect): StrategyNodeConnectVO {
            val strategyNodeConnectVO = StrategyNodeConnectVO()
            val fromNode = convertStrategyNodeToVO(strategyNodesConnect.fromNode)
            val toNode = convertStrategyNodeToVO(strategyNodesConnect.toNode)
            strategyNodeConnectVO.fromNode = fromNode
            strategyNodeConnectVO.toNode = toNode
            strategyNodeConnectVO.logic = strategyNodesConnect.logic

            return  strategyNodeConnectVO
        }

        /**
         * StrategyNode graph
         */
        fun convertStrategyNodeGraphToVO(graph: Map<StrategyNode, List<StrategyNodesConnect>>): Map<String, List<StrategyNodeConnectVO>> {
            val map = mutableMapOf<String, List<StrategyNodeConnectVO>>()
            for(item in graph){
                val key: StrategyNode = item.key
                val connectList: List<StrategyNodesConnect> = item.value
                val strategyNodeConnectVOList = mutableListOf<StrategyNodeConnectVO>()
                for(strategyNodeConnect in connectList){
                    val strategyNodeConnectVO = convertStrategyNodeConnectToVO(strategyNodeConnect)
                    strategyNodeConnectVOList.add(strategyNodeConnectVO)
                }
                map.put(key.code, strategyNodeConnectVOList)
            }
            return map
        }
    }
}