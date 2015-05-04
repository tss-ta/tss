package com.netcracker.router;

import com.netcracker.router.container.InstanceAndMethod;
import com.netcracker.router.container.MenuContainer;
import com.netcracker.router.container.MetaAction;
import com.netcracker.router.util.ArgumentValidator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Kyrylo Berehovyi
 */

public class AnnotationRouter implements Router {

    private final static Logger LOGGER = Logger.getLogger(AnnotationRouter.class.getName());

    private MenuContainer coreContainer = new MenuContainer();
    private AnnotationRouterInitializer initializer;

    public AnnotationRouter(String packageToScan) {
        initializer = new AnnotationRouterInitializer(packageToScan);
        initializer.initialize(this);

        LOGGER.log(Level.INFO, coreContainer.info());
    }

    @Override
    public InstanceAndMethod findActionMethod(String menu, String action, HttpMethod httpMethod) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");

        return coreContainer.findActionContainer(menu)
                            .findHttpMethodContainer(action)
                            .findInstanceAndMethod(httpMethod);
    }

    @Override
    public void addActionMethod(String menu, String action, HttpMethod httpMethod, InstanceAndMethod instanceAndMethod) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");
        ArgumentValidator.validateOnNull(instanceAndMethod, "instanceAndMethod");

        coreContainer.findOrCreateActionContainer(menu)
                     .findOrCreateHttpMethodContainer(action)
                     .addInstanceAndMethod(httpMethod, instanceAndMethod);
    }

    @Override
    public void addActionMethod(MetaAction metaAction) {
        ArgumentValidator.validateOnNull(metaAction, "metaAction");

        addActionMethod(metaAction.getMenu(), metaAction.getAction(), metaAction.getMethod(),
                metaAction.getInstanceAndMethod());
    }
}
