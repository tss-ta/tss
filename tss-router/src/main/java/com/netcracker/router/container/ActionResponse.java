package com.netcracker.router.container;

/**
 * @author Kyrylo Berehovyi
 */

public class ActionResponse {
    private String pageContent;
    private String errorMessage;
    private String successMessage;
    private Object model;
    private boolean redirect;

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActionResponse{");
        sb.append("partial='").append(pageContent).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", successMessage='").append(successMessage).append('\'');
        sb.append(", model=").append(model);
        sb.append(", redirect=").append(redirect);
        sb.append('}');
        return sb.toString();
    }
}
