/**
 * Utility Class: ExcelUtil.java
 * 
 * Purpose:
 * - Provides utility functions to interact with Excel files using Apache POI.
 * - Supports reading and writing test data in an Excel format.
 * - Handles scenarios where rows or cells might be missing.
 *
 * Methods:
 * - getRowCount(String filename, String sheetName): Retrieves the total number of rows in the Excel sheet.
 * - getCellCount(String filename, String sheetName, int rowNum): Retrieves the total number of cells in a given row.
 * - getCellValue(String filename, String sheetName, int rowNum, int cellNum): Fetches a specific cell value from the Excel sheet.
 * - setCellValue(String filename, String sheetName, int rowNum, int cellNum, String cellValue): Updates a specific cell value in the Excel sheet.
 */

package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class ExcelUtil {

	
	public static FileInputStream file;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String filename,String sheetname) throws IOException {
		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetname);
		int rows = ws.getLastRowNum();
		wb.close();
		file.close();
		return rows;
	}
	public static int getCellCount(String filename,String sheetname,int rownum) throws IOException {
		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetname);
		int cell = ws.getRow(rownum).getLastCellNum();
		wb.close();
		file.close();
		return cell;
	}
	public static String getCellValue(String filename,String sheetName,int rownum,int cellnum) throws IOException{
		file = new FileInputStream(filename);
	    wb = new XSSFWorkbook(file);
	    ws = wb.getSheet(sheetName);
	    
	    row = ws.getRow(rownum);
	    if (row == null) return ""; // Handle missing row
	    
	    cell = row.getCell(cellnum);
	    if (cell == null) return ""; // Handle missing cell
	    
	    String cellValue = cell.toString();
	    
	    wb.close();
	    file.close();
	    return cellValue;
	}
	public static void setCellValue(String filename,String sheetName,int rownum,int cellnum,String cellValue) throws IOException{
		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(cellValue);
		fo = new FileOutputStream(filename);
		wb.write(fo);
		wb.close();
		file.close();
		fo.close();
	}
}
