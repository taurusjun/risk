package com.mtech.risk.dataio.dao

import com.mtech.risk.dataio.model.Tag
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface TagDictDAO {
    @Select("select * from tag_dict")
    fun getAllTags(): List<Tag>?

    @Select("select * from tag_dict where code=#{code}")
    fun getTagDefByCode(code:String): Tag?
}