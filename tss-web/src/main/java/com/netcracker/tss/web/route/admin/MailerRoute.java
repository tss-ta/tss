package com.netcracker.tss.web.route.admin;

import com.netcracker.router.HttpMethod;
import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.tss.web.util.Page;
import com.netcracker.tss.web.util.RequestAttribute;
import com.netcracker.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Kyrylo Berehovyi
 */

@ActionRoute(menu = "mailer")
public class MailerRoute {

    private static final String MAILER_ACTIVATED_MESSAGE = "Mailer was Activated.";
    private static final String MAILER_DISABLED_MESSAGE = "Mailer was disabled.";

    @Action(action = "state")
    public ActionResponse viewMailerState(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        request.setAttribute(RequestAttribute.MAILER_STATE.getName(), GlobalVariables.MAILER_STATE.get());
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_MAILER.getType());
        response.setPageContent(Page.ADMIN_MAILER.getAbsolutePath());
        return response;
    }

    @Action(action = "state", httpMethod = HttpMethod.POST)
    public ActionResponse changewMailerState(HttpServletRequest request) {
        ActionResponse response = new ActionResponse();
        boolean newMailerState = changeMailerState();
        request.setAttribute(RequestAttribute.MAILER_STATE.getName(), newMailerState);
        if (newMailerState) {
            response.setSuccessMessage(MAILER_ACTIVATED_MESSAGE);
        } else {
            response.setSuccessMessage(MAILER_DISABLED_MESSAGE);
        }
        request.setAttribute(RequestAttribute.PAGE_TYPE.getName(), Page.ADMIN_MAILER.getType());
        response.setPageContent(Page.ADMIN_MAILER.getAbsolutePath());
        return response;
    }

    private boolean changeMailerState() {
        AtomicBoolean atomicBoolean = GlobalVariables.MAILER_STATE;
        if (atomicBoolean.get()) {
            atomicBoolean.set(false);
            return false;
        }
        atomicBoolean.set(true);
        return true;
    }
}
