package com.mtech.risk.plugin.mvel.impl;

import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.plugin.mvel.action.MVELAction;
import com.mtech.risk.plugin.mvel.calc.ConstantsKt;
import com.mtech.risk.plugin.service.ActionExecutor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.Map;

@Slf4j
public class MVELRuleActionExecutor implements ActionExecutor {
    private final EventContext eventContext;

    public MVELRuleActionExecutor(EventContext eventContext) {
        this.eventContext = eventContext;
    }

    @Override
    @SneakyThrows
    public void exe(String actionName, String rawInputParamStr, Map<String, String> extraMap) {
        //将actionName转换为具体的执行类,
        // e.g.: addTag --> com.mtech.risk.plugin.mvel.action.impl.AddTagAction
        String opClassName = ConstantsKt.ACTION_IMPL_PACKAGE+"."+ StringUtils.capitalize(actionName)+ConstantsKt.ACTION_APPENDIX;
        Class<? extends MVELAction> opClass = Class.forName(opClassName).asSubclass(MVELAction.class);
        Constructor constructor = opClass.getDeclaredConstructors()[0];
        MVELAction actionInstance = (MVELAction)constructor.newInstance(eventContext);
        actionInstance.exe(rawInputParamStr, extraMap);
    }
}
