package com.netcracker.router.container;

import com.netcracker.router.util.ArgumentValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyrylo Berehovyi
 */

public class ActionContainer {
    private Map<String, HttpMethodContainer> container = new HashMap<>();

    public HttpMethodContainer findOrCreateHttpMethodContainer(String action) {
        ArgumentValidator.validateOnNull(action, "action");

        HttpMethodContainer searchedContainer = container.get(action);
        if (searchedContainer == null) {
            searchedContainer = new HttpMethodContainer();
            container.put(action, searchedContainer);
        }
        return searchedContainer;
    }

    public HttpMethodContainer findHttpMethodContainer(String action) {
        ArgumentValidator.validateOnNull(action, "action");
        
        HttpMethodContainer searchedContainer = container.get(action);
        if (searchedContainer == null) {
            throw new IllegalArgumentException("Action route for action '" + action + "' not found.");
        }
        return searchedContainer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActionContainer{");
        sb.append("container=").append(container);
        sb.append('}');
        return sb.toString();
    }
}
