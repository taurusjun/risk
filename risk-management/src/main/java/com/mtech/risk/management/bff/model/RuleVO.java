package com.mtech.risk.management.bff.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RuleVO {
    String uuid;
    String name;
    String code;
    int categoryId;
    String description;
    String status;
    int version;

    List<RuleGroupVO> ruleGroups = new ArrayList<>();
}
