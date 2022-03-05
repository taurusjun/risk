package com.mtech.risk.plugin.mvel.calc.operator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public enum MVELOperators {
    EQ((left, right) -> {
        try{
            return left.equals(right);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }, "equals")
    ,
    LT((left, right) -> {
        try{
            Number leftVal = ElementConverter.toNumberVal(left);
            Number rightVal = ElementConverter.toNumberVal(right);
            return leftVal.doubleValue() < rightVal.doubleValue();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }, "Less than")
    ,
    GT((left, right) -> {
        try{
            Number leftVal = ElementConverter.toNumberVal(left);
            Number rightVal = ElementConverter.toNumberVal(right);
            return leftVal.doubleValue() > rightVal.doubleValue();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }, "Greater than")
    ,
    CONTAINS((left, right) -> {
        try{
            List<String> leftVal = ElementConverter.toStringListVal(left);
            String rightVal = ElementConverter.toStringVal(right);
            return leftVal.contains(rightVal);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    },  "Left contains right")
    ,
    ;

    static final Map<String, MVELOperators> innerMap = new HashMap<>();

    static {
        for (MVELOperators op: values()) {
            innerMap.put(op.name(), op);
        }
    }

    public static MVELOperators findOperationByName(String uniqueName) {
        return innerMap.get(uniqueName);
    }

    public static Set<String> nameList() {
        return innerMap.keySet();
    }

    private final BiFunction<Element, Element, Boolean> operation;
    private final String description;

    MVELOperators(BiFunction<Element, Element, Boolean> operation, String description) {
        this.operation = operation;
        this.description = description;
    }

    public Boolean apply(Element left, Element right) {
        return operation.apply(left, right);
    }
}
