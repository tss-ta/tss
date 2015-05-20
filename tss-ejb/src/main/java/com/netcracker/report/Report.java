package com.netcracker.report;

import com.netcracker.entity.ReportInfo;
import com.netcracker.report.container.ReportData;

/**
 * @author Kyrylo Berehovyi
 */

public class Report {
    private ReportInfo info;
    private ReportData data;

    public Report() {}

    public Report(ReportInfo info, ReportData data) {
        this.info = info;
        this.data = data;
    }

    public ReportInfo getInfo() {
        return info;
    }

    public void setInfo(ReportInfo info) {
        this.info = info;
    }

    public ReportData getData() {
        return data;
    }

    public void setData(ReportData data) {
        this.data = data;
    }
}
