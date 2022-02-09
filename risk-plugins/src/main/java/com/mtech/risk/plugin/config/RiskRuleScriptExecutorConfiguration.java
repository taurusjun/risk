package com.mtech.risk.plugin.config;

import com.mtech.risk.plugin.mvel.impl.MVELRiskRuleScriptExecutor;
import com.mtech.risk.plugin.service.RiskRuleScriptExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MVELRiskRuleScriptExecutor.class)
public class RiskRuleScriptExecutorConfiguration {
}
