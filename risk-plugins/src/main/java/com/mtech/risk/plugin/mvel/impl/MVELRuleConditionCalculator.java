package com.mtech.risk.plugin.mvel.impl;

import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.dataplatform.service.DataCalculationService;
import com.mtech.risk.plugin.mvel.calc.ConstantsKt;
import com.mtech.risk.plugin.mvel.calc.operator.Element;
import com.mtech.risk.plugin.mvel.calc.operator.MVELOperators;
import com.mtech.risk.plugin.service.RuleConditionCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class MVELRuleConditionCalculator implements RuleConditionCalculator {
    @Autowired
    private DataCalculationService dataCalculationService;

    private final EventContext eventContext;
    public MVELRuleConditionCalculator(EventContext eventContext) {
        this.eventContext = eventContext;
    }

    @Override
    public boolean calc(String leftNode, String operator, String rightValue, Map<String, String> extraMap) {
        Map<String, Object> dataMap = eventContext.eventContextToMap();
        String leftIdentifyType = extraMap.get(ConstantsKt.LEFT_IDENTIFY_TYPE);
        String leftReturnType = extraMap.get(ConstantsKt.LEFT_RETURN_TYPE);
        //left
        Object leftNodeValue = dataCalculationService.calc(leftNode, leftIdentifyType, leftReturnType, dataMap);
        Element leftEl = new Element(leftNodeValue, leftReturnType);
        //right
        Element rightEl = new Element(rightValue, "String");
        //operator
        MVELOperators operatorEnum = MVELOperators.findOperationByName(operator.toUpperCase());
        return operatorEnum.apply(leftEl, rightEl);
    }

    public EventContext getEventContext() {
        return eventContext;
    }
}
