package com.netcracker.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Kyrylo Berehovyi
 */

@Entity
@Table(name = "report_info")
public class ReportInfo {

    @Id
    private Integer id;
    private String name;
    private String description;
    private boolean countable;

    @Column(name = "select_query")
    private String selectQuery;

    @Column(name = "count_query")
    private String countQuery;

    @Column(name = "page_size")
    private Integer pageSize;

    public ReportInfo() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCountable() {
        return countable;
    }

    public void setCountable(boolean countable) {
        this.countable = countable;
    }

    public String getSelectQuery() {
        return selectQuery;
    }

    public void setSelectQuery(String selectQuery) {
        this.selectQuery = selectQuery;
    }

    public String getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(String countQuery) {
        this.countQuery = countQuery;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportInfo that = (ReportInfo) o;

        if (countable != that.countable) return false;
        if (countQuery != null ? !countQuery.equals(that.countQuery) : that.countQuery != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pageSize != null ? !pageSize.equals(that.pageSize) : that.pageSize != null) return false;
        if (selectQuery != null ? !selectQuery.equals(that.selectQuery) : that.selectQuery != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (countable ? 1 : 0);
        result = 31 * result + (selectQuery != null ? selectQuery.hashCode() : 0);
        result = 31 * result + (countQuery != null ? countQuery.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportInfo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", countable=").append(countable);
        sb.append(", selectQuery='").append(selectQuery).append('\'');
        sb.append(", countQuery='").append(countQuery).append('\'');
        sb.append(", pageSize=").append(pageSize);
        sb.append('}');
        return sb.toString();
    }
}
