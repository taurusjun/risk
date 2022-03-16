package com.mtech.risk.dataio.model;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Strategy {
    int id;
    String uuid;
    String code;
    String description;

    StrategyNode startNode;
    Map<StrategyNode, StrategyConnect> graph = new ConcurrentHashMap<>();
}
