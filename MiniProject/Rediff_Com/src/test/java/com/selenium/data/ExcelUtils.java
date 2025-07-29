
package com.selenium.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.selenium.rediffUtilities.ExtentReportManager;
import com.selenium.rediffUtilities.ScreenShot;
import org.testng.Assert;

public class ExcelUtils {
    String fileName;
    FileInputStream fis;
    FileOutputStream fos;
    Workbook wb;
    Sheet sheet;
    CellStyle style;

    public int failedValidationCount = 0;
    public List<String> failedValidations = new ArrayList<>();

    //Constructor
    public ExcelUtils(String excelName) 
    {
        this.fileName = System.getProperty("user.dir") + "\\src\\test\\java\\com\\selenium\\data\\" + excelName;
        try 
        {
            fis = new FileInputStream(new File(fileName));
            wb = new XSSFWorkbook(fis);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    //Counting total no of Rows
    public int getRowCount(String sheetName) 
    {
        sheet = wb.getSheet(sheetName);
        return sheet.getPhysicalNumberOfRows();
    }
    
    //Counting total no of Columns/Cells
    public int getColumnCount(String sheetName) 
    {
        sheet = wb.getSheet(sheetName);
        return sheet.getRow(0).getLastCellNum();
    }

    //Reading Cell data
    public String getCellData(String sheetName, int rownum, int columnum) 
    {
        sheet = wb.getSheet(sheetName);
        Cell cell = sheet.getRow(rownum).getCell(columnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        try 
        {
            return new DataFormatter().formatCellValue(cell);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return "Missing Data";
        }
    }
    
    //Writing into Cell
    public void setCellData(String sheetName, int rowIndex, int columnIndex, String data) throws IOException 
    {
        sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rowIndex);
        if (row == null) row = sheet.createRow(rowIndex);
        Cell cell = row.getCell(columnIndex);
        if (cell == null) cell = row.createCell(columnIndex);
        cell.setCellValue(data);

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cell.setCellStyle(cellStyle);
        sheet.autoSizeColumn(columnIndex);

        fis.close();
        fos = new FileOutputStream(fileName);
        wb.write(fos);
        fos.close();
    }

    //Filing Green color
    public void fillGreenColor(String sheetName, int rownum, int columnIndex) throws IOException 
    {
        fillColor(sheetName, rownum, columnIndex, IndexedColors.GREEN);
    }

    //Filling Red Color
    public void fillRedColor(String sheetName, int rownum, int columnIndex) throws IOException 
    {
        fillColor(sheetName, rownum, columnIndex, IndexedColors.RED);
    }

    //Main Color Method
    private void fillColor(String sheetName, int rownum, int columnIndex, IndexedColors color) throws IOException 
    {
        sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rownum);
        if (row == null) row = sheet.createRow(rownum);
        Cell cell = row.getCell(columnIndex);
        if (cell == null) cell = row.createCell(columnIndex);

        style = wb.createCellStyle();
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(style);

        fos = new FileOutputStream(fileName);
        wb.write(fos);
        fos.close();
    }

    
    //Validation 
    

    public void validation(String sheetName, int rownum, int columnIndex, String actual, String expected,
                           ExtentReportManager erm, WebDriver driver, String screenshotName) {
        try {
            try {
                Assert.assertEquals(actual, expected);
                setCellData(sheetName, rownum, columnIndex, "PASS");
                fillGreenColor(sheetName, rownum, columnIndex);
                erm.logPass("Validation Passed: Expected = " + expected + ", Actual = " + actual);
            } catch (AssertionError ae) {
                setCellData(sheetName, rownum, columnIndex, "FAIL");
                fillRedColor(sheetName, rownum, columnIndex);
                failedValidationCount++;
                failedValidations.add("Row " + rownum + ": Expected [" + expected + "] but got [" + actual + "]");

                String screenshotPath = ScreenShot.screenShotTC(driver, screenshotName);

                String failMessage = "<b> Validation Failed at Row " + rownum + "</b><br>" +
                                     "<ul>" +
                                     "<li><b>Expected:</b> " + expected + "</li>" +
                                     "<li><b>Actual:</b> " + actual + "</li>" +
                                     "</ul>" +
                                     "<br><b> Screenshot:</b><br>" +
                                     "<img src='." + screenshotPath + "' height='400' width='800'/>";

                erm.testLogger.log(Status.FAIL, failMessage);

                // Optionally rethrow to fail the test
                //throw ae;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //File Closing
    public void closeFile() 
    {
        try 
        {
            if (fos != null) fos.close();
            if (wb != null) wb.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
