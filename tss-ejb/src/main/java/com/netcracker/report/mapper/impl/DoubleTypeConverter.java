package com.netcracker.report.mapper.impl;

import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.DataType;
import com.netcracker.report.mapper.TypeConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kyrylo Berehovyi
 */
public class DoubleTypeConverter implements TypeConverter {

    public static final DataType TYPE = DataType.DOUBLE;
    public static final Double DEFAULT_VALUE = 0.0;

    @Override
    public MultipurposeValue convert(ResultSet resultSet, int index, DataType type) throws SQLException {
        return new MultipurposeValue(type, resultSet.getDouble(index));
    }

    @Override
    public MultipurposeValue convert(String value) {
        return new MultipurposeValue(TYPE, Double.parseDouble(value));
    }

    @Override
    public void insertValueIntoPreparedStatement(PreparedStatement statement, int index, MultipurposeValue value) throws SQLException {
        statement.setDouble(index, value.getDoubleValue());
    }

    @Override
    public MultipurposeValue dafaultValue() {
        return new MultipurposeValue(TYPE, DEFAULT_VALUE);
    }

    @Override
    public DataType type() {
        return TYPE;
    }

    @Override
    public void insertDefaultValueIntoPreparedStatement(PreparedStatement statement, int index) throws SQLException {
        statement.setDouble(index, DEFAULT_VALUE);
    }
}
