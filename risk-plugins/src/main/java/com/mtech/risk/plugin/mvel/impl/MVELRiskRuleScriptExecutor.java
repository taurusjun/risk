package com.mtech.risk.plugin.mvel.impl;

import com.mtech.risk.plugin.model.RuleConditionObject;
import com.mtech.risk.plugin.model.RuleGroupObject;
import com.mtech.risk.plugin.model.RuleObject;
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor;
import com.mtech.risk.plugin.service.RuleConditionCalculator;
import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MVELRiskRuleScriptExecutor implements RiskRuleScriptExecutor {
    private final String CONDITION_CALC_CLASS_FUNCION = "ruleConditionCalculator.calc";

    @Override
    public String compile(RuleObject ruleObject) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< ruleObject.getRuleGroupList().size(); i++){
            RuleGroupObject ruleGroupObject = ruleObject.getRuleGroupList().get(i);
            if(i!=0){
                //第一个group的逻辑符号不参与计算
                String logic = ruleGroupObject.getLogicCode();
                sb.append(logic.equalsIgnoreCase("AND")?"&&":"||");
            }
            //left brace
            sb.append(" (");
            sb.append(this.compileRuleGroup(ruleGroupObject));
            //right brace
            sb.append(") ");
        }

        String script = sb.toString();
        Map<String,Object> map = new HashMap<String,Object>();
        RuleConditionCalculator ruleConditionCalculator = new MVELRuleConditionCalculator();
        map.put("ruleConditionCalculator", ruleConditionCalculator);
        boolean rslt = MVEL.evalToBoolean(script, map);

        return null;
    }

    private String compileRuleGroup(RuleGroupObject ruleGroupObject){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< ruleGroupObject.getRuleConditionList().size(); i++){
            RuleConditionObject ruleConditionObject = ruleGroupObject.getRuleConditionList().get(i);
            if(i!=0){
                //第一个条件的逻辑符号不参与计算
                String logic = ruleConditionObject.getLogicCode();
                sb.append(logic.equalsIgnoreCase("AND")?"&&":"||");
            }
            sb.append(" ");
            sb.append(this.CONDITION_CALC_CLASS_FUNCION);
            //left brace
            sb.append("(");
            //1st param
            sb.append("\"");
            sb.append(ruleConditionObject.getLeftNode().getCode());
            sb.append("\",");
            //2st param
            sb.append("\"");
            sb.append(ruleConditionObject.getOperator().getCode());
            sb.append("\",");
            //3st param
            sb.append("\"");
            sb.append(ruleConditionObject.getRightValue());
            sb.append("\",");
            //
            Map<String, String> map = new HashMap<>();
            map.put("leftIdentifyType", ruleConditionObject.getLeftNode().getIdentifyType());
            map.put("opUUID", ruleConditionObject.getOperator().getUuid());
            sb.append(this.map2String(map));
            //right brace
            sb.append(") ");

        }

        return sb.toString();
    }

    private String map2String(Map<String, String> map){
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

    @Override
    public String execute() {
        return null;
    }

    public static void main(String[] args){
        MVELRiskRuleScriptExecutor scriptExecutor = new MVELRiskRuleScriptExecutor();
        HashMap<String, String> map = new HashMap<>();
        map.put("a","b");
        map.put("c","d");
        scriptExecutor.map2String(map);

    }
}
