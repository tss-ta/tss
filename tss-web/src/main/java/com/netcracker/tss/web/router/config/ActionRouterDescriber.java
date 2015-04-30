package com.netcracker.tss.web.router.config;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */

public enum ActionRouterDescriber {
    ACTION_ROUTER("carActionRouter"),
    ROUTER_NAME_DESCRIBER_LIST("routerNameDescriberList");

    private String attributeName;

    ActionRouterDescriber(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
