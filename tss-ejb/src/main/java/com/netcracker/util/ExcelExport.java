
package com.netcracker.util;

import com.netcracker.entity.TaxiOrder;
import com.netcracker.report.Report;
import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.container.RowData;
import com.netcracker.util.reports.ReportsRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author maks
 */
public class ExcelExport {

    private static final int HEADER_ROW_NUMBER = 0;
    private static final int HEADER_START_CELL_NUMBER = 0;
    private static final int TITLE_ROW_NUMBER = 2;
    private static final int DATA_ROW_NUMBER = 3;
    private static final int TITLE_START_CELL_NUMBER = 0;
    private static final int DATA_START_CELL_NUMBER = 0;
    private static final int DB_FIRST_COLUMN_INDEX = 1;

    public File exportReportRows(int allOrders, List<ReportsRow> rows) throws IOException {
        return exportReportRows("TSS report", allOrders, rows);
    }

    public File exportReportRows(String header, int allOrders, List<ReportsRow> rows) throws IOException {
        return writeReport(header, allOrders, createNewFile(), rows);

    }

    public File exportOrdersReport(Date begin, Date end, int allOrders, List<TaxiOrder> orders) throws IOException {
        return writeOrdersReport(begin, end, allOrders, createNewFile(), orders);
    }

    private File createNewFile () {
        String directory = "tsstemp";
        if (!(Files.isDirectory(Paths.get(directory)))) {
            new File(directory).mkdirs();
        }

        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = directory + File.separator + "Report" + time + ".xls"; //!!!!!!!!!
        return new File(filePath);
    }


    public File writeReport(String header, int allOrders, File f, List<ReportsRow> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;


        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Report");
        writeReportsRows(header, allOrders, sheet, report);

        try (FileOutputStream out = new FileOutputStream(f)) {
            workbook.write(out);
            return f;
        }
    }

    public File createExcelReport(Report report) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(report.getInfo().getName());
        File file = newFile();

        createHeader(sheet, report.getInfo().getDescription());
        createBody(sheet, report);

        writeWorkBook(workbook, file);

        return file;
    }

    private void writeWorkBook(HSSFWorkbook workbook, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File newFile() {
        File file = createNewFile();
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void createBody(HSSFSheet sheet, Report report) {
        createBodyTitle(sheet, report);
        createBodyData(sheet, report);
    }

    public void createBodyTitle(HSSFSheet sheet, Report report) {
        Row title = sheet.createRow(TITLE_ROW_NUMBER);
//        System.out.println(report);
        for (int i = DB_FIRST_COLUMN_INDEX; i <= report.getData().columnAmount(); i++) {
            title.createCell(DATA_START_CELL_NUMBER + i).setCellValue(report.getData().columnName(i));
        }
    }

    public void createBodyData(HSSFSheet sheet, Report report) {
        Row row;
        int dataRowNumber = DATA_ROW_NUMBER;
        for (RowData rowData : report.getData().getRows()) {
            row = sheet.createRow(dataRowNumber);
            for (int i = DB_FIRST_COLUMN_INDEX; i <= rowData.columnAmount(); i++) {
                setCellValue(row.createCell(DATA_START_CELL_NUMBER + i), rowData.getColumn(i));
            }
            dataRowNumber++;
        }
    }

    private void setCellValue(Cell cell, MultipurposeValue column) {
        switch (column.getType()) {
            case STRING: cell.setCellValue(column.getStringValue());
                break;
            case INTEGER: cell.setCellValue(column.getIntValue());
                break;
            case LONG: cell.setCellValue(column.getLongValue());
                break;
            case DOUBLE: cell.setCellValue(column.getDoubleValue());
                break;
            case BOOLEAN: cell.setCellValue(column.isBooleanValue());
                break;
            case TIMESTAMP: cell.setCellValue(column.getTimestampValue());
                break;
        }
    }

    private void createHeader(HSSFSheet sheet, String description) {
        Row row = sheet.createRow(HEADER_ROW_NUMBER);
        row.createCell(HEADER_START_CELL_NUMBER).setCellValue(description);
    }

    private void writeReportsRows(String header, int allOrders, Sheet sheet, List<ReportsRow> reportsRows) {
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue(header);
        firstRow.createCell(4).setCellValue("Report was created by Taxi Service System : ");
        firstRow.createCell(8).setCellValue(dateToString(Calendar.getInstance().getTime()));
        Row tableHeader = sheet.createRow(1);
        tableHeader.createCell(0).setCellValue("Parameter");
        tableHeader.createCell(1).setCellValue("+");
        tableHeader.createCell(2).setCellValue("-");
        int rowNum = 2;
        for (ReportsRow dataRow : reportsRows) {
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(dataRow.getName());
            row.createCell(1).setCellValue(dataRow.getValue());
            row.createCell(2).setCellValue(allOrders - dataRow.getValue());
            rowNum++;
        }
        Row footer = sheet.createRow(rowNum + 2);
        footer.createCell(0).setCellValue("All analyzed taxi orders : ");
        footer.createCell(4).setCellValue(allOrders);
    }

    public File writeOrdersReport(Date begin, Date end, int allOrders, File f, List<TaxiOrder> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;

        if (f.exists()) {
            f.delete();
            f.createNewFile();
        }
        f.createNewFile();

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Report");
        writeOrdersTable(begin, end, allOrders, sheet, report);

        try (FileOutputStream out = new FileOutputStream(f)) {
            workbook.write(out);
            return f;
        }
    }

    private void writeOrdersTable(Date begin, Date end, int allOrders, Sheet sheet, List<TaxiOrder> reportsRows) {
        Row firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("New orders by period report");
        firstRow.createCell(4).setCellValue("Report was created by Taxi Service System : ");
        firstRow.createCell(8).setCellValue(dateToString(Calendar.getInstance().getTime()));
        Row periodRow = sheet.createRow(1);
        periodRow.createCell(0).setCellValue("Start of period:");
        periodRow.createCell(3).setCellValue(dateToString(begin));
        periodRow.createCell(5).setCellValue("End of period:");
        periodRow.createCell(8).setCellValue(dateToString(end));
        Row tableHeader = sheet.createRow(3);
        tableHeader.createCell(0).setCellValue("Price");
        tableHeader.createCell(1).setCellValue("Payment type");
        tableHeader.createCell(2).setCellValue("Booking time");
        tableHeader.createCell(3).setCellValue("Order type");
        tableHeader.createCell(4).setCellValue("Music style");
        tableHeader.createCell(5).setCellValue("Status");
        tableHeader.createCell(6).setCellValue("Comment");
        tableHeader.createCell(7).setCellValue("Male/Female (true/false)");
        tableHeader.createCell(8).setCellValue("Is driver smoking");
        tableHeader.createCell(9).setCellValue("Car category");
        tableHeader.createCell(10).setCellValue("Animalable car");
        tableHeader.createCell(11).setCellValue("Wi-Fi");
        tableHeader.createCell(12).setCellValue("Conditioner");
        tableHeader.createCell(13).setCellValue("Service");
//        tableHeader.createCell(14).setCellValue("Driver");
        int rowNum = 4;
        for (TaxiOrder order : reportsRows) {
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(order.getPrice());
            row.createCell(1).setCellValue(order.getPayment());
            row.createCell(2).setCellValue(dateToString(order.getBookingTime()));
            row.createCell(3).setCellValue(dateToString(order.getOrderTime()));
            writeNullableValue(row.createCell(4), order.getMusicStyle());
            row.createCell(5).setCellValue(order.getEnumStatus().toString());
            writeNullableValue(row.createCell(6), order.getComment());
            writeNullableValue(row.createCell(7), order.getMale());
            writeNullableValue(row.createCell(6), order.getSmoke());
            writeNullableValue(row.createCell(9), order.getEnumCarCategory().toString());
            writeNullableValue(row.createCell(10), order.getAnimalTransport());//null?
            writeNullableValue(row.createCell(11), order.getWifi());//null?
            writeNullableValue(row.createCell(12), order.getConditioner());//null?


            rowNum++;
        }
        Row footer = sheet.createRow(rowNum + 2);
        footer.createCell(0).setCellValue("All new taxi orders booked by this period : ");
        footer.createCell(4).setCellValue(allOrders);
    }

    public void writeNullableValue (Cell cell, Object obj){
        if (obj == null){
            cell.setCellValue("");
        } else {

            if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Number) {
                    cell.setCellValue(((Number) obj).doubleValue());
                } else {
                cell.setCellValue(obj.toString());
            }
        }
    }

    public String dateToString(Date date) { //maybe private?
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
        return dateFormat.format(date);
    }

}
    

