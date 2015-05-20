package com.netcracker.router.container;

import com.netcracker.router.ContentType;

import java.lang.reflect.Method;

/**
 * @author Kyrylo Berehovyi
 */

public class ActionMetaData {
    private Object instance;
    private Method method;
    private ContentType responseContentType;

    public ActionMetaData() {}

    public ActionMetaData(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    public ActionMetaData(Object instance, Method method, ContentType responseContentType) {
        this.instance = instance;
        this.method = method;
        this.responseContentType = responseContentType;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ContentType getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(ContentType responseContentType) {
        this.responseContentType = responseContentType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstanceAndMethod{");
        sb.append("instance=").append(instance);
        sb.append(", method=").append(method);
        sb.append('}');
        return sb.toString();
    }
}
