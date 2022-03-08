package com.mtech.risk.management.controller

import com.mtech.risk.dataio.model.*
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
@RequestMapping("/rule")
class RuleController(@Autowired private val ruleService: RuleService,@Autowired private val ruleDataMngService: RuleDataMngService) {

    @PostMapping("/update")
    fun ruleUpdate(@RequestBody ruleVO: RuleVO) {
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.ruleUpdate(ruleVO))
    }

    @PostMapping("/create")
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

    @GetMapping("/exe/{uuid}")
    fun ruleExe(@PathVariable uuid : String) =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.mockExe(uuid))

    @GetMapping("/compile/{uuid}")
    fun forceCompile(@PathVariable uuid : String) =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleDataMngService.compileScript(uuid))

    @GetMapping("/vo/{uuid}")
    fun getRuleVOByUUID(@PathVariable uuid : String): ResponseEntity<Result<RuleVO>> {
        val ruleVO = ruleDataMngService.ruleVOQuery(uuid)
        return ResponseEntity.status(HttpStatus.OK).body(Result.ok(ruleVO))
    }

    @GetMapping("/logic/{uuid}")
    fun getRuleLogicByUUID(@PathVariable uuid : String): ResponseEntity<RuleLogic> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleLogic(uuid))

    @GetMapping("/complete/{uuid}")
    fun getFullRuleByUUID(@PathVariable uuid : String): ResponseEntity<Rule> =
        ResponseEntity.status(HttpStatus.OK)
                    .body(ruleService.getCompleteRule(uuid))

    @GetMapping("/group/{uuid}")
    fun getRuleGroupByUUID(@PathVariable uuid : String): ResponseEntity<RuleGroup> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleGroupByUUID(uuid))

    @GetMapping("/groupsOfrule/{uuid}")
    fun getRuleGroupByRuleUUID(@PathVariable uuid : String): ResponseEntity<List<RuleGroup>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleGroupsByRuleUUID(uuid))

    @GetMapping("/conditionOfgroup/{uuid}")
    fun getRuleConditionByRuleGroupUUID(@PathVariable uuid : String): ResponseEntity<RuleCondition> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleConditionByRuleGroupUUID(uuid))

    @GetMapping("/conditionelement/{id}")
    fun getRuleConditionElementByUuid(@PathVariable id : String): ResponseEntity<RuleConditionElement> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleConditionElementById(id))

    @GetMapping("/actionOfrule/{uuid}")
    fun getRuleActionListByRuleUUID(@PathVariable uuid : String): ResponseEntity<List<RuleAction>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(ruleService.getRuleActionListByUUID(uuid))

//    @GetMapping("/ruleconditionoperator/{uuid}")
//    fun getRuleConditionOperatorByUuid(@PathVariable uuid : String): ResponseEntity<RuleConditionOperator> =
//        ResponseEntity.status(HttpStatus.OK)
//            .body(ruleService.getRuleConditionOperatorByUuid(uuid))

}