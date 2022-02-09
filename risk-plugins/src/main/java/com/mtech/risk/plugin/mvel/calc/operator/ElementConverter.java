package com.mtech.risk.plugin.mvel.calc.operator;

import com.google.gson.Gson;
import com.mtech.risk.plugin.mvel.calc.ConstantsKt;

import java.util.ArrayList;
import java.util.List;

public class ElementConverter {
    public static Number toNumberVal(Element el){
        try {
            if (el.type.equals(ConstantsKt.TYPE_NUMBER)){
                String theType = "java.lang.Number";
                Class<? extends Number> theClass = Class.forName(theType).asSubclass(Number.class);
                Number num = theClass.cast(el.value);
                return num;
            }else if(el.type.equals(ConstantsKt.TYPE_STRING)){
                return Double.valueOf(el.value.toString());
            }else {
                throw new RuntimeException("Cannot convert element to List<String> value: "+el.toString());
            }
        }catch (Exception e){
            throw new RuntimeException("Error:"+e.getMessage()+" ,cannot convert element to number value: "+el.toString());
        }
    }

    public static Boolean toBooleanVal(Element el){
        try {
            return Boolean.valueOf(el.value.toString());
        }catch (Exception e){
            throw new RuntimeException("Error:"+e.getMessage()+" ,cannot convert element to boolean value: "+el.toString());
        }
    }

    public static String toStringVal(Element el){
        return el.value.toString();
    }

    public static List<String> toStringListVal(Element el){
        try {
            if(el.type.equals(ConstantsKt.TYPE_STRING)){
                Gson gson = new Gson();
                return gson.fromJson(el.value.toString(), ArrayList.class);
            }else if(el.type.equals(ConstantsKt.TYPE_STR_LIST)){
                return (List<String>) el.value;
            }else{
                throw new RuntimeException("Cannot convert element to List<String> value: "+el.toString());
            }
        }catch (Exception e){
            throw new RuntimeException("Error:"+e.getMessage()+" ,cannot convert element to List<String> value: "+el.toString());
        }
    }
}
