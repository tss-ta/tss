package com.netcracker.router;

import com.netcracker.router.container.ActionMetaData;
import com.netcracker.router.container.ActionInfo;

/**
 * @author Kyrylo Berehovyi
 */

public interface Router {

    ActionMetaData findActionMethod(String menu, String action, HttpMethod method);

    void addActionMethod(String menu, String action, HttpMethod httpMethod, ActionMetaData actionMetaData);

    void addActionMethod(ActionInfo actionInfo);
}
