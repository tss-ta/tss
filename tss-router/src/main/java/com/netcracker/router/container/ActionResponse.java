package com.netcracker.router.container;

/**
 * @author Kyrylo Berehovyi
 */

public class ActionResponse {
    private String pageContent;
    private String errorMessage;
    private String successMessage;
    private String redirectURI;

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

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActionResponse{");
        sb.append("pageContent='").append(pageContent).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", successMessage='").append(successMessage).append('\'');
        sb.append(", redirectURI='").append(redirectURI).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
