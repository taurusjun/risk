package com.mtech.risk.management.bff.model;

import java.util.ArrayList;
import java.util.List;

public class RuleGroupVO {
    String uuid;
    String logicCode;

    List<RuleConditionVO> ruleConditions = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLogicCode() {
        return logicCode;
    }

    public void setLogicCode(String logicCode) {
        this.logicCode = logicCode;
    }

    public List<RuleConditionVO> getRuleConditions() {
        return ruleConditions;
    }

    public void setRuleConditions(List<RuleConditionVO> ruleConditions) {
        this.ruleConditions = ruleConditions;
    }
}
