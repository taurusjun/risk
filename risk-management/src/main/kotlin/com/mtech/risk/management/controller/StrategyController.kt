package com.mtech.risk.management.controller

import com.mtech.risk.dataio.model.*
import com.mtech.risk.dataio.service.StrategyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/strategy")
class StrategyController(@Autowired private val strategyService: StrategyService) {
    @GetMapping("/nodes/{strategyUUID}")
    fun getStrategyNodeListByStrategyUUID(@PathVariable strategyUUID : String): ResponseEntity<List<StrategyNode>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyNodesByStrategyUUID(strategyUUID))

    @GetMapping("/mockconnects")
    fun getMockStrategyNodeListByStrategyUUID(): ResponseEntity<List<StrategyNodeConnectPojo>> {
        val uuidSet = mutableSetOf<String>()
        uuidSet.add("f049ff84-a3af-11ec-b909-0242ac120002")
        uuidSet.add("82d63dad-d7d7-4c55-a7c6-211cfc4e67a6")
        return ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyNodeConnectsPojoByFromNodeUUID(uuidSet))
    }

    @PostMapping("/connects")
    fun getStrategyNodeListByStrategyUUID(@RequestBody uuidSet: Set<String>): ResponseEntity<List<StrategyNodeConnectPojo>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyNodeConnectsPojoByFromNodeUUID(uuidSet))
    }

    @GetMapping("/nodesWithConnect/{strategyUUID}")
    fun getStrategyNodesAndConnectByStrategyUUID(@PathVariable strategyUUID : String): ResponseEntity<List<StrategyNodeWithConnectPojo>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyNodesAndConnectPojoByStrategyUUID(strategyUUID))

    @GetMapping("/strategyWithnodesAndConnects/{strategyUUID}")
    fun getSingleStrategyWithNodesAndConnectByUUID(@PathVariable strategyUUID : String): ResponseEntity<StrategyWithNodesAndConnectPojo> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getSingleStrategyWithNodesAndConnectPojoByUUID(strategyUUID))

    @GetMapping("/strategyInnerDetail/{strategyUUID}")
    fun getStrategyInnerDetailByStrategyUUID(@PathVariable strategyUUID : String): ResponseEntity<StrategyInnerDetail> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyInnerDetailByStrategyUUID(strategyUUID))

    @GetMapping("/strategyOuterModel/{strategyUUID}")
    fun getStrategyOuterModelByStrategyUUID(@PathVariable strategyUUID : String): ResponseEntity<StrategyOuterModel> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyOuterModelByStrategyUUID(strategyUUID))

    @GetMapping("/strategyConnects/{strategyUUID}")
    fun getStrategyConnectsByUUID(@PathVariable strategyUUID : String): ResponseEntity<List<StrategyConnectPojo>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyConnectsByUUID(strategyUUID))

    @GetMapping("/strategyWithConnects/{strategyUUID}")
    fun getStrategyWithConnectsByUUID(@PathVariable strategyUUID : String): ResponseEntity<StrategyWithConnectsPojo> =
        ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyWithConnectsByUUID(strategyUUID))
}