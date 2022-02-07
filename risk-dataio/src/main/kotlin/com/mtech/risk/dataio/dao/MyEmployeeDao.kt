package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.Employee
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
interface MyEmployeeDao {
    @Select("select * from employee order by wwid desc limit 1")
    fun getLatest(): Employee?
}