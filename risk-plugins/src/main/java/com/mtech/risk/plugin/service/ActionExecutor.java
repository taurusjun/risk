package com.mtech.risk.plugin.service;

import java.util.Map;

/**
 *  Action executor
 *  rule和strategy的动作执行调用同一个接口
 */
public interface ActionExecutor {
    /**
     * Action执行接口，业务上会进一步区分是否rule还是strategy的动作
     * (1) rule的动作，可以调用builtin action里面，是指内建的一些动作
     * (2) strategy的动作，大部分需要调用处罚中心的接口
     * @param actionName 动作名称，映射到具体动作用
     * @param rawInputParamStr 原始输入字符串，建议可以使用json格式，当然最简单的可以是单字符串，由action的具体调用者自己解析
     * @param extraMap 额外信息，扩展用
     */
    void exe(String actionName, String rawInputParamStr, Map<String, String> extraMap);
}
