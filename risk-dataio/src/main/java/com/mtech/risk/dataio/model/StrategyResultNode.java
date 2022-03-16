package com.mtech.risk.dataio.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StrategyResultNode extends StrategyNode{
    StrategyResultEnum result;

    public StrategyResultNode(int id, String uuid, String code, String description, StrategyNodeTypeEnum type, String strategyUuid, StrategyResultEnum result) {
        super(id, uuid, code, description, type, strategyUuid);
        this.result = result;
    }
}
