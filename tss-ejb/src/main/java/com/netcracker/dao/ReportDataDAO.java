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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Kyrylo Berehovyi
 */
public class ReportDataDAO {

    private DataSource dataSource = getDatasource();
    private ResultSetTypeMapper mapper = new ResultSetTypeMapper();

    public ReportData createReportData(String query) {
        Connection connection = null;
        ReportData reportData = new ReportData();
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            resultSet = connection.prepareStatement(query).executeQuery();
            initializeReportMetaData(reportData, resultSet.getMetaData());
//            convertDataFromResultSet(reportData, resultSet);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int count = metaData.getColumnCount();
//            System.out.println("count=" + count);
//            System.out.println("-------------------------------");
//            for (int i = 1; i <= count; i++) {
//                System.out.println("col_index=" + i);
//                System.out.println("col_name=" + metaData.getColumnName(i));
//                System.out.println("col_label=" + metaData.getColumnLabel(i));
//                System.out.println("col_type_index=" + metaData.getColumnType(i));
//                System.out.println("col_type_name=" + metaData.getColumnTypeName(i));
//                System.out.println("-------------------------------");
//            }
            convertDataFromResultSet(reportData, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                closeConnection(connection);
            }
        }
        return reportData;
    }

    private void convertDataFromResultSet(ReportData reportData, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            reportData.addRow(createRowData(resultSet, reportData));
        }
        System.out.println(reportData.getRowsAmount());
        System.out.println("ReportData:");
        System.out.println(reportData);
    }

    private RowData createRowData(ResultSet resultSet, ReportData reportData) throws SQLException {
        RowData rowData = new RowData();
        MultipurposeValue multiValue;
        for (int index = 1; index <= reportData.getColumnAmount(); index++) {
            multiValue = mapper.getDataFromColumn(resultSet, index, reportData.getColumnType(index));
            rowData.addColumn(index, multiValue);
        }
        System.out.println("====================================");
        System.out.println(rowData);
        System.out.println("====================================");
        return rowData;
    }

    private void initializeReportMetaData(ReportData data, ResultSetMetaData metaData) throws SQLException {
        DataType type;
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
//            System.out.println("Column type: " + metaData.getColumnType(i));
            type = mapper.convertColumnTypeToLanguageType(metaData.getColumnType(i));
            data.addMetaColumnInfo(i, metaData.getColumnName(i), type);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDatasource() {
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


}
