package com.mtech.risk.dataio.config

import com.mtech.risk.dataio.service.EmployeeService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration()
@Import(value=[EmployeeService::class])
open class DataIOAutoConfiguration