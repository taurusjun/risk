package com.mtech.risk.dataio.service

import com.mtech.risk.dataio.dao.TagDictDAO
import com.mtech.risk.dataio.model.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagDictService(@Autowired private val tagDictDAO: TagDictDAO) {
    fun getAllTags(): List<Tag>? =
        tagDictDAO.getAllTags()
}