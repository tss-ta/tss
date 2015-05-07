package com.netcracker.router;

import com.netcracker.router.container.InstanceAndMethod;
import com.netcracker.router.container.MetaAction;

/**
 * @author Kyrylo Berehovyi
 */

public interface Router {

    InstanceAndMethod findActionMethod(String menu, String action, HttpMethod method);

    void addActionMethod(String menu, String action, HttpMethod httpMethod, InstanceAndMethod instanceAndMethod);

    void addActionMethod(MetaAction metaAction);
}
