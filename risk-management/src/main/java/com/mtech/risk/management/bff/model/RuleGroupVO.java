package com.mtech.risk.management.bff.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RuleGroupVO {
    String uuid;
    String logicCode;

    List<RuleConditionVO> ruleConditions = new ArrayList<>();
}
