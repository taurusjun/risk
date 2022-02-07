package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.MyEmployeeDao
import com.mtech.risk.dataio.model.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * This class handles all CRUD (create, Read, Update, Delete Operations) service
 * @author Padmaja Krishna Kumar
 * @version 1.0
 * @since 03 Feb 2021
 **/
@Service
open class EmployeeService(@Autowired private val myEmployeeDao : MyEmployeeDao) {

    fun getLatestEmployee(): Employee? =
            myEmployeeDao.getLatest()

}