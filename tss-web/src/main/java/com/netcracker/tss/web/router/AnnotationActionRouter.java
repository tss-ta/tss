package com.netcracker.tss.web.router;

import com.netcracker.tss.web.router.config.HttpMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kyrylo Berehovyi on 03/05/2015.
 */
public class AnnotationActionRouter {
    private Map<String, Map<String, Map<HttpMethod, Method>>> core = new HashMap<>();

    public AnnotationActionRouter() {

    }

//    public Method getMethod(String menu, String action, HttpMethod method) {
//        // ToDo args validation
//        Map<String, Map<HttpMethod, Method>> actionMap = core.get(menu);
//        if (actionMap == null) {
//        }
//    }

    private void initCore() {

    }
}
