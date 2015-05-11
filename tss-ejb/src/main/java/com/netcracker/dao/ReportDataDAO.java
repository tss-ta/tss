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

//            resultSet = connection.prepareStatement("select count(id) from car").executeQuery();
//
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            System.out.println("==================================");
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.println("column name: " + metaData.getColumnName(i));
//                System.out.println("column type: " + metaData.getColumnTypeName(i));
//                System.out.println("column type index: " + metaData.getColumnType(i));
//            }

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
//        System.out.println(reportData.getRowsAmount());
//        System.out.println("ReportData:");
//        System.out.println(reportData);
    }

    private RowData createRowData(ResultSet resultSet, ReportData reportData) throws SQLException {
        RowData rowData = new RowData();
        MultipurposeValue multiValue;
        for (int index = 1; index <= reportData.getColumnAmount(); index++) {
            System.out.println("index=" + index);
            System.out.println("colType=" + reportData.getColumnType(index));
            multiValue = mapper.getDataFromColumn(resultSet, index, reportData.getColumnType(index));
            rowData.addColumn(index, multiValue);
        }
//        System.out.println("====================================");
//        System.out.println(rowData);
//        System.out.println("====================================");
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
                    .lookup("java:jboss/datasources/PostgreSQLDS");
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