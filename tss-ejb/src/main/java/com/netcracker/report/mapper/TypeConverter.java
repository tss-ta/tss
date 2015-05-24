package com.netcracker.report.mapper;

import com.netcracker.report.container.MultipurposeValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kyrylo Berehovyi
 */

public interface TypeConverter {
    MultipurposeValue convert(ResultSet resultSet, int index, DataType type) throws SQLException;
    MultipurposeValue convert(String value);
    MultipurposeValue dafaultValue();
    void insertValueIntoPreparedStatement(PreparedStatement statement, int index, MultipurposeValue value) throws SQLException;
    void insertDefaultValueIntoPreparedStatement(PreparedStatement statement, int index) throws SQLException;
    DataType type();
}
