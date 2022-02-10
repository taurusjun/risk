package com.mtech.risk.dataplatform.model.protocol;

import lombok.Data;

@Data
public class IndicatorProtocol implements CalculationProtocol {
    //unique code
    String code;
    /**
     * indicator name
     * input is Map<String, Object>
     * return is Map<String, Object>
     */
    String indicatorName;
}
