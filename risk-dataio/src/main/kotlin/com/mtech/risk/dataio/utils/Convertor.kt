package com.mtech.risk.dataio.utils

import com.mtech.risk.dataio.model.*

class Convertor {
    companion object{
        fun convertStrategyNodePojo2StrategyNode(strategyNodePojo: StrategyNodePojo):StrategyNode{
            return buildStrategyNode(
                strategyNodePojo.id,
                strategyNodePojo.uuid,
                strategyNodePojo.code,
                strategyNodePojo.description,
                strategyNodePojo.type,
                strategyNodePojo.weight,
                strategyNodePojo.ruleUuid,
                strategyNodePojo.result,
                strategyNodePojo.strategyUuid
            )
        }

        fun convertStrategyNodeWithConnectPojo2StrategyNodeWithConnect(strategyNodeWithConnectPojo: StrategyNodeWithConnectPojo):StrategyNodeWithConnect {
            val strategyNodeWithConnect: StrategyNodeWithConnect = StrategyNodeWithConnect()
            //// build node
            val strategyNode = buildStrategyNode(
                strategyNodeWithConnectPojo.id,
                strategyNodeWithConnectPojo.uuid,
                strategyNodeWithConnectPojo.code,
                strategyNodeWithConnectPojo.description,
                strategyNodeWithConnectPojo.type,
                strategyNodeWithConnectPojo.weight,
                strategyNodeWithConnectPojo.ruleUuid,
                strategyNodeWithConnectPojo.result,
                strategyNodeWithConnectPojo.strategyUuid
            )
            strategyNodeWithConnect.setStrategyNode(strategyNode)
            //// build connect list
            strategyNodeWithConnect.setConnects(mutableListOf())
            val connectWithNodeDetailsList = strategyNodeWithConnectPojo.connects
            for(strategyConnectWithNodeDetailsPojo: StrategyConnectWithNodeDetailsPojo in connectWithNodeDetailsList){
                val fromNodePojo = strategyConnectWithNodeDetailsPojo.fromNode
                val fromNode = convertStrategyNodePojo2StrategyNode(fromNodePojo)
                val toNodePojo = strategyConnectWithNodeDetailsPojo.toNode
                val toNode = convertStrategyNodePojo2StrategyNode(toNodePojo)
                val strategyConnect = StrategyConnect(strategyConnectWithNodeDetailsPojo.id, strategyConnectWithNodeDetailsPojo.uuid, strategyConnectWithNodeDetailsPojo.logic, fromNode, toNode)
                strategyNodeWithConnect.getConnects().add(strategyConnect)
            }

            return strategyNodeWithConnect
        }


        fun convertStrategyWithNodeAndConnectPojo2StrategyComplete(strategyWithNodesAndConnectPojo: StrategyWithNodesAndConnectPojo):StrategyComplete {
            val strategyComplete = StrategyComplete()
            //base info
            strategyComplete.setId(strategyWithNodesAndConnectPojo.id)
            strategyComplete.setUuid(strategyWithNodesAndConnectPojo.uuid)
            strategyComplete.setCode(strategyWithNodesAndConnectPojo.code)
            strategyComplete.setDescription(strategyWithNodesAndConnectPojo.description)
            //start node
            val startNode = convertStrategyNodePojo2StrategyNode(strategyWithNodesAndConnectPojo.startNode)
            strategyComplete.setStartNode(startNode)
            //graph
            val graph = mutableMapOf<StrategyNode, List<StrategyNode>>()
            val strategyNodeWithConnectPojoList = strategyWithNodesAndConnectPojo.nodes
            for(strategyNodeWithConnectPojo:StrategyNodeWithConnectPojo in strategyNodeWithConnectPojoList){
                val strategyNodeWithConnect:StrategyNodeWithConnect = convertStrategyNodeWithConnectPojo2StrategyNodeWithConnect(strategyNodeWithConnectPojo)
                val fromNode = strategyNodeWithConnect.strategyNode
                val toNodeList = mutableListOf<StrategyNode>()
                val connectList: List<StrategyConnect> = strategyNodeWithConnect.connects
                for(strategyConnect:StrategyConnect in connectList){
                    toNodeList.add(strategyConnect.getToNode())
                }
                graph[fromNode] = toNodeList
            }
            strategyComplete.setGraph(graph)
            return  strategyComplete;
        }


        /**
         *  build strategyNode
         */
        private fun buildStrategyNode(id: Int,
                                      uuid: String,
                                      code: String,
                                      description: String?,
                                      type: String,
                                      weight: Int,
                                      ruleUuid: String?,
                                      result: String?,
                                      strategyUuid: String
                                      ):StrategyNode{
            var strategyNode: StrategyNode?
            when(type){
                //start node
                StrategyNodeTypeEnum.start.name -> {
                    strategyNode = StrategyStartNode(
                        id,
                        uuid,
                        code,
                        description,
                        StrategyNodeTypeEnum.start,
                        strategyUuid
                    )
                }
                //common node
                StrategyNodeTypeEnum.common.name -> {
                    strategyNode = StrategyCommonNode(
                        id,
                        uuid,
                        code,
                        description,
                        StrategyNodeTypeEnum.common,
                        strategyUuid,
                        weight,
                        ruleUuid
                    )
                }
                //result node
                StrategyNodeTypeEnum.result.name -> {
                    if(result==null){
                        throw RuntimeException("Strategy Result node's result field could not be null")
                    }
                    strategyNode = StrategyResultNode(
                        id,
                        uuid,
                        code,
                        description,
                        StrategyNodeTypeEnum.result,
                        strategyUuid,
                        StrategyResultEnum.valueOf(result)
                    )
                }
                //
                else -> {
                    throw RuntimeException("Cannot build strategyNode, type :${type} cannot be recognized.");
                }
            }
            return strategyNode;
        }
    }
}