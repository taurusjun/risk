package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.ActionDefDAO
import com.mtech.risk.dataio.model.ActionDef
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActionDefService(@Autowired private val actionDefDAO: ActionDefDAO) {
    fun getAllActionDef(): List<ActionDef>? =
        actionDefDAO.getAllActionDef()
}