package com.mtech.risk.plugin.mvel.calc.operator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum MVELOperators {
    LT((left, right) -> {
        try{
            Number leftVal = ElementConverter.toNumberVal(left);
            Number rightVal = ElementConverter.toNumberVal(right);
            return leftVal.doubleValue() < rightVal.doubleValue();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }, "LT", "Less than")
    ,
    GT((left, right) -> {
        try{
            Number leftVal = ElementConverter.toNumberVal(left);
            Number rightVal = ElementConverter.toNumberVal(right);
            return leftVal.doubleValue() > rightVal.doubleValue();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }, "GT", "Greater than")
    ,
    CONTAINS((left, right) -> {
        try{
            List<String> leftVal = ElementConverter.toStringListVal(left);
            String rightVal = ElementConverter.toStringVal(right);
            return leftVal.contains(rightVal);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }, "CONTAINS", "Left contains right")
    ,
    ;

    static final Map<String, MVELOperators> innerMap = new HashMap<>();

    static {
        for (MVELOperators op: values()) {
            innerMap.put(op.uniqueName, op);
        }
    }

    public static MVELOperators findOperationByName(String uniqueName) {
        return innerMap.get(uniqueName);
    }

    private final BiFunction<Element, Element, Boolean> operation;
    private final String uniqueName;
    private final String description;

    MVELOperators(BiFunction<Element, Element, Boolean> operation, String uniqueName, String description) {
        this.operation = operation;
        this.uniqueName = uniqueName;
        this.description = description;
    }

    public Boolean apply(Element left, Element right) {
        return operation.apply(left, right);
    }
}
