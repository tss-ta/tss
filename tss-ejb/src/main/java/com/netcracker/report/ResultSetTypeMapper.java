package com.netcracker.report;

import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.DataType;
import com.netcracker.report.mapper.TypeConverter;
import com.netcracker.report.mapper.exception.TypeConverterException;
import com.netcracker.report.mapper.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyrylo Berehovyi
 */

public class ResultSetTypeMapper {

    private static final int INTEGER = 4;
    private static final int VARCHAR = 12;
    private static final int BOOL = -7;
    private static final int DOUBLE = 8;
    private static final int TIMESTAMP = 93;
    private static final int LONG = -5;

    private static final Map<Integer, DataType> typeRelationMap = new HashMap<>();
    private static final Map<DataType, TypeConverter> converterMap = new HashMap<>();

    static {
        addDependency(INTEGER, DataType.INTEGER, new IntegerTypeConverter());
        addDependency(VARCHAR, DataType.STRING, new StringTypeConverter());
        addDependency(BOOL, DataType.BOOLEAN, new BooleanTypeConverter());
        addDependency(DOUBLE, DataType.DOUBLE, new DoubleTypeConverter());
        addDependency(TIMESTAMP, DataType.TIMESTAMP, new TimestampTypeConverter());
        addDependency(LONG, DataType.LONG, new LongTypeConverter());
    }

    private static void addDependency(Integer index, DataType type, TypeConverter converter) {
        typeRelationMap.put(index, type);
        converterMap.put(type, converter);
    }

    public DataType convertColumnTypeToLanguageType(int typeNumber) {
        DataType type = typeRelationMap.get(typeNumber);
        if (type == null) {
            throw new TypeConverterException("Unknown DB type index '" + typeNumber + "'. Type converter for this type does not exist.");
        }
        return type;
    }

    public MultipurposeValue getDataFromColumn(ResultSet resultSet, int index, DataType type) throws SQLException {
        TypeConverter converter = converterMap.get(type);
        if(converter == null) {
            throw new TypeConverterException("Type converter for type '" + type.name() + "' does not exist.");
        }
        return converter.convert(resultSet, index, type);
    }
}
