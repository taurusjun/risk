package com.mtech.risk.management.bff.model;

import java.util.ArrayList;
import java.util.List;

public class RuleWithActionsVO {
    String uuid;
    List<RuleActionVO> ruleActionVOList = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<RuleActionVO> getRuleActionVOList() {
        return ruleActionVOList;
    }

    public void setRuleActionVOList(List<RuleActionVO> ruleActionVOList) {
        this.ruleActionVOList = ruleActionVOList;
    }
}
