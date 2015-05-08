package com.netcracker.report;

import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.DataType;
import com.netcracker.report.mapper.TypeConverter;
import com.netcracker.report.mapper.impl.BooleanTypeConverter;
import com.netcracker.report.mapper.impl.IntegerTypeConverter;
import com.netcracker.report.mapper.impl.StringTypeConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyrylo Berehovyi
 */

public class ResultSetTypeMapper {

    private static final int SERIAL = 4;
    private static final int VARCHAR = 12;
    private static final int BOOL = -7;

    private static final Map<Integer, DataType> typeRelationMap = new HashMap<>();
    private static final Map<DataType, TypeConverter> converterMap = new HashMap<>();

    static {
        addDependency(SERIAL, DataType.INTEGER, new IntegerTypeConverter());
        addDependency(VARCHAR, DataType.STRING, new StringTypeConverter());
        addDependency(BOOL, DataType.BOOLEAN, new BooleanTypeConverter());
    }

    public static void addDependency(Integer index, DataType type, TypeConverter converter) {
        typeRelationMap.put(index, type);
        converterMap.put(type, converter);
    }

    public DataType convertColumnTypeToLanguageType(int typeNumber) {
        return typeRelationMap.get(typeNumber);
    }

    public MultipurposeValue getDataFromColumn(ResultSet resultSet, int index, DataType type) throws SQLException {
        return converterMap.get(type).convert(resultSet, index, type);
    }
}
