package com.mtech.risk.management.controller

import com.mtech.risk.dataio.model.*
import com.mtech.risk.dataio.service.RuleService
import com.mtech.risk.management.bff.model.RuleVO
import com.mtech.risk.management.response.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.mtech.risk.management.service.RuleDataMngService

@RestController
@CrossOrigin
class RuleController(@Autowired private val ruleService: RuleService,@Autowired private val ruleDataMngService: RuleDataMngService) {

    @PostMapping("/rulechange")
    fun ruleUpdate(@RequestBody ruleVO: RuleVO) {
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.ruleUpdate(ruleVO))
    }

    @PostMapping("/rulenew")
    fun rulInsert(@RequestBody ruleVO: RuleVO) {
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.ruleInsert(ruleVO))
    }

//    @GetMapping("/ruleupdate/{uuid}")
//    fun ruleUpdate(@PathVariable uuid : String) {
//        val rule = ruleService.getRule(uuid);
//        ResponseEntity.status(HttpStatus.OK)
//            .body(ruleDataMngService.ruleUpdate(rule))
//    }

    @GetMapping("/ruleexe/{uuid}")
    fun ruleExe(@PathVariable uuid : String) =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.mockExe(uuid))

    @GetMapping("/rulecompile/{uuid}")
    fun trigger(@PathVariable uuid : String) =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.compileScript(uuid))

    @GetMapping("/ruleedit")
    fun getRuleEditInfo(@RequestParam uuid : String): ResponseEntity<Result<Map<String, Any>>> {
        val ruleVO = ruleDataMngService.ruleVOQuery(uuid)
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

    @GetMapping("/rulevo/{uuid}")
    fun getRuleVOByUUID(@PathVariable uuid : String): ResponseEntity<Result<RuleVO>> {
        val ruleVO = ruleDataMngService.ruleVOQuery(uuid)
        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(ruleVO))
    }

    @GetMapping("/rule/{uuid}")
    fun getRuleByUUID(@PathVariable uuid : String): ResponseEntity<Rule> =
        ResponseEntity.status(HttpStatus.OK)
                    .body(ruleService.getRule(uuid))

    @GetMapping("/rulegroup/{uuid}")
    fun getRuleGroupByUUID(@PathVariable uuid : String): ResponseEntity<RuleGroup> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleGroupByUUID(uuid))

    @GetMapping("/rulegroup/rule/{uuid}")
    fun getRuleGroupByRuleUUID(@PathVariable uuid : String): ResponseEntity<List<RuleGroup>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleGroupByRuleUUID(uuid))

    @GetMapping("/rulecondition/rulegroup/{uuid}")
    fun getRuleConditionByRuleGroupUUID(@PathVariable uuid : String): ResponseEntity<RuleCondition> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleConditionByRuleGroupUUID(uuid))

    @GetMapping("/ruleconditionelement/{id}")
    fun getRuleConditionElementByUuid(@PathVariable id : String): ResponseEntity<RuleConditionElement> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleConditionElementById(id))

//    @GetMapping("/ruleconditionoperator/{uuid}")
//    fun getRuleConditionOperatorByUuid(@PathVariable uuid : String): ResponseEntity<RuleConditionOperator> =
//        ResponseEntity.status(HttpStatus.OK)
//            .body(ruleService.getRuleConditionOperatorByUuid(uuid))

}