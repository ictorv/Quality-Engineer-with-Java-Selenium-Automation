package com.selenium.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	public static String xfile=System.getProperty("user.dir")+"\\TestData\\ExcelSheet.xlsx";
	public static String urlfile = System.getProperty("user.dir")+"\\TestData\\config.properties";
	public static String sheetName="Sheet1";
	
	public static String getURL() {
		try {
			propertiesfi = new FileInputStream(urlfile);
			pObj = new Properties();
			pObj.load(propertiesfi);
			return pObj.getProperty("URL");
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "";
		}
	}


		public static void writeDataIntoExcel(int rowIndex, String browserName, String message) {
		    try {
			        int columnIndex;

			        // Map browser name to column index
			        switch (browserName.toLowerCase()) {
		            case "edge":
		                columnIndex = 3; // Column D
		                break;
		            case "chrome":
		                columnIndex = 4; // Column E
		                break;
		            default:
		                columnIndex = 0; // Fallback to Column A
		        }

			        fi = new FileInputStream(xfile);
			        workbook = new XSSFWorkbook(fi);
			        sheet = workbook.getSheet(sheetName);

			        // Assume row is already created elsewhere	
			        row = sheet.getRow(rowIndex);

			        // Directly overwrite cell content
			        cell = row.createCell(columnIndex, CellType.STRING);
			        cell.setCellValue(message);

			        fo = new FileOutputStream(xfile);
			        workbook.write(fo);
			        workbook.close();
			        fo.close();
		    } catch (IOException e) {
			        System.out.println(e.getMessage());
			}


		
	}
	
	
	
	
	

}

