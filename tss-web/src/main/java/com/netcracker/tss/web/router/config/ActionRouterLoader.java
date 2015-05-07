package com.netcracker.tss.web.router.config;

import com.netcracker.tss.web.route.admin.car.AddCarGetRoute;
import com.netcracker.tss.web.route.admin.car.AddCarPostRoute;
import com.netcracker.tss.web.route.admin.car.AllCarsGetRoute;
import com.netcracker.tss.web.route.admin.car.SearchCarsByLicensePostRoute;
import com.netcracker.tss.web.router.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
        testAnnotation();
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

    public void testAnnotation() {
//        Reflections reflections = new Reflections("com.netcracker.tss.web");
//        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(ActionRoute.class);
//        for (Class<?> clazz : annotated) {
//            System.out.println("-----Class: " + clazz.getCanonicalName());
//            System.out.println("-----Class.menu: " + clazz.getAnnotation(ActionRoute.class).menu());
//            Method[] methods = clazz.getMethods();
//            for (Method method : methods) {
//                Action actionAnnotation = method.getAnnotation(Action.class);
//                if(actionAnnotation != null) {
//                    System.out.println("-----Action.method: " + actionAnnotation.httpMethod());
//                    System.out.println("-----Action.action: " + actionAnnotation.action());
//                    ActionRequest request = null;
//                    try {
//                        request = (ActionRequest) method.invoke(clazz.newInstance());
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    } catch (InstantiationException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("-----ActionRequest: " + request);
//                }
//            }
//        }
    }
}
