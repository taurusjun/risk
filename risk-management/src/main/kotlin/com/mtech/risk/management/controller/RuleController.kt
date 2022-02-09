package com.mtech.risk.management.controller

import com.mtech.risk.dataio.model.*
import com.mtech.risk.dataio.service.RuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.mtech.risk.management.service.RuleDataMngService

@RestController
class RuleController(@Autowired private val ruleService: RuleService,@Autowired private val ruleDataMngService: RuleDataMngService) {

    @GetMapping("/ruleexe/{uuid}")
    fun ruleExe(@PathVariable uuid : String) =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.mockExe(uuid))

    @GetMapping("/rulecompile/{uuid}")
    fun trigger(@PathVariable uuid : String) =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.compileScript(uuid))

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

    @GetMapping("/ruleconditionoperator/{uuid}")
    fun getRuleConditionOperatorByUuid(@PathVariable uuid : String): ResponseEntity<RuleConditionOperator> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleConditionOperatorByUuid(uuid))

}