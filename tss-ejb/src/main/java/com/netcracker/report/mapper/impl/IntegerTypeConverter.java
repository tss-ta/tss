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

public class IntegerTypeConverter implements TypeConverter {

    public static final DataType TYPE = DataType.INTEGER;
    public static final Integer DEFAULT_VALUE = 0;

    @Override
    public MultipurposeValue convert(ResultSet resultSet, int index, DataType type) throws SQLException {
        return new MultipurposeValue(type, resultSet.getInt(index));
    }

    @Override
    public MultipurposeValue convert(String value) {
        return new MultipurposeValue(TYPE, Integer.parseInt(value));
    }

    @Override
    public void insertValueIntoPreparedStatement(PreparedStatement statement, int index, MultipurposeValue value) throws SQLException {
        statement.setInt(index, value.getIntValue());
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
        statement.setInt(index, DEFAULT_VALUE);
    }
}
