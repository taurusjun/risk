package com.mtech.risk.plugin.config;

import com.mtech.risk.plugin.mvel.calc.RuleRepository;
import com.mtech.risk.plugin.mvel.impl.MVELRiskRuleScriptExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MVELRiskRuleScriptExecutor.class, RuleRepository.class})
public class RiskRuleScriptExecutorConfiguration {
}
