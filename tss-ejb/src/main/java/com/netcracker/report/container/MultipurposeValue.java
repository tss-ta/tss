package com.netcracker.report.container;

import com.netcracker.report.mapper.DataType;

/**
 * @author Kyrylo Berehovyi
 */

public class MultipurposeValue {
    private DataType type;
    private String stringValue;
    private int intValue;
    private double doubleValue;
    private boolean booleanValue;

    public MultipurposeValue(DataType type, String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
    }

    public MultipurposeValue(DataType type, int intValue) {
        this.type = type;
        this.intValue = intValue;
    }

    public MultipurposeValue(DataType type, double doubleValue) {
        this.type = type;
        this.doubleValue = doubleValue;
    }

    public MultipurposeValue(DataType type, boolean booleanValue) {
        this.type = type;
        this.booleanValue = booleanValue;
    }

    public DataType getType() {
        return type;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenericData{");
        sb.append("type=").append(type);
        sb.append(", stringValue='").append(stringValue).append('\'');
        sb.append(", intValue=").append(intValue);
        sb.append(", doubleValue=").append(doubleValue);
        sb.append(", booleanValue=").append(booleanValue);
        sb.append('}');
        return sb.toString();
    }
}
