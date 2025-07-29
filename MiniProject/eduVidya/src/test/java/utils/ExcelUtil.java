/**
 * File Name: ExcelUtil.java
 * Description:
 * This utility class provides methods to read from and write to Excel files (.xlsx format).
 * It supports operations such as retrieving row and cell counts, reading cell values,
 * and updating cell content.

 * Purpose:
 * Used for managing test data and results in Excel sheets during automated test execution.

 * Methods:
 * - getLastRowNumber: Returns the last row number in the sheet.
 * - getLastCellNumber: Returns the last cell number in a row.
 * - getCellvalue: Retrieves the value of a specific cell.
 * - setCellvalue: Writes a value to a specific cell.
 */

package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	static FileInputStream fi;
	static FileOutputStream fo;
	static XSSFWorkbook wb;
	static XSSFSheet ws;
	static XSSFRow row;
	static XSSFCell cell;

	public static int getLastRowNumber(String fname, String sheetName) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowNum = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowNum;
	}

	public static int getLastCellNumber(String fname, String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		int cellNum = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellNum;
	}

	public static String getCellvalue(String fname, String sheetName, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(cellNum);
		String value;
		try {
//			cell.toString();
			DataFormatter format = new DataFormatter();
			value = format.formatCellValue(cell);
		} catch (Exception e) {
			value = "";
		}
		wb.close();
		fi.close();
		return value;
	}

	public static void setCellvalue(String fname, String sheetName, int rowNum, int cellNum, String value)
			throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue(value);
		fo = new FileOutputStream(fname);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

}
