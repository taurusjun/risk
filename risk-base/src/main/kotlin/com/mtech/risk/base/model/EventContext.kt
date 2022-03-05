package com.mtech.risk.base.model

import java.util.concurrent.ConcurrentHashMap

/**
 * 事件上下文
 */
class EventContext(
    //event不允许修改，额外数据添加到extendInfo
    val event:Event,
    //这个属性用来做额外数据传递
    val extendInfo:ConcurrentHashMap<String, Any>){

    /**
     * 将属性打平转成map
     * 如果event和extendInfo中属性key相同，event会覆盖extendInfo中的值
     */
    open fun eventContextToMap(): ConcurrentHashMap<String, Any>{
        val dataMap: ConcurrentHashMap<String, Any> = ConcurrentHashMap();
        //extendInfo
        dataMap.putAll(extendInfo)
        //event
        val (code, name) = event
        dataMap["eventCode"] = code
        dataMap["eventName"] = name
        dataMap.putAll(event.properties)
        return dataMap
    }

    /**
     *  增加额外属性
     */
    open fun addExtraProps(key: String, value: Any){
        extendInfo[key] = value
    }
}