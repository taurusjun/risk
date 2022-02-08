package com.mtech.risk.plugin.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RuleGroupObject {
    String uuid;
    String logicCode;
    List<RuleConditionObject> ruleConditionList = new ArrayList<>();
}
