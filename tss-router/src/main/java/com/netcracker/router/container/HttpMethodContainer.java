package com.netcracker.router.container;

import com.netcracker.router.HttpMethod;
import com.netcracker.router.exception.HttpMethodNotAllowedException;
import com.netcracker.router.util.ArgumentValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyrylo Berehovyi
 */

public class HttpMethodContainer {
    private Map<HttpMethod, ActionMetaData> container = new HashMap<>();

    public HttpMethodContainer() {}

    public void addInstanceAndMethod(HttpMethod httpMethod, ActionMetaData actionMetaData) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");
        ArgumentValidator.validateOnNull(actionMetaData, "instanceAndMethod");

        ActionMetaData oldActionMetaData = container.get(httpMethod);
        if (oldActionMetaData != null) {
            throw new IllegalArgumentException("Http method '" + httpMethod + "' duplication.");
        }

        container.put(httpMethod, actionMetaData);
    }

    public ActionMetaData findInstanceAndMethod(HttpMethod httpMethod) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");

        ActionMetaData searchedActionMetaData = container.get(httpMethod);
        if (searchedActionMetaData == null) {
            throw new HttpMethodNotAllowedException("Http method '" +
                    httpMethod.getName() + "' not allowed for this action.");
        }

        return searchedActionMetaData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpMethodContainer{");
        sb.append("container=").append(container);
        sb.append('}');
        return sb.toString();
    }

    public String info(String message) {
        final StringBuilder sb = new StringBuilder();
        ActionMetaData actionMetaData = null;
        for (HttpMethod httpMethod : container.keySet()) {
            actionMetaData = container.get(httpMethod);
            sb.append(message)
              .append(" httpMethod=").append(httpMethod.getName())
              .append(" classMethod=").append(actionMetaData.getMethod().getName())
              .append(" class=").append(actionMetaData.getInstance().getClass().getSimpleName())
              .append("}\n");
        }
        return sb.toString();
    }
}
