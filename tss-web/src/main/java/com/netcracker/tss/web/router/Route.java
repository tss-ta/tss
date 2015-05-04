package com.netcracker.tss.web.router;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */
public interface Route {
    ActionRequest action(HttpServletRequest request);
    String getRouteName();
}
