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

    public MultipurposeValue columnData(Integer number) {
        return row.get(number);
    }

    public void addColumn(Integer number, MultipurposeValue data) {
        row.put(number, data);
    }

    public int columnAmount() {
        return row.size();
    }

    public Set<Integer> allColumnNumbers() {
        return row.keySet();
    }

    public Map<Integer, MultipurposeValue> getRow() {
        return row;
    }

    public void setRow(Map<Integer, MultipurposeValue> row) {
        this.row = row;
    }

    public MultipurposeValue getColumn(Integer index) {
        return row.get(index);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RowData{");
        sb.append("row=").append(row);
        sb.append('}');
        return sb.toString();
    }
}
