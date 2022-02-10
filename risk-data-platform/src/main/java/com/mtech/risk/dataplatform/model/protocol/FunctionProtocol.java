package com.mtech.risk.dataplatform.model.protocol;

import com.mtech.risk.base.model.RiskDataType;
import lombok.Data;

@Data
public class FunctionProtocol implements CalculationProtocol {
    //unique code
    String code;
    //function name, all function input is Map<String, Object>
    String functionName;
    //Number/String/Boolean/List_String
    RiskDataType returnType;
}
