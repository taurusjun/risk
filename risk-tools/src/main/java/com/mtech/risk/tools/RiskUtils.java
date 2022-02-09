package com.mtech.risk.tools;

import java.util.Map;
import java.util.Set;

public class RiskUtils {
    static final String LOGIC_AND = "AND";
    static final String LOGIC_AND_SYMBOL = "&&";

    static final String LOGIC_OR = "OR";
    static final String LOGIC_OR_SYMBOL = "||";

    static final String LOGIC_UNKNOWN = "unknown";
    /**
     * 将map转为mvel2支持的表达形式，比如
     * map{a->b,c->d} ==> ["a":"b", "c":"d"]
     * @param map
     * @return
     */
    public static String map2MVELString(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        Set<String> keys = map.keySet();
        sb.append("[");
        for(String k: keys){
            String val = map.get(k);
            sb.append("\"");
            sb.append(k);
            sb.append("\":");
            sb.append("\"");
            sb.append(val);
            sb.append("\",");
        }
        int len = sb.length();
        if(sb.charAt(len-1)==','){
            sb.deleteCharAt(len-1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static String logicConvert(String logicCode){
        String returnCode = LOGIC_UNKNOWN;
        switch (logicCode){
            case LOGIC_AND:
                returnCode = LOGIC_AND_SYMBOL;
                break;
            case LOGIC_OR:
                returnCode = LOGIC_OR_SYMBOL;
                break;
            default:
                returnCode = LOGIC_UNKNOWN;
                throw new RuntimeException("Cannot recognize logic code: "+logicCode);
        }

        return returnCode;
    }
}
