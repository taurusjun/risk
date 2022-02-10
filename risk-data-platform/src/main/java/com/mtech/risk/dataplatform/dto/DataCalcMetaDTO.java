package com.mtech.risk.dataplatform.dto;

import lombok.Data;

@Data
public class DataCalcMetaDTO {
    //unique code
    String code;
    String name;
    //Number/String/Boolean/List_String
    String returnType;
    //variable
    String identifyType;
}
