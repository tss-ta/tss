package com.netcracker.tss.web.router;

/**
 * Created by Kyrylo Berehovyi on 28/04/2015.
 */
public class DefaultActionRequest implements ActionRequest {
    private String destinationResource;
    private boolean redirect;
    private String successMessage;
    private String errorMessage;

    public DefaultActionRequest(String destinationResource, boolean redirect) {
        this.destinationResource = destinationResource;
        this.redirect = redirect;
    }

    @Override
    public String getDestinationResource() {
        return destinationResource;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setDestinationResource(String destinationResource) {
        this.destinationResource = destinationResource;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DefaultActionRequest{");
        sb.append("destinationResource='").append(destinationResource).append('\'');
        sb.append(", redirect=").append(redirect);
        sb.append(", successMessage='").append(successMessage).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
