package com.netcracker.router;

import com.netcracker.router.annotation.Action;
import com.netcracker.router.annotation.ActionRoute;
import com.netcracker.router.container.ActionResponse;
import com.netcracker.router.container.InstanceAndMethod;
import com.netcracker.router.container.MetaAction;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Kyrylo Berehovyi
 */

public class AnnotationRouterInitializer {

    private static final Class<?> actionMethodReturnType = ActionResponse.class;

    private Reflections reflections;

    public AnnotationRouterInitializer(String packageToScan) {
        reflections = new Reflections(packageToScan);
    }

    public void initialize(AnnotationRouter router) {
        Set<Class<?>> annotatedClasses = findAllAnnotatedClasses(ActionRoute.class);
        Object instance;
        String menu;
        for (Class<?> annotated : annotatedClasses) {
            List<Method> methods = findAllValidAnnotatedMethods(Action.class, annotated, actionMethodReturnType);
            if (!methods.isEmpty()) {
                menu = annotated.getAnnotation(ActionRoute.class).menu();
                instance = createInstance(annotated);
                addInstanceAndMethodToRouter(methods, instance, menu, router);
            }
        }
    }

    private void addInstanceAndMethodToRouter(List<Method> methods, Object instance, String menu, AnnotationRouter router) {
        for (Method method : methods) {
            router.addActionMethod(createMetaAction(instance, menu, method));
        }
    }

    private MetaAction createMetaAction(Object instance, String menu, Method method) {
        MetaAction metaAction = new MetaAction();
        Action actionAnnotation = method.getAnnotation(Action.class);
        metaAction.setMenu(menu);
        metaAction.setAction(actionAnnotation.action());
        metaAction.setMethod(actionAnnotation.httpMethod());
        metaAction.setInstanceAndMethod(new InstanceAndMethod(instance, method));
        return metaAction;
    }

    private Object createInstance(Class<?> type) {
        Object instance = null;
        try {
            instance = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private Set<Class<?>> findAllAnnotatedClasses(Class<? extends Annotation> annotation) {
        return reflections.getTypesAnnotatedWith(annotation);
    }

    private List<Method> findAllValidAnnotatedMethods(Class<? extends Annotation> annotation,
                                                      Class<?> type, Class<?> returnType) {

        Method[] methods = type.getMethods();
        List<Method> validMethods = new ArrayList<>();
        for (Method method : methods) {
            if (isValidMethod(annotation, method, returnType)) {
                validMethods.add(method);
            }
        }
        return validMethods;
    }

    public boolean isValidMethod(Class<? extends Annotation> annotation, Method method, Class<?> returnType) {
        if (method.getAnnotation(annotation) == null) {
            return false;
        }
        if(!method.getReturnType().equals(returnType)) {
            return false;
        }
        return true;
    }
}
