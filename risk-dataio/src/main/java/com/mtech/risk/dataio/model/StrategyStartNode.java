package com.mtech.risk.dataio.model;

import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StrategyStartNode extends StrategyNode{
    public StrategyStartNode(int id, String uuid, String code, String description, StrategyNodeTypeEnum type, String strategyUuid) {
        super(id, uuid, code, description, type, strategyUuid);
    }
}
