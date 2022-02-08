package com.mtech.risk.plugin.service;

import com.mtech.risk.plugin.model.RuleObject;

public interface RiskRuleScriptExecutor {
    String compile(RuleObject ruleObject);
    String execute();
}
