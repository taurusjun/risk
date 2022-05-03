package com.mtech.risk.dataio.model;

public class StrategyNodesConnect {
    int id;
    String uuid;
    String logic;
    StrategyNode fromNode;
    StrategyNode toNode;

    public StrategyNodesConnect(int id, String uuid, String logic, StrategyNode fromNode, StrategyNode toNode) {
        this.id = id;
        this.uuid = uuid;
        this.logic = logic;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    public StrategyNodesConnect() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    public StrategyNode getFromNode() {
        return fromNode;
    }

    public void setFromNode(StrategyNode fromNode) {
        this.fromNode = fromNode;
    }

    public StrategyNode getToNode() {
        return toNode;
    }

    public void setToNode(StrategyNode toNode) {
        this.toNode = toNode;
    }
}
