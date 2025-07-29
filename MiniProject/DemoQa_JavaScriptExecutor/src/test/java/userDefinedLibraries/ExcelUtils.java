package userDefinedLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream file;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	// Return number of rows in the sheet
	public static int getRowCount(String filename, String sheetname) throws IOException {

		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetname);
		int rows = ws.getLastRowNum();
		wb.close();
		file.close();
		return rows;

	}

	// Return number of cell in the sheet
	public static int getCellCount(String filename, String sheetname, int rownum) throws IOException {

		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetname);
		int cell = ws.getRow(rownum).getLastCellNum();
		wb.close();
		file.close();
		return cell;

	}

	// Returns a cell value as a String from the specified location
	public static String getCellValue(String filename, String sheetName, int rownum, int cellnum) throws IOException {

		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		String cellValue;
		try {
			DataFormatter formatter = new DataFormatter();
			cellValue = formatter.formatCellValue(cell);
		} catch (Exception e) {
			cellValue = "";
		}
		wb.close();
		file.close();
		return cellValue;

	}

	// Writes a value to a specific cell in the Excel sheet
	public static void setCellValue(String filename, String sheetName, int rownum, int cellnum, String cellValue)
			throws IOException {

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

	// Fills a specific cell with a green background (commonly used for marking
	// success)
	public static void fillGreenColor(String filename, String sheetName, int rownum, int cellnum) throws IOException {

		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(filename);
		wb.write(fo);
		wb.close();
		file.close();
		fo.close();

	}

	// Fills a specific cell with a red background (commonly used for marking
	// failure)
	public static void fillRedColor(String filename, String sheetName, int rownum, int cellnum) throws IOException {

		file = new FileInputStream(filename);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(cellnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(filename);
		wb.write(fo);
		wb.close();
		file.close();
		fo.close();

	}

	// Reads multiple rows and columns of data from a sheet and stores it in a 2D
	// array
	public static String[][] readMultipleData(String path, String sheetName) throws IOException {

		file = new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetName);

		int rowCount = ws.getLastRowNum();
		int cellCount = ws.getRow(0).getLastCellNum();

		String[][] data = new String[rowCount][10];

		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i <= 5; i++) {
			row = ws.getRow(i);
			for (int j = 0; j < cellCount - 1; j++) { // Adjusted range as per your original logic
				try {
					cell = row.getCell(j);
					data[i - 1][j] = formatter.formatCellValue(cell);
				} catch (Exception e) {
					data[i - 1][j] = "";
				}
			}
		}

		wb.close();
		file.close();
		return data;
	}

}
