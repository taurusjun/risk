package com.mtech.risk.dataplatform.variable.service;

import java.util.Map;

public interface VariableCalculationService {
    Object calc(String calcCode, String returnType, Map<String, Object> data);
}
