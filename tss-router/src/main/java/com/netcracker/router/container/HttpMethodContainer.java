package com.netcracker.router.container;

import com.netcracker.router.HttpMethod;
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
            throw new UnsupportedOperationException("Http method '" + httpMethod + "' not supported.");
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
}
