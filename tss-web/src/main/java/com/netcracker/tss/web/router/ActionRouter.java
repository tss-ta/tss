package com.netcracker.tss.web.router;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */
public interface ActionRouter {
    Route getRoute(String name);
    void addRoute(String name, Route route);
    void addRoute(Route route);
}
