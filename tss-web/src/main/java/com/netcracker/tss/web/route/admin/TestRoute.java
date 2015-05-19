package com.netcracker.tss.web.route.admin;

import com.netcracker.entity.User;
import com.netcracker.router.ContentType;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "test")
public class TestRoute {

    @Action(action = "test", responseContentType = ContentType.JSON)
    public ActionResponse test(HttpServletRequest request) {
        ActionResponse actionResponse = new ActionResponse();
        User user = new User();
        user.setId(1324);
        user.setPasswordHash("dfdfkirffn");
        user.setEmail("df@dd.d");
        actionResponse.setModel(user);
        return actionResponse;
    }
}
