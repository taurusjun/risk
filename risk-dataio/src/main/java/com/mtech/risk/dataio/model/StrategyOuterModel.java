package com.mtech.risk.dataio.model;

import java.util.List;
import java.util.Map;

/**
 * Strategy 外部模型
 * 定义输入和输出
 */
public class StrategyOuterModel {
    int id;
    String uuid;
    String code;
    String description;

    //入节点，唯一
    StrategyNode startNode;
    //结果节点列表，多个
    List<StrategyNode> resultNodes;
    //出节点，到别的策略的
    Map<StrategyNode, List<StrategyNodesConnect>> outerNodeMap;

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

    public List<StrategyNode> getResultNodes() {
        return resultNodes;
    }

    public void setResultNodes(List<StrategyNode> resultNodes) {
        this.resultNodes = resultNodes;
    }

    public Map<StrategyNode, List<StrategyNodesConnect>> getOuterNodeMap() {
        return outerNodeMap;
    }

    public void setOuterNodeMap(Map<StrategyNode, List<StrategyNodesConnect>> outerNodeMap) {
        this.outerNodeMap = outerNodeMap;
    }
}
