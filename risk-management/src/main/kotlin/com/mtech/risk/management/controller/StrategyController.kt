package com.mtech.risk.management.controller

import com.mtech.risk.dataio.model.StrategyConnect
import com.mtech.risk.dataio.model.StrategyNode
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
    fun getMockStrategyNodeListByStrategyUUID(): ResponseEntity<List<StrategyConnect>> {
        val uuidSet = mutableSetOf<String>()
        uuidSet.add("f049ff84-a3af-11ec-b909-0242ac120002")
        uuidSet.add("82d63dad-d7d7-4c55-a7c6-211cfc4e67a6")
        return ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyConnectsByFromNodeUUID(uuidSet))
    }

    @PostMapping("/connects")
    fun getStrategyNodeListByStrategyUUID(@RequestBody uuidSet: Set<String>): ResponseEntity<List<StrategyConnect>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(strategyService.getStrategyConnectsByFromNodeUUID(uuidSet))
    }
}