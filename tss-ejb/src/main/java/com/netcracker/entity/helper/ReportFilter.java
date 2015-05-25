package com.netcracker.entity.helper;

import com.netcracker.entity.Criterion;
import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.DataType;

import java.util.*;

/**
 * @author Kyrylo Berehovyi
 */

public class ReportFilter {
    private Integer criterionAmount;
    private List<MultipurposeValue> criteria = new ArrayList<>();

    public ReportFilter() {}

    public ReportFilter(Integer criterionAmount) {
        this.criterionAmount = criterionAmount;
    }

    public ReportFilter(Integer criterionAmount, List<MultipurposeValue> criteria) {
        this.criterionAmount = criterionAmount;
        this.criteria = criteria;
    }

    public Integer getCriterionAmount() {
        return criterionAmount;
    }

    public void setCriterionAmount(Integer criterionAmount) {
        this.criterionAmount = criterionAmount;
    }

    public List<MultipurposeValue> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<MultipurposeValue> criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReportFilter that = (ReportFilter) o;

        if (criteria != null ? !criteria.equals(that.criteria) : that.criteria != null) return false;
        if (criterionAmount != null ? !criterionAmount.equals(that.criterionAmount) : that.criterionAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = criterionAmount != null ? criterionAmount.hashCode() : 0;
        result = 31 * result + (criteria != null ? criteria.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportFilter{");
        sb.append("criterionAmount=").append(criterionAmount);
        sb.append(", criteria=").append(criteria);
        sb.append('}');
        return sb.toString();
    }
}
