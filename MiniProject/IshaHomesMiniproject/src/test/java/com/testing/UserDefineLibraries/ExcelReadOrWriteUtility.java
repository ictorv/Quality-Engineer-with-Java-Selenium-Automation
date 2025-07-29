package com.testing.UserDefineLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To write data into Excel based on column name in Selenium Java using Apache
 * POI, you'll need to:
 * 
 * Locate the column index by column header name.
 * 
 * Locate the row by index (or add new row).
 * 
 * Write the value into the correct cell.
 * 
 * Save the workbook.
 */

public class ExcelReadOrWriteUtility {
	String filePath;
	static File fp;
	static FileInputStream fi;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static String sheetName;
	static FileOutputStream fo;
	static XSSFRow row;
	static XSSFCell cell;

	public ExcelReadOrWriteUtility(String excelPath, String sheetName) {
		// TODO Auto-generated constructor stub
		this.filePath = excelPath;
		ExcelReadOrWriteUtility.sheetName = sheetName;
	}

	public void excelRead(String excelPath, String sheetName) {
		fp = new File(System.getProperty("user.dir") + "\\" + filePath);
		try {
			fi = new FileInputStream(fp);
			try {
				wb = new XSSFWorkbook(fi);
				sheet = wb.getSheet(sheetName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	public int getColumnCount() {
		Row row = sheet.getRow(0);
		int columnNum = row.getLastCellNum();
		return columnNum;
	}

	public String getCellData(int rowNum, int colNum) {// avoid nukl pointer ecpetio we have this belpow ,methods
		Cell cell = sheet.getRow(rowNum).getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String data = cell.toString();
		return data;
	}

	public static void writeDataIntoExcel(String sheetName, int r, int c, String message) throws IOException {
		try {
			fi = new FileInputStream(fp);

			wb = new XSSFWorkbook(fi);

			sheet = wb.getSheet(sheetName);

			// Get the target row (create if it doesn't exist)
			row = sheet.getRow(r);
			// Write the value to the target cell
			cell = row.createCell(c);
			cell.setCellValue(message);

			fo = new FileOutputStream(fp);
			wb.write(fo);
			wb.close();

			// Write back to the Excel file
			fo.close(); // Close input stream before writing

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeFile() {
		try {
			fo.close();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
