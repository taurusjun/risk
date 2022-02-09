package com.mtech.risk.dataplatform.service;

import java.util.Map;

/**
 * 数据平台计算入口
 * @param
 */
public interface DataCalculationService {
    /**
     *
     * @param calcCode 计算用的code，比如指代具体的某个变量
     * @param identifyType 区分是变量还是其他
     * @param returnType 返回类型
     * @param data 计算用到的数据
     * @return
     */
    public Object calc(String calcCode, String identifyType, String returnType, Map<String, Object> data);
}
