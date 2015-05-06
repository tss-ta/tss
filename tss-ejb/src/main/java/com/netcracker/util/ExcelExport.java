
package com.netcracker.util;

import com.netcracker.entity.TaxiOrder;
import com.netcracker.util.reports.ReportsRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author maks
 */
public class ExcelExport {

    public File exportReportRows(int allOrders, List<ReportsRow> rows) throws IOException {
        return exportReportRows("TSS report", allOrders, rows);
    }

    public File exportReportRows(String header, int allOrders, List<ReportsRow> rows) throws IOException {
        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = "mytemp" + File.separator + "Report" + time + ".xls";
        return writeReport(header, allOrders, filePath, rows);
    }

    public File exportOrdersReport(Date begin, Date end, int allOrders, List<TaxiOrder> orders) throws IOException {
        String time = ((Long) Calendar.getInstance().getTimeInMillis()).toString();
        String filePath = "mytemp" + File.separator + "TOReport" + time + ".xls";
        return writeOrdersReport(begin, end, allOrders, filePath, orders);
    }


    public File writeReport(String header, int allOrders, String path, List<ReportsRow> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        File f = new File(path);

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

    public File writeOrdersReport(Date begin, Date end, int allOrders, String path, List<TaxiOrder> report) throws IOException {

        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        File f = new File(path);

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
        tableHeader.createCell(14).setCellValue("Driver");
        int rowNum = 4;
        for (TaxiOrder order : reportsRows) {
            Row row = sheet.createRow(rowNum);
//            row.createCell(0).setCellValue(order.getPrice());
//            row.createCell(1).setCellValue(order.getPayment()); //wtf? null
            row.createCell(2).setCellValue(dateToString(order.getBookingTime()));
            row.createCell(3).setCellValue(dateToString(order.getOrderTime()));
//            row.createCell(4).setCellValue(order.getMusicStyle());
            row.createCell(5).setCellValue(order.getEnumStatus().toString());
//            row.createCell(6).setCellValue(order.getComment());
            setNullableData(order.getMale(), row.createCell(7));
//            row.createCell(7).setCellValue(order.getMale()); //null?

            setNullableData(order.getSmoke(), row.createCell(6));
//            row.createCell(8).setCellValue(order.getSmoke());//null?
            row.createCell(9).setCellValue(order.getEnumCarCategory().toString());
            row.createCell(10).setCellValue(order.getAnimalTransport());//null?
            row.createCell(11).setCellValue(order.getWifi());//null?
            row.createCell(12).setCellValue(order.getConditioner());//null?
//            row.createCell(0).setCellValue(order.getPrice());

            rowNum++;
        }
        Row footer = sheet.createRow(rowNum + 2);
        footer.createCell(0).setCellValue("All new taxi orders booked by this period : ");
        footer.createCell(4).setCellValue(allOrders);
    }

    public String dateToString(Date date) { //maybe private?
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);
        return dateFormat.format(date);
    }

    private void setNullableData(Object data, Cell cell) {
        if (data == null) {
            cell.setCellValue("not set");
        } else {
            if (data instanceof Date)
                cell.setCellValue((Date) data);
            else if (data instanceof Boolean)
                cell.setCellValue((Boolean) data);
            else if (data instanceof String)
                cell.setCellValue((String) data);
            else if (data instanceof Number) {
                cell.setCellValue(((Number) data).doubleValue());
            }
        }
    }

//    public File write(String path, List<List> data) throws IOException {
//
//        HSSFWorkbook workbook = null;
//        HSSFSheet sheet = null;
//        File f = new File(path);
//
//        if (!f.exists()) {
//            workbook = new HSSFWorkbook();
//            sheet = workbook.createSheet("Sorting time");
//            formTable(sheet, data);
//
//        } else {
//            try (FileInputStream in = new FileInputStream(new File(path));) {
//
//                workbook = new HSSFWorkbook(in);
//                sheet = workbook.getSheetAt(0);
//                formTable(sheet, data);
//            }
//        }
//        try (FileOutputStream out = new FileOutputStream(f)) {
//            workbook.write(out);
//            return f;
//        }
//    }
//    private void writeTable(Sheet sheet, List<List> data) {
//        int rownum = 0;
//        for (List dataRow : data) {
//            Row row = sheet.getRow(rownum);
//            if (row == null) {
//                row = sheet.createRow(rownum);
//            }
//            rownum++;
//
//            int cellnum = 0;
//            for (Object obj : dataRow) {
//                Cell cell = row.getCell(cellnum);
//                if (cell == null) {
//                    cell = row.createCell(cellnum);
//                }
//                cellnum++;
//
//                if (obj instanceof Date)
//                    cell.setCellValue((Date) obj);
//                else if (obj instanceof Boolean)
//                    cell.setCellValue((Boolean) obj);
//                else if (obj instanceof String)
//                    cell.setCellValue((String) obj);
//                else if (obj instanceof Number) {
//                    cell.setCellValue(((Number) obj).doubleValue());
//                }
//            }
//        }
//    }
    }
    

