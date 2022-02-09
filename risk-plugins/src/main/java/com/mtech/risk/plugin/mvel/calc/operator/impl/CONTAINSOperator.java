package com.mtech.risk.plugin.mvel.calc.operator.impl;

import com.mtech.risk.plugin.mvel.calc.operator.Element;
import com.mtech.risk.plugin.mvel.calc.operator.ElementConverter;
import com.mtech.risk.plugin.mvel.calc.operator.MVELOperator;

import java.util.List;

/**
 * Contains Operator
 */
public class CONTAINSOperator implements MVELOperator {

    @Override
    public boolean eval(Element left, Element right) {
        try{
            List<String> leftVal = ElementConverter.toStringListVal(left);
            String rightVal = ElementConverter.toStringVal(right);
            return leftVal.contains(rightVal);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
