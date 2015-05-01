package com.netcracker.util;

/**
 * Created by happy on 02/05/2015.
 */

public class JndiNameBuilder {

    private static final String JNDI_PREFFIX = "java:app/tss-ejb/";

    public <T> String buildEjbName(Class<T> beanLocal) {
        if (beanLocal == null)
            throw new IllegalArgumentException("Illegal 'beanLocal' argument value: null.");

        StringBuilder name = new StringBuilder(JNDI_PREFFIX);
        name.append(beanLocal.getSimpleName().split("Local")[0])
            .append("!")
            .append(beanLocal.getCanonicalName())
            .append("Home");
        return name.toString();
    }
}
