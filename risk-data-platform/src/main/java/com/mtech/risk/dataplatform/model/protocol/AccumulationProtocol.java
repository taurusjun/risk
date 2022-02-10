package com.mtech.risk.dataplatform.model.protocol;

import com.mtech.risk.dataplatform.model.operator.CalcOperator;
import lombok.Data;

import java.util.Map;

@Data
public class AccumulationProtocol implements CalculationProtocol {
    //unique code
    String code;
    //窗口长度，以秒计数
    long secondsWindow;
    //累积主体
    String main;
    //客体和算子
    Map<String, CalcOperator> object;
}
