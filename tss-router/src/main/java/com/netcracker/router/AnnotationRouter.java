package com.netcracker.router;

import com.netcracker.router.container.ActionMetaData;
import com.netcracker.router.container.MenuContainer;
import com.netcracker.router.container.ActionInfo;
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
    public ActionMetaData findActionMethod(String menu, String action, HttpMethod httpMethod) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");

        return coreContainer.findActionContainer(menu)
                            .findHttpMethodContainer(action)
                            .findInstanceAndMethod(httpMethod);
    }

    @Override
    public void addActionMethod(String menu, String action, HttpMethod httpMethod, ActionMetaData actionMetaData) {
        ArgumentValidator.validateOnNull(httpMethod, "httpMethod");
        ArgumentValidator.validateOnNull(actionMetaData, "instanceAndMethod");

        coreContainer.findOrCreateActionContainer(menu)
                     .findOrCreateHttpMethodContainer(action)
                     .addInstanceAndMethod(httpMethod, actionMetaData);
    }

    @Override
    public void addActionMethod(ActionInfo actionInfo) {
        ArgumentValidator.validateOnNull(actionInfo, "metaAction");

        addActionMethod(actionInfo.getMenu(), actionInfo.getAction(), actionInfo.getMethod(),
                actionInfo.getActionMetaData());
    }
}
