package com.mtech.risk.plugin.mvel.calc.operator;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Element {
    Object value;
    String type;

    public Element(Object value, String type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        Element el = (Element) obj;
        return  StringUtils.equals(this.type, el.type) && this.value.equals(el.value);
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
