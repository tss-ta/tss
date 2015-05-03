package com.netcracker.router.container;

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
            throw new IllegalArgumentException("Menu '" + menu + "' not found.");
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
}
