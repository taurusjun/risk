package com.mtech.risk.dataio.model;

import java.util.List;
import java.util.Map;

/**
 * Strategy 内部逻辑模型
 * 使用了图的模型定义
 */
public class StrategyInnerDetail {
    int id;
    String uuid;
    String code;
    String description;

    StrategyNode startNode;
    /**
     * 邻接矩阵
     */
    Map<StrategyNode, List<StrategyNodesConnect>> graph;

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

    public Map<StrategyNode, List<StrategyNodesConnect>> getGraph() {
        return graph;
    }

    public void setGraph(Map<StrategyNode, List<StrategyNodesConnect>> graph) {
        this.graph = graph;
    }
}
