package com.netcracker.entity.helper;

/**
 * @author Kyrylo Berehovyi
 */

public class Pager {
    private Integer firstPage;
    private Integer previousPage;
    private Integer nextPage;
    private Integer lastPage;

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pager{");
        sb.append("firstPage=").append(firstPage);
        sb.append(", lastPage=").append(lastPage);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", previousPage=").append(previousPage);
        sb.append('}');
        return sb.toString();
    }
}
