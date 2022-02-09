package com.mtech.risk.plugin.service;

import java.util.Map;

public interface RuleConditionCalculator {
    boolean calc(String leftNode, String operator, String rightValue, Map<String, String> extraMap);
}
