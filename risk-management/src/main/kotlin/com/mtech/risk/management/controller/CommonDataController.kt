package com.mtech.risk.management.controller

import com.mtech.risk.dataio.service.ActionDefService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/data")
class CommonDataController(@Autowired private val actionDefService: ActionDefService) {
    @GetMapping("/actionDef/all")
    fun ruleExe() =
        ResponseEntity.status(HttpStatus.OK)
            .body(actionDefService.getAllActionDef())
}