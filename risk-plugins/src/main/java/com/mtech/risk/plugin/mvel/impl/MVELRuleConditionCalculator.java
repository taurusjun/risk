package com.mtech.risk.plugin.mvel.impl;

import com.mtech.risk.plugin.model.RuleConditionElementObject;
import com.mtech.risk.plugin.model.RuleConditionOperatorObject;
import com.mtech.risk.plugin.service.RuleConditionCalculator;

import java.util.Map;

public class MVELRuleConditionCalculator implements RuleConditionCalculator {

    @Override
    public boolean calc(String leftNode, String operator, String rightValue, Map<String, String> extraMap) {
        return false;
    }
}
