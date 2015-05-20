package com.netcracker.dao;

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

/**
 * @author Kyrylo Berehovyi
 */

public class ReportDataDAO {

    private static final int FIRST_COLUMN = 1;
    private static final int LIMIT_POSITION = 1;
    private static final int OFFSET_POSITION = 2;
    public static final String DATA_SOURCE = "java:jboss/datasources/PostgreSQLDS";

    private DataSource dataSource = getDataSource();
    private ResultSetTypeMapper mapper = new ResultSetTypeMapper();

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
            System.out.println("paparameterMetaData: " + parameterMetaData);
            if (parameterMetaData != null) {
                System.out.println("count: " + parameterMetaData.getParameterCount());
                for (int i = 1; i <= parameterMetaData.getParameterCount(); i++) {
                    System.out.println("param " + i + " type:" + parameterMetaData.getParameterType(i));
                }
            }

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
