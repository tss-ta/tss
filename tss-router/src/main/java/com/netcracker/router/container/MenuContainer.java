package com.netcracker.router.container;

import com.netcracker.router.exception.ActionNotFoundException;
import com.netcracker.router.util.ArgumentValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyrylo Berehovyi
 */

public class MenuContainer {
    private Map<String, ActionContainer> container = new HashMap<>();

    public ActionContainer findOrCreateActionContainer(String menu) {
        ArgumentValidator.validateOnNull(menu, "menu");
        ActionContainer searchedActionContainer = container.get(menu);
        if (searchedActionContainer == null) {
            searchedActionContainer = new ActionContainer();
            container.put(menu, searchedActionContainer);
        }
        return searchedActionContainer;
    }

    public ActionContainer findActionContainer(String menu) {
        ArgumentValidator.validateOnNull(menu, "menu");
        ActionContainer searchedActionContainer = container.get(menu);
        if (searchedActionContainer == null) {
            throw new ActionNotFoundException("No actions found for menu='" + menu + "'.");
        }
        return searchedActionContainer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuContainer{");
        sb.append("container=").append(container);
        sb.append('}');
        return sb.toString();
    }

    public String info() {
        final StringBuilder sb = new StringBuilder("\n");
        for (String menu : container.keySet()) {
            sb.append(container.get(menu).info("Route{menu=" + menu));
        }
        return sb.toString();
    }
}
