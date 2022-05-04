package com.mtech.risk.management.controller.spa

import com.mtech.risk.management.response.Result
import com.mtech.risk.management.service.StrategyDataMngService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/StrategyPage")
class StrategyPageController(@Autowired private val strategyDataMngService: StrategyDataMngService) {
    @GetMapping("/graph/node")
    fun getRuleList(): ResponseEntity<Result<Map<String, Any>>> {
        val strategyNodeGraphVO = strategyDataMngService.strategyNodeGraphView()
        val strategyNodeMap = strategyDataMngService.getAllStrategyNodeVO()

        val map: MutableMap<String, Any> = mutableMapOf()
        if(strategyNodeGraphVO!=null){
            map["StrategyNodeGraph"] = strategyNodeGraphVO
        }
        if(strategyNodeMap!=null){
            map["strategyNodeMap"] = strategyNodeMap
        }

        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(map))
    }
}