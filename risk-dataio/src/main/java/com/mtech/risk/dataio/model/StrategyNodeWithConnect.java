package com.mtech.risk.dataio.model;

import java.util.List;

public class StrategyNodeWithConnect {
    StrategyNode strategyNode;
    List<StrategyConnect> connects;

    public StrategyNode getStrategyNode() {
        return strategyNode;
    }

    public void setStrategyNode(StrategyNode strategyNode) {
        this.strategyNode = strategyNode;
    }

    public List<StrategyConnect> getConnects() {
        return connects;
    }

    public void setConnects(List<StrategyConnect> connects) {
        this.connects = connects;
    }
}
