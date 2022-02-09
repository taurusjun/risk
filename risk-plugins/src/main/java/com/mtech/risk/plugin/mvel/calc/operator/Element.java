package com.mtech.risk.plugin.mvel.calc.operator;

import lombok.Data;

@Data
public class Element {
    Object value;
    String type;

    public Element(Object value, String type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
