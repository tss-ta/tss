package com.netcracker.report.mapper.impl;

import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.DataType;
import com.netcracker.report.mapper.TypeConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Kyrylo Berehovyi
 */
public class TimestampTypeConverter implements TypeConverter {

    public static final DataType TYPE = DataType.TIMESTAMP;
    public static final Timestamp DEFAULT_VALUE = Timestamp.valueOf("0001-01-01 01:01:01");

    @Override
    public MultipurposeValue convert(ResultSet resultSet, int index, DataType type) throws SQLException {
        return new MultipurposeValue(type, resultSet.getTimestamp(index));
    }

    @Override
    public MultipurposeValue convert(String value) {
        return new MultipurposeValue(TYPE, Timestamp.valueOf(value));
    }

    @Override
    public DataType type() {
        return TYPE;
    }

    @Override
    public void insertValueIntoPreparedStatement(PreparedStatement statement, int index, MultipurposeValue value)
            throws SQLException {
        statement.setTimestamp(index, value.getTimestampValue());
    }

    @Override
    public MultipurposeValue dafaultValue() {
        return new MultipurposeValue(TYPE, DEFAULT_VALUE);
    }

    @Override
    public void insertDefaultValueIntoPreparedStatement(PreparedStatement statement, int index) throws SQLException {
        statement.setTimestamp(index,DEFAULT_VALUE);
    }
}
