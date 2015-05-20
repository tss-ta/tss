package com.netcracker.router.container;

import com.netcracker.router.HttpMethod;

/**
 * @author Kyrylo Berehovyi
 */

public class ActionInfo {
    private String menu;
    private String action;
    private HttpMethod method;
    private ActionMetaData actionMetaData;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public ActionMetaData getActionMetaData() {
        return actionMetaData;
    }

    public void setActionMetaData(ActionMetaData actionMetaData) {
        this.actionMetaData = actionMetaData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetaAction{");
        sb.append("menu='").append(menu).append('\'');
        sb.append(", action='").append(action).append('\'');
        sb.append(", method=").append(method);
        sb.append(", instanceAndMethod=").append(actionMetaData);
        sb.append('}');
        return sb.toString();
    }
}
