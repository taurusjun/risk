package com.mtech.risk.dataio.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StrategyCommonNode extends StrategyNode{
    int weight;
    String ruleUUID;

    public StrategyCommonNode(int id, String uuid, String code, String description, StrategyNodeTypeEnum type, String strategyUuid, int weight, String ruleUUID) {
        super(id, uuid, code, description, type, strategyUuid);
        this.weight = weight;
        this.ruleUUID = ruleUUID;
    }
}
