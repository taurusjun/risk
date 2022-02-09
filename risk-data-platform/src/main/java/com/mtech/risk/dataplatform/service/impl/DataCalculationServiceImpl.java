package com.mtech.risk.dataplatform.service.impl;

import com.mtech.risk.dataplatform.service.DataCalculationService;
import com.mtech.risk.dataplatform.variable.service.VariableCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DataCalculationServiceImpl implements DataCalculationService {
    @Autowired
    private VariableCalculationService variableCalculationService;

    @Override
    public Object calc(String calcCode, String identifyType, String returnType, Map<String, Object> data) {
        switch (identifyType){
            case "variable":
                return variableCalculationService.calc(calcCode, returnType, data);
            default:
                return null;
        }
    }
}
