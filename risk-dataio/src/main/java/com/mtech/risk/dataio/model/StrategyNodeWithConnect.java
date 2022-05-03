package com.mtech.risk.dataio.model;

import java.util.List;

public class StrategyNodeWithConnect {
    StrategyNode strategyNode;
    List<StrategyNodesConnect> connects;

    public StrategyNode getStrategyNode() {
        return strategyNode;
    }

    public void setStrategyNode(StrategyNode strategyNode) {
        this.strategyNode = strategyNode;
    }

    public List<StrategyNodesConnect> getConnects() {
        return connects;
    }

    public void setConnects(List<StrategyNodesConnect> connects) {
        this.connects = connects;
    }
}
