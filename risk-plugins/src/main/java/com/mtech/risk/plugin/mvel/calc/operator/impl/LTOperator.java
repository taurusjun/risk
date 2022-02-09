package com.mtech.risk.plugin.mvel.calc.operator.impl;

import com.mtech.risk.plugin.mvel.calc.operator.Element;
import com.mtech.risk.plugin.mvel.calc.operator.ElementConverter;
import com.mtech.risk.plugin.mvel.calc.operator.MVELOperator;

/**
 * Less Than Operator
 */
public class LTOperator implements MVELOperator {

    @Override
    public boolean eval(Element left, Element right) {
        try{
            Number leftVal = ElementConverter.toNumberVal(left);
            Number rightVal = ElementConverter.toNumberVal(right);
            return leftVal.doubleValue() < rightVal.doubleValue();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
