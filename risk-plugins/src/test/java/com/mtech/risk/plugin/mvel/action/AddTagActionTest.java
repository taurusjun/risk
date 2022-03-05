package com.mtech.risk.plugin.mvel.action;

import com.mtech.risk.base.model.Event;
import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.plugin.mvel.impl.MVELRuleActionExecutor;
import com.mtech.risk.plugin.service.ActionExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AddTagActionTest {

    @Test
    void exeTest(){
        EventContext eventContext = new EventContext(new Event("event1","event1", new HashMap<>()), new ConcurrentHashMap<>());
        ActionExecutor actionExecutor = new MVELRuleActionExecutor(eventContext);
        String actionName = "addTag";
        String paramStr = "[\"a\",\"b\"]";
        actionExecutor.exe(actionName, paramStr, null);
        Map<String, Object> props = eventContext.eventContextToMap();
        List<String> tagList = (List<String>)props.get("tag");
        Assertions.assertTrue(tagList.contains("a"));
        Assertions.assertTrue(tagList.contains("b"));
    }

}
