package com.selenium.alertUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
 * -----------------------------------------------------------------------------
 * File Name   : DataDrivenUtils.java
 * Package     : com.selenium.alertUtilities
 * Author      : Adinath Khose
 * 
 * Description :
 * This utility class provides methods for data-driven testing using Excel and 
 * Properties files. It includes functionalities to:
 * 
 * - Read configuration values (like URL) from a `.properties` file.
 * - Write test execution messages or results into an Excel sheet.
 * - Apply conditional formatting (green/red color) to Excel cells based on test status.
 * 
 * Dependencies:
 * - Apache POI library for Excel file operations.
 * - Java Properties class for reading `.properties` files.
 * 
 * Usage:
 * - Call `getURL(path)` to retrieve the URL from a properties file.
 * - Use `writeDataIntoExcel(row, column, message)` to log test results.
 * - Use `fillGreenColor(row, column)` or `fillRedColor(row, column)` to highlight cells.
 * 
 * -----------------------------------------------------------------------------
 */


public class DataDrivenUtils {
	
	public static FileInputStream propertiesfi;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static Properties pObj;
	public static CellStyle style;
	
	static String xfile = System.getProperty("user.dir")+"\\testdata\\MiniProjectExcecutionOutput.xlsx";
	static String sheetName = "Sheet1";
	
	public static String getURL(String path) {
		try {
			propertiesfi = new FileInputStream(path);
			pObj = new Properties();
			pObj.load(propertiesfi);
			return pObj.getProperty("URL");
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public static void writeDataIntoExcel(int r, int c, String message) {
		try {
			fi = new FileInputStream(xfile);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(r);
			cell = row.createCell(c);
			cell.setCellValue(message);
			fo = new FileOutputStream(xfile);
			workbook.write(fo);
			workbook.close();
			fo.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	public static void fillGreenColor(int r, int c) {
		try {
			fi = new FileInputStream(xfile);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(r);
			cell = row.getCell(c);
			
			style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);
			
			fo = new FileOutputStream(xfile);
			workbook.write(fo);
			workbook.close();
			fo.close();
			fi.close();
			
		} catch (IOException e) {
			
			System.out.println("Unable to Color");
		}
		
	}
	public static void fillRedColor(int r, int c) {
		try {
			fi = new FileInputStream(xfile);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(r);
			cell = row.getCell(c);
			
			style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);
			
			fo = new FileOutputStream(xfile);
			workbook.write(fo);
			workbook.close();
			fo.close();
			fi.close();
			
		} catch (IOException e) {
			
			System.out.println("Unable to Color");
		}
	}
	
}
