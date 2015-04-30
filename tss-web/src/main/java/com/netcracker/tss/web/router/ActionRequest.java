package com.netcracker.tss.web.router;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */

public interface ActionRequest {
    public String getDestinationResource();
    public boolean isRedirect();
    public String getErrorMessage();
    public String getSuccessMessage();
}
