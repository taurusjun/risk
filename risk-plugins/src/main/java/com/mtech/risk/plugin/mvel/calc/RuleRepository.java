package com.mtech.risk.plugin.mvel.calc;

import com.mtech.risk.dataio.model.RuleCompiledScript;
import com.mtech.risk.dataio.service.RuleService;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RuleRepository {
    @Autowired
    private RuleService ruleService;

    private Map<String, Serializable> rule2scriptMap = new HashMap<>();
    @PostConstruct
    public void load(){
        List<RuleCompiledScript> ruleCompiledScripts = ruleService.getAllRuleCompiledScripts();
        for(RuleCompiledScript script: ruleCompiledScripts){
            String ruleUUId = script.getRuleUUID();
            String ruleScript = script.getScript();
            Serializable sScript = MVEL.compileExpression(ruleScript);
            this.rule2scriptMap.put(ruleUUId, sScript);
        }
    }

    public Serializable findExecutableScript(String ruleUUID){
        return this.rule2scriptMap.get(ruleUUID);
    }
}
