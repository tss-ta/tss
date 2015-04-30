package com.netcracker.tss.web.router.config;

import com.netcracker.tss.web.route.admin.car.AddCarGetRoute;
import com.netcracker.tss.web.route.admin.car.AddCarPostRoute;
import com.netcracker.tss.web.route.admin.car.AllCarsGetRoute;
import com.netcracker.tss.web.route.admin.car.SearchCarsByLicensePostRoute;
import com.netcracker.tss.web.router.ActionRouter;
import com.netcracker.tss.web.router.DefaultActionRouter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */

@WebListener
public class ActionRouterLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(ActionRouterDescriber.ACTION_ROUTER.getAttributeName(),
                createActionRouter());
        servletContext.setAttribute(ActionRouterDescriber.ROUTER_NAME_DESCRIBER_LIST.getAttributeName(),
                createRouterNameDescriberList());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}

    public ActionRouter createActionRouter() {
        ActionRouter actionRouter = new DefaultActionRouter();

        actionRouter.addRoute(new AddCarPostRoute());
        actionRouter.addRoute(new AddCarGetRoute());
        actionRouter.addRoute(new AllCarsGetRoute());
        actionRouter.addRoute(new SearchCarsByLicensePostRoute());

        return actionRouter;
    }

    public List<RouterNameDescriber> createRouterNameDescriberList() {
        List<RouterNameDescriber> routerNameDescriberList = new LinkedList<RouterNameDescriber>();

        routerNameDescriberList.add(RouterNameDescriber.MENU);
        routerNameDescriberList.add(RouterNameDescriber.ACTION);

        return routerNameDescriberList;
    }
}
