package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.ActionDef
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ActionDefDAO {
    @Select("select * from action_dict")
    fun getAllActionDef(): List<ActionDef>?

    @Select("select * from action_dict where code=#{code}")
    fun getActionDefByCode(code:String): ActionDef?
}