package com.mtech.risk.management.bff.model;

public class RuleConditionVO {
    String uuid;
    String logicCode;
    int leftId;
    String operatorCode;
    String rightValue;

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

    public int getLeftId() {
        return leftId;
    }

    public void setLeftId(int leftId) {
        this.leftId = leftId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }
}
