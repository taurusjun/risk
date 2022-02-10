package com.mtech.risk.dataplatform.model.protocol;

import com.mtech.risk.base.model.RiskDataType;
import lombok.Data;

@Data
public class ServiceProtocol implements CalculationProtocol {
    //unique code
    String code;
    //full path service name, input is Map<String, Object>
    String serviceName;
    //Number/String/Boolean/List_String
    RiskDataType returnType;
    //service defined in gradle formatter, e.g. com.google.code.gson:gson:2.7
    String importLibInfo;
}
