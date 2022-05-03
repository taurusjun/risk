package com.mtech.risk.dataio.utils

import com.mtech.risk.dataio.model.*

class Convertor {
    companion object{
        /**
         * Pojo to model
         */
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

        /**
         * pojo to model
         */
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
            for(strategyNodeConnectWithNodeDetailsPojo: StrategyNodeConnectWithNodeDetailsPojo in connectWithNodeDetailsList){
                val fromNodePojo = strategyNodeConnectWithNodeDetailsPojo.fromNode
                val fromNode = convertStrategyNodePojo2StrategyNode(fromNodePojo)
                val toNodePojo = strategyNodeConnectWithNodeDetailsPojo.toNode
                val toNode = convertStrategyNodePojo2StrategyNode(toNodePojo)
                val strategyNodesConnect = StrategyNodesConnect(
                    strategyNodeConnectWithNodeDetailsPojo.id,
                    strategyNodeConnectWithNodeDetailsPojo.uuid,
                    strategyNodeConnectWithNodeDetailsPojo.logic,
                    fromNode,
                    toNode
                )
                strategyNodeWithConnect.getConnects().add(strategyNodesConnect)
            }

            return strategyNodeWithConnect
        }

        /**
         * 转为策略内部模型
         */
        fun convertStrategyWithNodeAndConnectPojo2StrategyInnerDetail(strategyWithNodesAndConnectPojo: StrategyWithNodesAndConnectPojo): StrategyInnerDetail {
            val strategyInnerDetail = StrategyInnerDetail()
            //base info
            strategyInnerDetail.setId(strategyWithNodesAndConnectPojo.id)
            strategyInnerDetail.setUuid(strategyWithNodesAndConnectPojo.uuid)
            strategyInnerDetail.setCode(strategyWithNodesAndConnectPojo.code)
            strategyInnerDetail.setDescription(strategyWithNodesAndConnectPojo.description)
            //start node
            val startNode = convertStrategyNodePojo2StrategyNode(strategyWithNodesAndConnectPojo.startNode)
            strategyInnerDetail.setStartNode(startNode)
            //graph
            val graph = mutableMapOf<StrategyNode, List<StrategyNodesConnect>>()
            val strategyNodeWithConnectPojoList = strategyWithNodesAndConnectPojo.nodes
            for(strategyNodeWithConnectPojo:StrategyNodeWithConnectPojo in strategyNodeWithConnectPojoList){
                val strategyNodeWithConnect:StrategyNodeWithConnect = convertStrategyNodeWithConnectPojo2StrategyNodeWithConnect(strategyNodeWithConnectPojo)
                val fromNode = strategyNodeWithConnect.strategyNode
                val connectList: List<StrategyNodesConnect> = strategyNodeWithConnect.connects
                graph[fromNode] = connectList
            }
            strategyInnerDetail.setGraph(graph)
            return  strategyInnerDetail;
        }


        /**
         * 转为策略外部模型
         */
        fun convertStrategyWithNodeAndConnectPojo2StrategyOuterModel(strategyWithNodesAndConnectPojo: StrategyWithNodesAndConnectPojo): StrategyOuterModel {
            val strategyOuterModel = StrategyOuterModel()
            //base info
            strategyOuterModel.setId(strategyWithNodesAndConnectPojo.id)
            strategyOuterModel.setUuid(strategyWithNodesAndConnectPojo.uuid)
            strategyOuterModel.setCode(strategyWithNodesAndConnectPojo.code)
            strategyOuterModel.setDescription(strategyWithNodesAndConnectPojo.description)
            //start node
            val startNode = convertStrategyNodePojo2StrategyNode(strategyWithNodesAndConnectPojo.startNode)
            strategyOuterModel.setStartNode(startNode)
            //resultNodes & outerNodeMap
            val resultNodes = mutableListOf<StrategyNode>()
            val outerNodeMap = mutableMapOf<StrategyNode, MutableList<StrategyNodesConnect>>()
            val strategyUUID = strategyWithNodesAndConnectPojo.uuid
            val strategyNodeWithConnectPojoList = strategyWithNodesAndConnectPojo.nodes
            for(strategyNodeWithConnectPojo:StrategyNodeWithConnectPojo in strategyNodeWithConnectPojoList){
                val strategyNodeWithConnect:StrategyNodeWithConnect = convertStrategyNodeWithConnectPojo2StrategyNodeWithConnect(strategyNodeWithConnectPojo)
                val fromNode = strategyNodeWithConnect.strategyNode
                //结果节点
                if(fromNode.type == StrategyNodeTypeEnum.result){
                    resultNodes.add(fromNode);
                }else{
                    //一般节点，并且是连接到其他策略的，则加入outerNodeMap
                    val connectList: List<StrategyNodesConnect> = strategyNodeWithConnect.connects
                    for(connect: StrategyNodesConnect in connectList){
                        val toNode = connect.toNode
                        //是否连接到其他策略
                        if(toNode.strategyUuid!=strategyUUID){
                            if(outerNodeMap[fromNode]==null){
                                outerNodeMap[fromNode] = mutableListOf<StrategyNodesConnect>();
                            }
                            val list: MutableList<StrategyNodesConnect>? = outerNodeMap[fromNode]
                            list?.add(connect)
                        }
                    }
                }
            }
            strategyOuterModel.setResultNodes(resultNodes)
            strategyOuterModel.setOuterNodesMap(outerNodeMap)
            return  strategyOuterModel;
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