package com.netcracker.report.container;

import com.netcracker.report.mapper.DataType;

/**
 * @author Kyrylo Berehovyi
 */

public class ColumnMetaData {
    private String name;
    private DataType type;

    public ColumnMetaData() {}

    public ColumnMetaData(String name) {
        this.name = name;
    }

    public ColumnMetaData(DataType type) {
        this.type = type;
    }

    public ColumnMetaData(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColumnMetaData{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
