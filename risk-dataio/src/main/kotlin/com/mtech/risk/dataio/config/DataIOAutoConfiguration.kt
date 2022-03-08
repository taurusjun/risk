package com.mtech.risk.dataio.config

import com.mtech.risk.dataio.service.ActionDefService
import com.mtech.risk.dataio.service.RuleService
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration()
@MapperScan("com.mtech.risk.dataio.dao")
@Import(value=[RuleService::class, ActionDefService::class])
open class DataIOAutoConfiguration