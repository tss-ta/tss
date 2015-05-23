package com.netcracker.report.mapper.impl;

import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.DataType;
import com.netcracker.report.mapper.TypeConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Kyrylo Berehovyi
 */
public class TimestampTypeConverter implements TypeConverter {

    public static final DataType TYPE = DataType.TIMESTAMP;

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
}
