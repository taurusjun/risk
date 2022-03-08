package com.mtech.risk.management.bff.model;

import java.util.ArrayList;
import java.util.List;

public class RuleLogicVO {
    String uuid;
    String name;
    String code;
    int categoryId;
    String description;
    String status;
    int version;

    List<RuleGroupVO> ruleGroups = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<RuleGroupVO> getRuleGroups() {
        return ruleGroups;
    }

    public void setRuleGroups(List<RuleGroupVO> ruleGroups) {
        this.ruleGroups = ruleGroups;
    }
}
