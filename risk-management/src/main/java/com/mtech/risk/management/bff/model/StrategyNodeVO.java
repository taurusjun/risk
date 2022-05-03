package com.mtech.risk.management.bff.model;

import lombok.Data;

@Data
public class StrategyNodeVO {
    String uuid;
    String code;
    String description;
    String type;
    String strategyUuid;
}
