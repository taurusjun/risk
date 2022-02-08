package com.mtech.risk.plugin.model;

import lombok.Data;

@Data
public class RuleConditionObject {
    String uuid;
    String logicCode;
    RuleConditionElementObject leftNode;
    RuleConditionOperatorObject operator;
    String rightValue;
}
