package com.netcracker.tss.web.route.admin;

import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kyrylo Berehovyi on 04/05/2015.
 */

@ActionRoute(menu = "test")
public class TestPost {

    @Action(action = "post", httpMethod = HttpMethod.POST)
    public ActionResponse testPost(HttpServletRequest request) {
        ActionResponse actionResponse = new ActionResponse();
        return actionResponse;
    }

    @Action(action = "throw", httpMethod = HttpMethod.GET)
    public ActionResponse testThrowException(HttpServletRequest request) {
        if (1 == 1) {
            System.out.println("before throw.");
            throw new RuntimeException("Test RT exception ;)");
        }
        return new ActionResponse();
    }
}
