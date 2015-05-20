package com.netcracker.report.mapper;

import com.netcracker.report.container.MultipurposeValue;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kyrylo Berehovyi
 */

public interface TypeConverter {
    MultipurposeValue convert(ResultSet resultSet, int index, DataType type) throws SQLException;
}
