package com.netcracker.util;

/**
 * Created by Kyrylo Berehovyi on 02/05/2015.
 */

public class JndiNameBuilder {

    private static String JNDI_MODULE_PREFIX = "java:app/tss-ejb/";

    public <T> String buildEjbName(Class<T> beanLocal) {
        if (beanLocal == null)
            throw new IllegalArgumentException("Illegal 'beanLocal' argument value: null.");

        StringBuilder name = new StringBuilder(JNDI_MODULE_PREFIX);
        name.append(beanLocal.getSimpleName().split("Local")[0])
            .append("!")
            .append(beanLocal.getCanonicalName())
            .append("Home");
        return name.toString();
    }
    
    public static void setPrefix(String prefix){
    	JNDI_MODULE_PREFIX = prefix;
    }
}
