package com.netcracker.tss.web.route.admin;

import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kyrylo Berehovyi on 03/05/2015.
 */

@ActionRoute(menu = "test")
public class TestRoute {

    @Action(action = "test", httpMethod = HttpMethod.GET)
    public ActionResponse testGet(HttpServletRequest req) {
        System.out.println("--------URI: " + req.getRequestURI());
        ActionResponse actionResponse = new ActionResponse();
        actionResponse.setPageContent("/WEB-INF/views/admin/content/testPage.jsp");
        return actionResponse;
    }
}
