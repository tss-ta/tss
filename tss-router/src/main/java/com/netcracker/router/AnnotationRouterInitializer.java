package com.netcracker.router;

import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.router.container.ActionMetaData;
import com.netcracker.router.container.ActionInfo;
import com.netcracker.router.exception.RouterInitializationException;
import org.reflections.Reflections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Kyrylo Berehovyi
 */

public class AnnotationRouterInitializer {

//    private static final int MAX_ANNOTATED_METHOD_PARAMETERS_AMOUNT = 2;
    private static final Class<?> actionMethodReturnType = ActionResponse.class;
    private static final Class<?>[] validArgumentTypes = {HttpServletRequest.class};

    private Reflections reflections;

    public AnnotationRouterInitializer(String packageToScan) {
        reflections = new Reflections(packageToScan);
    }

    public void initialize(AnnotationRouter router) {
        Set<Class<?>> annotatedClasses = findAllAnnotatedClasses(ActionRoute.class);
        Object instance;
        String menu;
        for (Class<?> annotated : annotatedClasses) {
            List<Method> methods = findAllValidAnnotatedMethods(Action.class, annotated,
                    actionMethodReturnType, validArgumentTypes);
            if (!methods.isEmpty()) {
                menu = annotated.getAnnotation(ActionRoute.class).menu();
                instance = createInstance(annotated);
                addActionsToRouter(methods, instance, menu, router);
            }
        }
    }

    private void addActionsToRouter(List<Method> methods, Object instance, String menu, AnnotationRouter router) {
        for (Method method : methods) {
            router.addActionMethod(createActionInfo(instance, menu, method));
        }
    }

    private ActionInfo createActionInfo(Object instance, String menu, Method method) {
        ActionInfo actionInfo = new ActionInfo();
        Action actionAnnotation = method.getAnnotation(Action.class);
        actionInfo.setMenu(menu);
        actionInfo.setAction(actionAnnotation.action());
        actionInfo.setMethod(actionAnnotation.httpMethod());
        actionInfo.setActionMetaData(new ActionMetaData(instance, method, actionAnnotation.responseContentType()));
        return actionInfo;
    }

    private Object createInstance(Class<?> type) {
        Object instance = null;
        try {
            instance = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private Set<Class<?>> findAllAnnotatedClasses(Class<? extends Annotation> annotation) {
        return reflections.getTypesAnnotatedWith(annotation);
    }

    private List<Method> findAllValidAnnotatedMethods(Class<? extends Annotation> annotation,
                                                      Class<?> type, Class<?> returnType, Class<?>[] validArgumentTypes) {
        Method[] methods = type.getMethods();
        List<Method> validMethods = new ArrayList<>();
        for (Method method : methods) {
            if (isValidMethod(annotation, method, returnType, validArgumentTypes)) {
                validMethods.add(method);
            }
        }
        return validMethods;
    }

    public boolean isValidMethod(Class<? extends Annotation> annotation, Method method,
                                 Class<?> returnType, Class<?>[] validArgumentTypes) {
        if (method.getAnnotation(annotation) == null) {
            return false;
        }
        if (!method.getReturnType().equals(returnType)) {
            return false;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length > validArgumentTypes.length) {
            throw new RouterInitializationException("Method annotated as @Action has declared invalid parameters number.");
        }
//        if (parameterTypes.length > 0 && !parameterTypes[0].equals(parameterType)) {
//            throw new RouterInitializationException("Method annotated as @Action has declared invalid parameter type.");
//        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].equals(validArgumentTypes[i])) {
                throw new RouterInitializationException("Illegal method argument type '" + parameterTypes[i] +
                        "' was declared in method annotated as @Action." );
            }
        }
        return true;
    }
}
