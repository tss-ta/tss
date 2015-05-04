package com.netcracker.router.servlet;

import com.netcracker.router.AnnotationRouter;
import com.netcracker.router.HttpMethod;
import com.netcracker.router.Router;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.router.container.InstanceAndMethod;
import com.netcracker.router.exception.ActionNotFoundException;
import com.netcracker.router.exception.HttpMethodNotAllowedException;
import com.netcracker.router.util.LoggerUtil;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Kyrylo Berehovyi
 */

public class RouterServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(RouterServlet.class.getName());

    private static final String DEFAULT_MENU_ALIAS = "menu";
    private static final String DEFAULT_ACTION_ALIAS = "action";
    private static final String PAGE_CONTENT = "pageContent";
    private static final String MENU_ALIAS_PARAM_NAME = "menuAlias";
    private static final String ACTION_ALIAS_PARAM_NAME = "actionAlias";
    private static final String PACKAGE_TO_SCAN_PARAM_NAME = "packageToScan";
    private static final String TEMPLATE_PARAM_NAME = "templateView";
    private static final String CONTENT_404_PARAM_NAME = "404";
    private static final String CONTENT_405_PARAM_NAME = "405";
    private static final String CONTENT_500_PARAM_NAME = "500";

    private String menuAlias;
    private String actionAlias;
    private String packageToScan;
    private String template;
    private String content404;
    private String content405;
    private String content500;
    private Router router;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        initializeParams(config);
        checkInitParams();
        router = new AnnotationRouter(packageToScan);
    }

    private void initializeParams(ServletConfig config) {
        menuAlias = config.getInitParameter(MENU_ALIAS_PARAM_NAME);
        if (menuAlias == null) {
            menuAlias = DEFAULT_MENU_ALIAS;
        }
        actionAlias = config.getInitParameter(ACTION_ALIAS_PARAM_NAME);
        if (actionAlias == null) {
            actionAlias = DEFAULT_ACTION_ALIAS;
        }
        packageToScan = config.getInitParameter(PACKAGE_TO_SCAN_PARAM_NAME);
        template = config.getInitParameter(TEMPLATE_PARAM_NAME);
        content404 = config.getInitParameter(CONTENT_404_PARAM_NAME);
        content405 = config.getInitParameter(CONTENT_405_PARAM_NAME);
        content500 = config.getInitParameter(CONTENT_500_PARAM_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, HttpMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, HttpMethod.POST);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, HttpMethod httpMethod) throws ServletException, IOException {
        String menu = req.getParameter(this.menuAlias);
        String action = req.getParameter(this.actionAlias);
        InstanceAndMethod instanceAndMethod = null;
        ActionResponse actionResponse = null;

        if(menu == null || action == null) {
            forwardTo(req, resp, content404);
            return;
        }

        try {
            instanceAndMethod = router.findActionMethod(menu, action, httpMethod);
            actionResponse = (ActionResponse) instanceAndMethod.getMethod().invoke(instanceAndMethod.getInstance(), req);
        } catch (ActionNotFoundException e) {
            forwardTo(req, resp, content404);
            LOGGER.log(Level.INFO, e.getMessage());
            return;
        } catch (HttpMethodNotAllowedException e) {
            LOGGER.log(Level.INFO, e.getMessage());
            forwardTo(req, resp, content405);
            return;
        } catch (Exception e) {
            forwardTo(req, resp, content500);
            LOGGER.log(Level.ERROR, LoggerUtil.getStackTrace(e));
            return;
        }

        forwardTo(req, resp, actionResponse.getPageContent());
    }

    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String content)
            throws ServletException, IOException {
        req.setAttribute(PAGE_CONTENT, content);
        req.getRequestDispatcher(template).forward(req, resp);
    }

    private void checkInitParams() {
        if (template == null || content404 == null || content405 == null
                             || content500 == null || packageToScan == null) {
            throw new IllegalStateException("Invalid initial parameters");
        }
    }


}
