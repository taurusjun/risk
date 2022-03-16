package com.mtech.risk.dataio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyNode {
    int id;
    String uuid;
    String code;
    String description;
    StrategyNodeTypeEnum type;
    String strategyUuid;
}
