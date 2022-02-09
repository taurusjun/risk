package com.mtech.risk.plugin.service;

import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.plugin.model.RuleObject;

public interface RiskRuleScriptExecutor {
    String compile(RuleObject ruleObject);
    void execute(EventContext eventContext, String ruleUUID);
}
