package com.netcracker.tss.web.router;

import com.netcracker.tss.web.util.ArgumentValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */

public class DefaultActionRouter implements ActionRouter {
    private static final int DEFAULT_ROUTE_AMOUNT = 10;

    private Map<String, Route> routeMap;

    public DefaultActionRouter() {
        this(DEFAULT_ROUTE_AMOUNT);
    }

    public DefaultActionRouter(int routeAmount) {
        if (routeAmount <= 0)
            throw new IllegalArgumentException("Argument 'routeAmount' should be greater than or equal to zero.");
        this.routeMap =  new HashMap<String, Route>(routeAmount);
    }

    @Override
    public Route getRoute(String name) {
        ArgumentValidator.validateArgumentOnNull("name", name);
        return routeMap.get(name);
    }

    @Override
    public void addRoute(String name, Route route) {
        ArgumentValidator.validateArgumentOnNull("name", name);
        ArgumentValidator.validateArgumentOnNull("route", route);
        validateOnRouteDuplicating(name);
        routeMap.put(name, route);
    }

    @Override
    public void addRoute(Route route) {
        ArgumentValidator.validateArgumentOnNull("route", route);
        validateOnRouteDuplicating(route.getRouteName());
        routeMap.put(route.getRouteName(), route);
    }

    private void validateOnRouteDuplicating(String routeName) {
        if (routeMap.get(routeName) != null)
            throw new IllegalArgumentException("Route with route name '" + routeName + "' is exist.");
    }
}
