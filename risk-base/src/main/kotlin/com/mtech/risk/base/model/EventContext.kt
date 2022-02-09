package com.mtech.risk.base.model

/**
 * 传递数据的上下文
 */
data class EventContext(
    //event不允许修改，额外数据添加到extendInfo
    val event:Event,
    //这个属性用来做额外数据传递
    val extendInfo:Map<String, Object>
)
