package com.mtech.risk.dataplatform.config;

import com.mtech.risk.dataplatform.service.impl.DataCalculationServiceImpl;
import com.mtech.risk.dataplatform.variable.service.impl.DummyVariableCalculationServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataCalculationServiceImpl.class, DummyVariableCalculationServiceImpl.class})
public class RiskDataplatformConfiguration {
}
