package com.netcracker.entity;

import com.netcracker.report.Report;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Kyrylo Berehovyi
 */

@Entity
@Table(name = "criterion")
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40)
    private String name;

    @NotNull
    private Integer type;

    @ManyToOne
    @JoinColumn(name = "report_info")
    private ReportInfo reportInfo;

    public Criterion() {}

    public Criterion(Integer id, String name, Integer type, ReportInfo reportInfo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.reportInfo = reportInfo;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Criterion criterion = (Criterion) o;

        if (id != null ? !id.equals(criterion.id) : criterion.id != null) return false;
        if (name != null ? !name.equals(criterion.name) : criterion.name != null) return false;
        if (reportInfo != null ? !reportInfo.equals(criterion.reportInfo) : criterion.reportInfo != null) return false;
        if (type != null ? !type.equals(criterion.type) : criterion.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (reportInfo != null ? reportInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Criterion{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", reportInfo=").append(reportInfo);
        sb.append('}');
        return sb.toString();
    }
}
