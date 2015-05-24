package com.netcracker.dao;

import com.netcracker.entity.ReportInfo;
import com.netcracker.entity.helper.ReportFilter;
import com.netcracker.report.Report;
import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.container.RowData;
import com.netcracker.report.mapper.DataType;
import com.netcracker.report.ResultSetTypeMapper;
import com.netcracker.report.container.ReportData;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * @author Kyrylo Berehovyi
 */

public class ReportDataDAO {

    private static final int EMPTY_VALUE = 0;
    private static final int FIRST_COLUMN = 1;
    private static final int LIMIT_POSITION = 1;
    private static final int OFFSET_POSITION = 2;
    public static final String DATA_SOURCE = "java:jboss/datasources/PostgreSQLDS";
    public static final String SELECT_QUERY_INVALID_SYNTAX_MESSAGE = "Select query has invalid syntax.";
    public static final String FILTERABLE_INVALID_SYNTAX_MESSAGE = "Select query has invalid syntax or invalid criterion parameters amount/type";
    public static final String FILTERABLE_COUNT_QUERY_INVALID_SYNTAX_MESSAGE = "Count query has invalid syntax or invalid criterion parameters amount/type";
    public static final String COUNT_QUERY_INVALID_SYNTAX_MESSAGE = "Count query has invalid syntax";

    private DataSource dataSource = getDataSource();
    private ResultSetTypeMapper mapper = new ResultSetTypeMapper();

    public String validateQuery(ReportInfo reportInfo, ReportFilter filter) {
        Connection connection = null;
        String message = null;
        try {
            connection = dataSource.getConnection();
            if (reportInfo.isFilterable()) {
                message = validateFilterableByPreparedStatement(connection,reportInfo, filter);
            } else {
                message = validateUnfilterableByPreparedStatement(connection, reportInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return message;
    }

    private String validateUnfilterableByPreparedStatement(Connection connection, ReportInfo reportInfo) {
        if (!reportInfo.isFilterable() && !reportInfo.isCountable()) {
            return validateUnfilterableUncaunableQuery(connection, reportInfo);
        } else if (!reportInfo.isFilterable() && reportInfo.isCountable()) {
            return validateUnfilterableCountableQuery(connection, reportInfo);
        }
        return null;
    }

    private String validateFilterableByPreparedStatement(Connection connection, ReportInfo reportInfo,
                                                         ReportFilter filter) {
        if (reportInfo.isFilterable() && !reportInfo.isCountable()) {
            return validateFilterableUncountableQuery(connection, reportInfo, filter);
        } else if (reportInfo.isFilterable() && reportInfo.isCountable()) {
            return validateFilterableCountableQuery(connection, reportInfo, filter);
        }
        return null;
    }

    private String validateFilterableCountableQuery(Connection connection, ReportInfo reportInfo, ReportFilter filter) {
        if (!isValidFilterableCountableQuery(connection, reportInfo, filter)) {
            return FILTERABLE_INVALID_SYNTAX_MESSAGE;
        }
        if (!isValidFilterableCountQuery(connection, reportInfo, filter)) {
            return FILTERABLE_COUNT_QUERY_INVALID_SYNTAX_MESSAGE;
        }
        return null;
    }

    private boolean isValidFilterableCountQuery(Connection connection, ReportInfo reportInfo, ReportFilter filter) {
        try {
            PreparedStatement statement = connection.prepareStatement(reportInfo.getCountQuery());
            setParametersIntoPreparedStatement(statement, filter);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean isValidFilterableCountableQuery(Connection connection, ReportInfo reportInfo, ReportFilter filter) {
        try {
            PreparedStatement statement = connection.prepareStatement(reportInfo.getSelectQuery());
            setParametersIntoPreparedStatement(statement, filter);
            statement.setInt(filter.getCriterionAmount() + LIMIT_POSITION, EMPTY_VALUE);
            statement.setInt(filter.getCriterionAmount() + OFFSET_POSITION, EMPTY_VALUE);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private String validateFilterableUncountableQuery(Connection connection, ReportInfo reportInfo, ReportFilter filter) {
        if (!isValidFilterableSelectQuery(connection, reportInfo, filter)) {
            return FILTERABLE_INVALID_SYNTAX_MESSAGE;
        }
        return null;
    }

    private boolean isValidFilterableSelectQuery(Connection connection, ReportInfo reportInfo, ReportFilter filter) {
        try {
            PreparedStatement statement = connection.prepareStatement(reportInfo.getSelectQuery());
            setParametersIntoPreparedStatement(statement, filter);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private void setDefaultParametersIntoPreparedStatement(PreparedStatement statement, ReportFilter filter) throws SQLException {
        List<MultipurposeValue> valueList = filter.getCriteria();
        for (int index = 1; index <= filter.getCriterionAmount(); index++) {
            mapper.setDefaultValueInStatement(index, statement, valueList.get(index - 1));
        }
    }

    private String validateUnfilterableCountableQuery(Connection connection, ReportInfo reportInfo) {
        if (!isValidCountableSelectQuery(connection, reportInfo.getSelectQuery())) {
            return SELECT_QUERY_INVALID_SYNTAX_MESSAGE;
        }
        if (!isValidQuery(connection, reportInfo.getCountQuery())) {
            return COUNT_QUERY_INVALID_SYNTAX_MESSAGE;
        }
        return null;
    }

    private boolean isValidCountableSelectQuery(Connection connection, String selectQuery) {
        try {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(LIMIT_POSITION, EMPTY_VALUE);
            statement.setInt(OFFSET_POSITION, EMPTY_VALUE);
            statement.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private String validateUnfilterableUncaunableQuery(Connection connection, ReportInfo reportInfo) {
        if (!isValidQuery(connection, reportInfo.getSelectQuery())) {
            return SELECT_QUERY_INVALID_SYNTAX_MESSAGE;
        }
        return null;
    }

    private boolean isValidQuery(Connection connection, String query) {
        try {
            connection.prepareStatement(query).executeQuery();
            return true;
        } catch (SQLException e) {
           return false;
        }
    }
    
    public long countResults(String query) {
        Connection connection = null;
        ResultSet resultSet;
        long count = 0;
        try {
            connection = dataSource.getConnection();
            resultSet = connection.prepareStatement(query).executeQuery();
            count = getCountFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return count;
    }

    public long countResults(String query, ReportFilter filter) {
        Connection connection = null;
        ResultSet resultSet;
        long count = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            setParametersIntoPreparedStatement(statement, filter);
            resultSet = statement.executeQuery();
            count = getCountFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return count;
    }

    private long getCountFromResultSet(ResultSet resultSet) throws SQLException {
        long count = 0;
        if (resultSet.next()) {
            count = resultSet.getLong(FIRST_COLUMN);
        }
        return count;
    }

    public ReportData createReportData(String query) {
        Connection connection = null;
        ReportData reportData = new ReportData();
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            resultSet = connection.prepareStatement(query).executeQuery();
            initializeReportMetaData(reportData, resultSet.getMetaData());
            generateDataFromResultSet(reportData, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return reportData;
    }

    public ReportData createReportData(String query, ReportFilter filter) {
        Connection connection = null;
        ReportData reportData = new ReportData();
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            setParametersIntoPreparedStatement(statement, filter);
            resultSet = statement.executeQuery();
            initializeReportMetaData(reportData, resultSet.getMetaData());
            generateDataFromResultSet(reportData, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return reportData;
    }

    public ReportData createReportData(String query, int pageNumber, Integer pageSize, ReportFilter filter) {
        Connection connection = null;
        ReportData reportData = new ReportData();
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            setParametersIntoPreparedStatement(statement, filter);
            statement.setInt(filter.getCriterionAmount() + LIMIT_POSITION, pageSize);
            statement.setInt(filter.getCriterionAmount() + OFFSET_POSITION, (pageNumber - 1) * pageSize);

//            ParameterMetaData parameterMetaData = statement.getParameterMetaData();
//            System.out.println("paparameterMetaData: " + parameterMetaData);
//            if (parameterMetaData != null) {
//                System.out.println("count: " + parameterMetaData.getParameterCount());
//                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
//                    System.out.println("param " + i + " type:" + parameterMetaData.getParameterType(i));
//                }
//            }

            resultSet = statement.executeQuery();
            initializeReportMetaData(reportData, resultSet.getMetaData());
            generateDataFromResultSet(reportData, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return reportData;
    }

    private void setParametersIntoPreparedStatement(PreparedStatement statement, ReportFilter filter) throws SQLException {
        List<MultipurposeValue> valueList = filter.getCriteria();
        for (int index = 1; index <= filter.getCriterionAmount(); index++) {
            mapper.setValueInStatement(index, statement, valueList.get(index - 1));
        }
    }

    private void generateDataFromResultSet(ReportData reportData, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            reportData.addRow(createRowData(resultSet, reportData));
        }
    }

    private RowData createRowData(ResultSet resultSet, ReportData reportData) throws SQLException {
        RowData rowData = new RowData();
        MultipurposeValue multiValue;
        for (int index = 1; index <= reportData.columnAmount(); index++) {
            multiValue = mapper.getDataFromColumn(resultSet, index, reportData.columnType(index));
            rowData.addColumn(index, multiValue);
        }
        return rowData;
    }

    private void initializeReportMetaData(ReportData data, ResultSetMetaData metaData) throws SQLException {
        DataType type;
        for (int index = 1; index <= metaData.getColumnCount(); index++) {
            type = mapper.convertColumnTypeToLanguageType(metaData.getColumnType(index));
            data.addMetaColumnInfo(index, metaData.getColumnName(index), type);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        Context initCtx;
        DataSource dataSource = null;
        try {
            initCtx = new InitialContext();
            dataSource = (DataSource) initCtx
                    .lookup(DATA_SOURCE);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public ReportData createReportData(String query, int pageNumber, Integer pageSize) {
        Connection connection = null;
        ReportData reportData = new ReportData();
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(LIMIT_POSITION, pageSize);
            statement.setInt(OFFSET_POSITION, (pageNumber - 1) * pageSize);
            ParameterMetaData parameterMetaData = statement.getParameterMetaData();
//            System.out.println("paparameterMetaData: " + parameterMetaData);
//            if (parameterMetaData != null) {
//                System.out.println("count: " + parameterMetaData.getParameterCount());
//                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
//                    System.out.println("param " + i + " type:" + parameterMetaData.getParameterType(i));
//                }
//            }

            resultSet = statement.executeQuery();
            initializeReportMetaData(reportData, resultSet.getMetaData());
            generateDataFromResultSet(reportData, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return reportData;
    }

    public ReportData createSizedReportData(String query, Integer maxSize) {
        Connection connection = null;
        ReportData reportData = new ReportData();
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(LIMIT_POSITION, maxSize);
            statement.setInt(OFFSET_POSITION, 0);
            resultSet = statement.executeQuery();
            initializeReportMetaData(reportData, resultSet.getMetaData());
            generateDataFromResultSet(reportData, resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return reportData;
    }
}
