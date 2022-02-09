package com.mtech.risk.dataplatform.variable.service.impl;

import com.mtech.risk.dataplatform.variable.service.VariableCalculationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DummyVariableCalculationServiceImpl implements VariableCalculationService {

    @Override
    public Object calc(String calcCode, String returnType, Map<String, Object> data) {
        switch (returnType){
            case "Number": return 1L;
            case "String": return "a";
            case "Boolean": return true;
            case "List_String":
                List<String> list = new ArrayList<>();
                list.add("a");
                list.add("b");
                return list;
            default:
                return null;
        }
    }
}
