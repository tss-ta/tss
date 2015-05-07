package com.netcracker.router.container;

import java.lang.reflect.Method;

/**
 * @author Kyrylo Berehovyi
 */

public class InstanceAndMethod {
    private Object instance;
    private Method method;

    public InstanceAndMethod(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InstanceAndMethod{");
        sb.append("instance=").append(instance);
        sb.append(", method=").append(method);
        sb.append('}');
        return sb.toString();
    }
}
