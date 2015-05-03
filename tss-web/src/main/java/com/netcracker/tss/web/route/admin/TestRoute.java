package com.netcracker.tss.web.route.admin;

import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.router.DefaultActionRequest;

/**
 * Created by Kyrylo Berehovyi on 03/05/2015.
 */

@ActionRoute(menu = "test")
public class TestRoute {

    @Action(action = "test", httpMethod = HttpMethod.GET)
    public ActionResponse test() {
        ActionResponse actionResponse = new ActionResponse();
        actionResponse.setView("/WEB-INF/views/admin/content/405.jsp");
        return actionResponse;
    }
}
