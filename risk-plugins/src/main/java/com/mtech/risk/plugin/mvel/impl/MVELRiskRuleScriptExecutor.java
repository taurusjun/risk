package com.mtech.risk.plugin.mvel.impl;

import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.dataio.service.RuleService;
import com.mtech.risk.plugin.model.RuleConditionObject;
import com.mtech.risk.plugin.model.RuleGroupObject;
import com.mtech.risk.plugin.model.RuleObject;
import com.mtech.risk.plugin.mvel.calc.ConstantsKt;
import com.mtech.risk.plugin.mvel.calc.RuleRepository;
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor;
import com.mtech.risk.plugin.service.RuleConditionCalculator;
import com.mtech.risk.tools.RiskUtils;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Service
public class MVELRiskRuleScriptExecutor implements RiskRuleScriptExecutor {
    @Autowired
    private RuleService ruleService;

    @Autowired
    private RuleRepository ruleRepository;

    @Override
    public String compile(RuleObject ruleObject) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< ruleObject.getRuleGroupList().size(); i++){
            RuleGroupObject ruleGroupObject = ruleObject.getRuleGroupList().get(i);
            //第一个group的逻辑符号不参与计算
            if(i!=0){
                String logic = ruleGroupObject.getLogicCode();
                sb.append(RiskUtils.logicConvert(logic));
            }
            //left brace
            sb.append(" (");
            sb.append(this.compileRuleGroup(ruleGroupObject));
            //right brace
            sb.append(") ");
        }

        String script = sb.toString();
        RuleConditionCalculator ruleConditionCalculator = new MVELRuleConditionCalculator();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ruleConditionCalculator", ruleConditionCalculator);
        boolean rslt = MVEL.evalToBoolean(script, map);
//        Serializable ser = MVEL.compileExpression(script);
//        boolean rslt2 = (boolean)MVEL.executeExpression(ser, map);

        return script;
    }

    private String compileRuleGroup(RuleGroupObject ruleGroupObject){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< ruleGroupObject.getRuleConditionList().size(); i++){
            RuleConditionObject ruleConditionObject = ruleGroupObject.getRuleConditionList().get(i);
            //第一个条件的逻辑符号不参与计算
            if(i!=0){
                String logic = ruleConditionObject.getLogicCode();
                sb.append(RiskUtils.logicConvert(logic));
            }
            sb.append(" ");
            sb.append(ConstantsKt.CONDITION_CALC_CLASS_FUNCION);
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
            //4st param: extra param
            Map<String, String> map = new HashMap<>();
            map.put(ConstantsKt.LEFT_IDENTIFY_TYPE, ruleConditionObject.getLeftNode().getIdentifyType());
            map.put(ConstantsKt.OPERATOR_UUID, ruleConditionObject.getOperator().getUuid());
            sb.append(RiskUtils.map2MVELString(map));
            //right brace
            sb.append(") ");

        }

        return sb.toString();
    }

    @Override
    public void execute(EventContext eventContext, String ruleUUID) {
        //load script
        Serializable sScirpt = ruleRepository.findExecutableScript(ruleUUID);
        //param
        RuleConditionCalculator ruleConditionCalculator = new MVELRuleConditionCalculator();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ruleConditionCalculator", ruleConditionCalculator);
        //execute
        boolean rslt2 = (boolean)MVEL.executeExpression(sScirpt, map);
        System.out.println(rslt2);
    }
}
