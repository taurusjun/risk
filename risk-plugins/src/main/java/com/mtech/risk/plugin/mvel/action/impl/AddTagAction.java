package com.mtech.risk.plugin.mvel.action.impl;

import com.google.gson.Gson;
import com.mtech.risk.base.model.EventContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 在事件上下文中增加tag信息，会改变eventContext中extendInfo的值
 * 本类的exe方法是有副作用的(side effect)
 */
@Slf4j
public class AddTagAction extends AbstractAction {

    private final static String TAG = "tag";

    public AddTagAction(EventContext eventContext) {
        super(eventContext);
    }

    /**
     * 本方法是有副作用(side effect)
     * 改变eventContext中extendInfo的值
     * @param rawInputParamStr 输入参数，应该是list转成的string，比如["a", "b"]
     * @param extraMap 额外信息，扩展用的，本次用不到
     */
    @Override
    @SneakyThrows
    public void exe(String rawInputParamStr, Map<String, String> extraMap) {
        Gson gson = new Gson();
        //tag 的入参应该是["a", "b"], 转成list
        List<String> paramList = gson.fromJson(rawInputParamStr, ArrayList.class);
        //将额外tag信息加入event上下文
        this.eventContext.addExtraProps(TAG, paramList);
    }
}
