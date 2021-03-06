package com.mtech.risk.plugin.mvel.impl;

import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.plugin.model.RuleActionObject;
import com.mtech.risk.plugin.model.RuleConditionObject;
import com.mtech.risk.plugin.model.RuleGroupObject;
import com.mtech.risk.plugin.model.RuleObject;
import com.mtech.risk.plugin.mvel.calc.ConstantsKt;
import com.mtech.risk.plugin.mvel.calc.RuleRepository;
import com.mtech.risk.plugin.service.ActionExecutor;
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor;
import com.mtech.risk.plugin.service.RuleConditionCalculator;
import com.mtech.risk.tools.RiskUtils;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MVELRiskRuleScriptExecutor implements RiskRuleScriptExecutor {
    @Autowired
    private RuleRepository ruleRepository;

    private @Autowired
    AutowireCapableBeanFactory beanFactory;

    @Override
    public String compile(RuleObject ruleObject) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.compileRuleLogic(ruleObject));
        sb.append(this.compileRuleAction(ruleObject));
        return sb.toString();
    }

    /**
     * compile rule action
     * @param ruleObject
     * @return
     */
    private String compileRuleAction(RuleObject ruleObject) {
        StringBuilder sb = new StringBuilder();
        StringBuilder trueSB = new StringBuilder();
        StringBuilder falseSB = new StringBuilder();
        for (RuleActionObject ruleActionObj: ruleObject.getRuleActionList()) {
            if(ConstantsKt.ACTION_FLAG_YES.equalsIgnoreCase(ruleActionObj.getFlag())){
                trueSB.append(ruleActionStatements(ruleActionObj));
            }else {
                falseSB.append(ruleActionStatements(ruleActionObj));
            }
        }
        sb.append("if(");
        sb.append(ruleObject.getCode());
        sb.append(")");
        //true branch
        sb.append("{");
        sb.append(trueSB);
        sb.append("}");
        //false branch
        sb.append("else");
        sb.append("{");
        sb.append(falseSB);
        sb.append("}");
        return sb.toString();
    }

    /**
     * return following format:
     * 'actionExecutor.exe(actionName, rawInputParamStr, extraMap);'
     * @param ruleActionObj
     * @return
     */
    private String ruleActionStatements(RuleActionObject ruleActionObj){
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantsKt.ACTION_CLASS_FUNCION);
        //left brace
        sb.append("(");
        //1st param: actionName
        sb.append("\"");
        sb.append(ruleActionObj.getActionCode());
        sb.append("\"");
        sb.append(",");
        //2st param: rawInputParamStr
        sb.append("\"");
        sb.append(ruleActionObj.getParamsValue());
        sb.append("\"");
        sb.append(",");
        //3st param: extra param
        Map<String, String> map = new HashMap<>();
        map.put(ConstantsKt.ACTION_MVEL_EXTARMAP, ruleActionObj.getExtraMap());
        sb.append(RiskUtils.map2MVELString(map));
        //right brace
        sb.append(")");
        //end of line
        sb.append(";");
        return sb.toString();
    }

    /**
     * ???rule?????????????????????
     * @param ruleObject
     * @return
     */
    private String compileRuleLogic(RuleObject ruleObject) {
        StringBuilder sb = new StringBuilder();
        //assignment statement
        sb.append("boolean " + ruleObject.getCode() + "=");
        for(int i=0; i< ruleObject.getRuleGroupList().size(); i++){
            RuleGroupObject ruleGroupObject = ruleObject.getRuleGroupList().get(i);
            //?????????group??????????????????????????????
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
        //end of line
        sb.append(";");
        String script = sb.toString();
        log.info("rule uuid: {} and its compiled script is: {}", ruleObject.getUuid(), script);
        return script;
    }

    private String compileRuleGroup(RuleGroupObject ruleGroupObject){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< ruleGroupObject.getRuleConditionList().size(); i++){
            RuleConditionObject ruleConditionObject = ruleGroupObject.getRuleConditionList().get(i);
            //?????????????????????????????????????????????
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
            map.put(ConstantsKt.LEFT_RETURN_TYPE, ruleConditionObject.getLeftNode().getReturnType());
            map.put(ConstantsKt.LEFT_IDENTIFY_TYPE, ruleConditionObject.getLeftNode().getIdentifyType());
            map.put(ConstantsKt.OPERATOR_CODE, ruleConditionObject.getOperator().getCode());
            sb.append(RiskUtils.map2MVELString(map));
            //right brace
            sb.append(") ");
        }
        return sb.toString();
    }

    @Override
    public boolean execute(EventContext eventContext, String ruleUUID) {
        //load script
        Serializable sScirpt = ruleRepository.findExecutableScript(ruleUUID);
        //param: rule condition
        RuleConditionCalculator ruleConditionCalculator = new MVELRuleConditionCalculator(eventContext);
        beanFactory.autowireBean(ruleConditionCalculator);
        //param: rule action
        ActionExecutor actionExecutor = new MVELRuleActionExecutor(eventContext);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ConstantsKt.CONDITION_CALC_CLASS_INSTANCE, ruleConditionCalculator);
        map.put(ConstantsKt.ACTION_CLASS_INSTANCE, actionExecutor);
        //execute
        MVEL.executeExpression(sScirpt, map);
        return true;
    }
}
