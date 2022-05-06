package com.mtech.risk.management.controller.spa

import com.mtech.risk.dataio.service.StrategyService
import com.mtech.risk.management.bff.model.StrategyInnerDetailVO
import com.mtech.risk.management.response.Result
import com.mtech.risk.management.service.StrategyDataMngService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/StrategyPage")
class StrategyPageController(@Autowired private val strategyService: StrategyService,
                             @Autowired private val strategyDataMngService: StrategyDataMngService) {
    @GetMapping("/graph")
    fun getRuleList(): ResponseEntity<Result<Map<String, Any>>> {
        val strategyNodeGraphVO = strategyDataMngService.strategyNodeGraphView()
        val strategyNodeMap = strategyDataMngService.getAllStrategyNodeVO()

        val map: MutableMap<String, Any> = mutableMapOf()
        if(strategyNodeGraphVO!=null){
            map["strategyNodeGraph"] = strategyNodeGraphVO
        }
        if(strategyNodeMap!=null){
            map["strategyNodeMap"] = strategyNodeMap
        }

        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(map))
    }

    @GetMapping("/strategy")
    fun getStrategy(@RequestParam uuid : String): ResponseEntity<Result<StrategyInnerDetailVO>> {
        val strategyInnerDetailVO = strategyDataMngService.getStrategyInnerDetailVOByUUID(uuid)
        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(strategyInnerDetailVO))
    }
}