package com.mtech.risk.dataio.model;

import java.util.List;
import java.util.Map;

public class StrategyComplete {
    int id;
    String uuid;
    String code;
    String description;

    StrategyNode startNode;
    /**
     * 邻接矩阵
     */
    Map<StrategyNode, List<StrategyNode>> graph;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StrategyNode getStartNode() {
        return startNode;
    }

    public void setStartNode(StrategyNode startNode) {
        this.startNode = startNode;
    }

    public Map<StrategyNode, List<StrategyNode>> getGraph() {
        return graph;
    }

    public void setGraph(Map<StrategyNode, List<StrategyNode>> graph) {
        this.graph = graph;
    }
}
