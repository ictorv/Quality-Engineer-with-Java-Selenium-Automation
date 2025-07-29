package com.test.data;
/**
 * Utility class for reading and writing data to Excel (.xlsx) files using Apache POI.
 * Supports dynamic cell access by row and column index.
 * Used for managing test data and logging test results.
 */


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataSheet {

    // Read cell data from given file, sheet, row, and column
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        String cellData = "";
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    Cell cell = row.getCell(colNum);
                    if (cell != null) {
                        cellData = getCellValueAsString(cell);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + filePath);
            e.printStackTrace(); // Prints full error details
        }
        return cellData;
    }

    // Convert cell value to string for various cell types
    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    // Write data to specific cell in Excel sheet
    public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String result) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return;

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(result);

            // Write changes back to file
            try (java.io.FileOutputStream fos = new java.io.FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            System.out.println("Error writing to Excel file: " + filePath);
            e.printStackTrace();
        }
    }
}
