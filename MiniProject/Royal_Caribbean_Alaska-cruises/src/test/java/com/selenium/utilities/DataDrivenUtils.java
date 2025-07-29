package com.selenium.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/*
 * -----------------------------------------------------------------------------
 * File Name   : DataDrivenUtils.java
 * Package     : com.selenium.alertUtilities
 * Created Date: 18th June, 2025
 * Author      : Lava Prasad G V
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
	public static Random ran = new Random();
	static String xfile = System.getProperty("user.dir")+"\\TestData\\MiniProjectExcecutionOutput.xlsx";
	public static String propertiesPath = System.getProperty("user.dir")+"\\TestData\\config.properties";  

	static String sheetName = "Sheet1";
	
	public static String getURL() {
		try {
			propertiesfi = new FileInputStream(propertiesPath);
			pObj = new Properties();
			pObj.load(propertiesfi);
			return pObj.getProperty("URL");
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	
	
	
	public static Set<Integer> getRandNumSet(int num) {

		Set<Integer> uniqueNumbers = new HashSet<>();
		while (uniqueNumbers.size()<4) {
			int n = ran.nextInt(num);
			uniqueNumbers.add(n);
		}		
		return uniqueNumbers;
	}
	
	public static int getRandNum(int n) {
		return ran.nextInt(n);
	}
	
	public static void writeDataIntoExcel( List<String> message) throws Exception{
		
			fi = new FileInputStream(xfile);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			int r = sheet.getPhysicalNumberOfRows();
			row = sheet.createRow(++r);
			
			for(int i=0;i<message.size();i++) {
				row.createCell(i).setCellValue(message.get(i));
			}
		
			
			fo = new FileOutputStream(xfile);
			workbook.write(fo);
			workbook.close();
			fo.close();
					
	}
	
	
}
