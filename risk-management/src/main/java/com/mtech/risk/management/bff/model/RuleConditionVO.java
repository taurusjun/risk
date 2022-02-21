package com.mtech.risk.management.bff.model;

import lombok.Data;

@Data
public class RuleConditionVO {
    String uuid;
    String logicCode;
    int leftId;
    String operatorCode;
    String rightValue;
}
