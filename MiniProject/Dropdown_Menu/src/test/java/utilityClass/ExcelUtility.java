package utilityClass;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
	
	// File and workbook objects for reading/writing Excel files
	static FileInputStream fi;
	static FileOutputStream fo;
	static XSSFWorkbook wb;
	static XSSFSheet ws;
	static XSSFRow row;
	static XSSFCell cell;
	
	// Get the last used row number in a given sheet
	public static int getLastRowNumber(String fname,String sheetName) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowNum = ws.getLastRowNum();// Zero-based row count
		wb.close();
		fi.close();
		return rowNum;
	}
	
	// Get the number of columns (cells) in a given row
	public static int getLastCellNumber(String fname, String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		int cellNum = row.getLastCellNum(); //Alternative --getPhysicalNumberOfCells()
		wb.close();
		fi.close();
		return cellNum;
	}
	
	// Retrieve the value of a specific cell as a formatted string
	public static String getCellvalue(String fname, String sheetName, int rowNum, int cellNum) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		cell = row.getCell(cellNum);  //,row.missingCellValue.create Null_As_Blank
		String value ;
		try{
//			cell.toString();
			// Helps format numeric or date cells as readable text
			DataFormatter format = new DataFormatter();
			value = format.formatCellValue(cell); // Return empty string if cell is null or inaccessible
		}
		catch(Exception e) {
			value="";
		}
		wb.close();
		fi.close();
		return value;
	}
	
	// Set the value of a specific cell, or create it if it doesn't exist
	public static void setCellvalue(String fname, String sheetName, int rowNum, int cellNum, String value) throws IOException {
		fi = new FileInputStream(fname);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rowNum);
		
		// Create or overwrite cell
		cell = row.createCell(cellNum);
		cell.setCellValue(value); // Set new value
		fo = new FileOutputStream(fname);
		// Save changes
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
}
