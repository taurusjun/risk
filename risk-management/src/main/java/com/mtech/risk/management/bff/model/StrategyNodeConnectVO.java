package com.mtech.risk.management.bff.model;

import lombok.Data;

@Data
public class StrategyNodeConnectVO {
    StrategyNodeVO fromNode;
    StrategyNodeVO toNode;
    String logic;
}
