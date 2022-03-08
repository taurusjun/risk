package com.mtech.risk.management.bff.model;

import java.util.ArrayList;
import java.util.List;

public class RuleWithActionsVO {
    String uuid;
    List<RuleActionVO> ruleIsTrueActions = new ArrayList<>();
    List<RuleActionVO> ruleIsFalseActions = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<RuleActionVO> getRuleIsTrueActions() {
        return ruleIsTrueActions;
    }

    public void setRuleIsTrueActions(List<RuleActionVO> ruleIsTrueActions) {
        this.ruleIsTrueActions = ruleIsTrueActions;
    }

    public List<RuleActionVO> getRuleIsFalseActions() {
        return ruleIsFalseActions;
    }

    public void setRuleIsFalseActions(List<RuleActionVO> ruleIsFalseActions) {
        this.ruleIsFalseActions = ruleIsFalseActions;
    }
}
