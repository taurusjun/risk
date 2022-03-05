package com.mtech.risk.plugin.mvel.action.impl;

import com.mtech.risk.base.model.EventContext;
import com.mtech.risk.plugin.mvel.action.MVELAction;

public abstract class AbstractAction implements MVELAction {
    protected final EventContext eventContext;

    protected AbstractAction(EventContext eventContext) {
        this.eventContext = eventContext;
    }
}
