package com.mtech.risk.dataio.config

import com.mtech.risk.dataio.service.EmployeeService
import com.mtech.risk.dataio.service.RuleService
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration()
@MapperScan("com.mtech.risk.dataio.dao")
@Import(value=[EmployeeService::class, RuleService::class])
open class DataIOAutoConfiguration