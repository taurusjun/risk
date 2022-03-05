package com.mtech.risk.plugin.mvel.action;

import java.util.Map;

public interface MVELAction {
    void exe(String rawInputParamStr, Map<String, String> extraMap);
}
