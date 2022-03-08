package com.mtech.risk.management.controller.spa

import com.mtech.risk.dataio.service.ActionDefService
import com.mtech.risk.dataio.service.RuleService
import com.mtech.risk.management.bff.model.RuleVO
import com.mtech.risk.management.response.Result
import com.mtech.risk.management.service.RuleDataMngService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/RulePage")
class RulePageController(@Autowired private val ruleService: RuleService,
                         @Autowired private val ruleDataMngService: RuleDataMngService,
                         @Autowired private val actionDefService: ActionDefService
                         ) {

    @GetMapping("/list")
    fun getRuleList(): ResponseEntity<Result<List<RuleVO>>> {
        val ruleVOList = ruleDataMngService.ruleVOListQuery()
        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(ruleVOList))
    }

    @GetMapping("/logic")
    fun getLogicInfo(@RequestParam uuid : String): ResponseEntity<Result<Map<String, Any>>> {
        val ruleVO = ruleDataMngService.ruleLogicVOQuery(uuid)
        val variables = ruleService.getAllRuleConditionElements()
        val operators = ruleService.getAllRuleConditionOperators()
        val map: MutableMap<String, Any> = mutableMapOf()
        if (ruleVO != null) {
            map["rule"] = ruleVO
        }
        map["variablesArray"] = variables
        map["operatorsArray"] = operators
        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(map))
    }

    @GetMapping("/action")
    fun getRuleActionInfo(@RequestParam uuid : String): ResponseEntity<Result<Map<String, Any>>> {
        val ruleWithActionsVO = ruleDataMngService.ruleActionVOQuery(uuid)
        val actionDefs = actionDefService.getAllActionDef()
        val map: MutableMap<String, Any> = mutableMapOf()
        map["ruleWithActions"] = ruleWithActionsVO
        if(actionDefs!=null){
            map["actionDefs"] = actionDefs
        }
        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(map))
    }
}
