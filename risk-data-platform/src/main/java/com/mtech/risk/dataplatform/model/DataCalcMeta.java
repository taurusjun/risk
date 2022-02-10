package com.mtech.risk.dataplatform.model;

import com.mtech.risk.base.model.RiskDataCalcType;
import com.mtech.risk.base.model.RiskDataType;
import com.mtech.risk.dataplatform.model.protocol.CalculationProtocol;
import lombok.Data;

@Data
public class DataCalcMeta {
    //unique code
    String code;
    String name;
    //Number/String/Boolean/List_String
    RiskDataType returnType;
    //variable
    RiskDataCalcType identifyType;
    //function/indicator/service/accumulation
    CalculationProtocol calculationProtocol;
}
