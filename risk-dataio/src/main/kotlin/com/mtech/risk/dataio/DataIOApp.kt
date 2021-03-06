package com.mtech.risk.dataio

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@MapperScan("com.mtech.risk.dataio.dao")
@SpringBootApplication
open class DataIOApp

fun main(args: Array<String>) {
    runApplication<DataIOApp>(*args)
}