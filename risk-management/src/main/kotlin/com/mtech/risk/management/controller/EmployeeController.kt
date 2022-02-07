package com.mtech.risk.management.controller

import com.mtech.risk.dataio.model.Employee
import com.mtech.risk.dataio.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * This class handles all the rest api requests (create, Read, Update, Delete Operations)
 * @author Padmaja Krishna Kumar
 * @version 1.0
 * @since 03 Feb 2021
 **/
@RestController
class EmployeeController(@Autowired private val employeeService : EmployeeService) {

    @GetMapping("/employees/last")
    fun getLatestEmployee(): ResponseEntity<Employee> =
        ResponseEntity.status(HttpStatus.OK)
                    .body(employeeService.getLatestEmployee())
}