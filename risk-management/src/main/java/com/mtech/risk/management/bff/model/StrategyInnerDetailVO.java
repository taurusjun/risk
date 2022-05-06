package com.mtech.risk.management.bff.model;

import lombok.Data;

@Data
public class StrategyInnerDetailVO {
    int id;
    String uuid;
    String code;
    String description;

    StrategyNodeGraphVO strategyNodeGraphVO;
}
