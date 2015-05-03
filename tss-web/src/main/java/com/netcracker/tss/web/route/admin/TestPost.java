package com.netcracker.tss.web.route.admin;

import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;

/**
 * Created by Kyrylo Berehovyi on 04/05/2015.
 */

@ActionRoute(menu = "test")
public class TestPost {

    @Action(action = "post", httpMethod = HttpMethod.POST)
    public ActionResponse test() {
        ActionResponse actionResponse = new ActionResponse();
        return actionResponse;
    }
}
