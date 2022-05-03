package com.mtech.risk.management.bff.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class StrategyNodeGraphVO {
    StrategyNodeVO startNode;
    //connectVOMap的key: StrategyNode的code
    Map<String, List<StrategyNodeConnectVO>> connectVOMap;
}
