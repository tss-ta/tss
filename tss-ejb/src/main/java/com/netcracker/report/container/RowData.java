package com.netcracker.report.container;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Kyrylo Berehovyi
 */

public class RowData {
    private Map<Integer, MultipurposeValue> row = new HashMap<>();

    public RowData(){}

    public MultipurposeValue getColumnData(Integer number) {
        return row.get(number);
    }

    public void addColumn(Integer number, MultipurposeValue data) {
        row.put(number, data);
    }

    public Set<Integer> getAllColumnNumbers() {
        return row.keySet();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RowData{");
        sb.append("row=").append(row);
        sb.append('}');
        return sb.toString();
    }
}
