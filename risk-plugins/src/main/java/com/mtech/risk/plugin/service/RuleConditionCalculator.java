package com.mtech.risk.plugin.service;

import com.mtech.risk.plugin.model.RuleConditionElementObject;
import com.mtech.risk.plugin.model.RuleConditionOperatorObject;

import java.util.Map;

public interface RuleConditionCalculator {
    boolean calc(String leftNode, String operator, String rightValue, Map<String, String> extraMap);
}
