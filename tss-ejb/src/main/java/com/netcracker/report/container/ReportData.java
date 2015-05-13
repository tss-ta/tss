package com.netcracker.report.container;

import com.netcracker.report.mapper.DataType;

import java.util.*;

/**
 * @author Kyrylo Berehovyi
 */

public class ReportData {
    private Map<Integer, ColumnMetaData> columnMetaDataMap = new HashMap<>();
    private List<RowData> rows = new ArrayList<>();

    public int columnAmount() {
        return columnMetaDataMap.size();
    }

    public String columnName(Integer index) {
        return columnMetaDataMap.get(index).getName();
    }

    public DataType columnType(Integer index) {
        return columnMetaDataMap.get(index).getType();
    }

    public void addMetaColumnInfo(Integer index, String name, DataType type) {
        columnMetaDataMap.put(index, new ColumnMetaData(name, type));
    }

    public Set<Integer> allColumnIndeces() {
        return columnMetaDataMap.keySet();
    }

    public List<RowData> getRows() {
        return rows;
    }

    public void addRow(RowData data) {
        rows.add(data);
    }

    public RowData findRow(int index) {
        return rows.get(index);
    }

    public int rowsAmount() {
        return rows.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportData{");
        sb.append("metaInfo=").append(columnMetaDataMap);
        sb.append(", rows=").append(rows);
        sb.append('}');
        return sb.toString();
    }
}
