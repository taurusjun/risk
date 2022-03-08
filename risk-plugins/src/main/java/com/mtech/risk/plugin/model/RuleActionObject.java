package com.mtech.risk.plugin.model;

import lombok.Data;

@Data
public class RuleActionObject {
    String uuid;
    String ruleUUID;
    String flag;
    String actionCode;
    String paramsValue;
    String extraMap;
}
