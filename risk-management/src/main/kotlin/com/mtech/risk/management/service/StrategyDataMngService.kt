package com.mtech.risk.management.service

import com.mtech.risk.dataio.service.StrategyService
import com.mtech.risk.management.bff.model.StrategyNodeConnectVO
import com.mtech.risk.management.bff.model.StrategyNodeGraphVO
import com.mtech.risk.management.bff.model.StrategyNodeVO
import com.mtech.risk.management.utils.StrategyConvertor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class StrategyDataMngService(@Autowired private val strategyService: StrategyService) {

    fun strategyNodeGraphView(): StrategyNodeGraphVO? {
        val strategyInnerDetailList = strategyService.getAllStrategyInnerDetailByStrategyUUID() ?: return null
        val strategyNodeGraphVO = StrategyNodeGraphVO()
        val connectVOMap = mutableMapOf<String, List<StrategyNodeConnectVO>>()
        for (strategyInnerDetail in strategyInnerDetailList) {
            val startNode = strategyInnerDetail.startNode;
            //取第一个strategyInnerDetail的startNode作为节点图1的startNode
            if (strategyNodeGraphVO.startNode == null) {
                strategyNodeGraphVO.startNode = StrategyConvertor.convertStrategyNodeToVO(startNode)
            }
            val graphVO = StrategyConvertor.convertStrategyNodeGraphToVO(strategyInnerDetail.graph)
            connectVOMap.putAll(graphVO)
        }
        strategyNodeGraphVO.connectVOMap = connectVOMap
        return strategyNodeGraphVO
    }

    fun getAllStrategyNodeVO(): Map<String, StrategyNodeVO>? {
        val strategyNodeList = strategyService.getAllStrategyNodes()?:return null
        val map = mutableMapOf<String, StrategyNodeVO>()
        for (strategyNode in strategyNodeList) {
            val strategyNodeVO = StrategyConvertor.convertStrategyNodeToVO(strategyNode)
            map[strategyNodeVO.code] = strategyNodeVO
        }
        return map
    }
}