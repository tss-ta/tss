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
public class LongTypeConverter implements TypeConverter {

    public static final DataType TYPE = DataType.LONG;
    public static final Long DEFAULT_VALUE = 0L;

    @Override
    public MultipurposeValue convert(ResultSet resultSet, int index, DataType type) throws SQLException {
        return new MultipurposeValue(type, resultSet.getLong(index));
    }

    @Override
    public MultipurposeValue convert(String value) {
        return new MultipurposeValue(TYPE, Long.parseLong(value));
    }

    @Override
    public void insertValueIntoPreparedStatement(PreparedStatement statement, int index, MultipurposeValue value) throws SQLException {
        statement.setLong(index, value.getLongValue());
    }

    @Override
    public DataType type() {
        return TYPE;
    }

    @Override
    public MultipurposeValue dafaultValue() {
        return new MultipurposeValue(TYPE, DEFAULT_VALUE);
    }

    @Override
    public void insertDefaultValueIntoPreparedStatement(PreparedStatement statement, int index) throws SQLException {
        statement.setLong(index, DEFAULT_VALUE);
    }
}
