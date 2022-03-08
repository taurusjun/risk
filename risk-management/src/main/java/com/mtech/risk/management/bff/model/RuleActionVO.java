package com.mtech.risk.management.bff.model;

public class RuleActionVO {
    String uuid;
    String flag;
    String actionCode;
    String paramsValue;
    String extraMap;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getParamsValue() {
        return paramsValue;
    }

    public void setParamsValue(String paramsValue) {
        this.paramsValue = paramsValue;
    }

    public String getExtraMap() {
        return extraMap;
    }

    public void setExtraMap(String extraMap) {
        this.extraMap = extraMap;
    }
}
