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
    private Map<HttpMethod, InstanceAndMethod> container = new HashMap<>();

    public HttpMethodContainer() {}

    public void addInstanceAndMethod(HttpMethod httpMethod, InstanceAndMethod instanceAndMethod) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");
        ArgumentValidator.validateOnNull(instanceAndMethod, "instanceAndMethod");

        InstanceAndMethod oldInstanceAndMethod = container.get(httpMethod);
        if (oldInstanceAndMethod != null) {
            throw new IllegalArgumentException("Http method '" + httpMethod + "' duplication.");
        }

        container.put(httpMethod, instanceAndMethod);
    }

    public InstanceAndMethod findInstanceAndMethod(HttpMethod httpMethod) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");

        InstanceAndMethod searchedInstanceAndMethod = container.get(httpMethod);
        if (searchedInstanceAndMethod == null) {
            throw new HttpMethodNotAllowedException("Http method '" +
                    httpMethod.getName() + "' not allowed for this action.");
        }

        return searchedInstanceAndMethod;
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
        InstanceAndMethod instanceAndMethod = null;
        for (HttpMethod httpMethod : container.keySet()) {
            instanceAndMethod = container.get(httpMethod);
            sb.append(message)
              .append(" httpMethod=").append(httpMethod.getName())
              .append(" classMethod=").append(instanceAndMethod.getMethod().getName())
              .append(" class=").append(instanceAndMethod.getInstance().getClass().getSimpleName())
              .append("}\n");
        }
        return sb.toString();
    }
}
