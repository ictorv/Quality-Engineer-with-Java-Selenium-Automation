package com.TestUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil3 {
	// Path to Excel file
	String filePath;
	// File and workbook related objects
	FileInputStream fis;
	Workbook wb;
	Sheet sheet;
	 // List to store cell data from Excel
	List<String> data = new ArrayList<>();
	// Constructor: initializes workbook and sheet based on given path and sheet name
	public ExcelUtil3(String excelPath, String Sheet) {
		this.filePath = excelPath;
		File fp = new File(System.getProperty("user.dir")+"\\"+filePath);
		try {
			fis = new FileInputStream(fp);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(Sheet);
		}

		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// Get total number of rows with data in the sheet
	public int getRowCount() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}
	// Get total number of columns in the first row
	public int getColumnCount() {
		Row row = sheet.getRow(0);
		int colCount = row.getPhysicalNumberOfCells();
		return colCount;
	}
	 // Get data from a specific cell (rowNum, colNum)
	public String getCellData(int rowNum, int colNum) {
		Cell cell = sheet.getRow(rowNum).getCell(colNum,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String data = cell.toString();
		return data;
	}

	 // Read all data from the Excel sheet and print to console
	public void excelDataFetch() {
		int i,j;
		int rows = getRowCount();
		int column = getColumnCount();
		System.out.println("Number of rows present: "+rows);
		System.out.println("Number of columns present: "+column);
		// Iterate through each cell and collect data
		for(i=1; i<rows; i++) 
		{
			for(j=0; j<column; j++)
			{
				data.add(getCellData(i, j));
				System.out.print(getCellData(i, j).toString()+"\t");
			}
			System.out.println();
		}
	}
	// Return the list containing the fetched Excel data
	public List<String> getListArray() {
		return data;
	}
	// Write data into a specific cell of the Excel sheet
	public static void putCellData(String xlfile, String xlsheet, int rownum, int colnum, String input) throws IOException {
	    FileInputStream fi = new FileInputStream(xlfile);
	    XSSFWorkbook workbook = new XSSFWorkbook(fi);
	    XSSFSheet worksheet = workbook.getSheet(xlsheet);
	 // If sheet doesn't exist, create it
	    if (worksheet == null) {
	        worksheet = workbook.createSheet(xlsheet);
	    }
	
	    XSSFRow row = worksheet.getRow(rownum);
	    if (row == null) {
	        row = worksheet.createRow(rownum);
	    }
	    row.createCell(colnum).setCellValue(input);
	    fi.close();
	 
	    FileOutputStream fo = new FileOutputStream(xlfile);
	    workbook.write(fo);
	    fo.close();
	    workbook.close();
	}
	  // Clear all data from a given sheet
	public static void clearSheetData(String xlfile, String xlsheet) throws IOException {
	    FileInputStream fi = new FileInputStream(xlfile);
	    XSSFWorkbook workbook = new XSSFWorkbook(fi);
	    XSSFSheet worksheet = workbook.getSheet(xlsheet);
	    if (worksheet == null) {
	        worksheet = workbook.createSheet(xlsheet);
	    } else {
	        int lastRow = worksheet.getLastRowNum();
	        for (int i = 0; i <= lastRow; i++) {
	            XSSFRow row = worksheet.getRow(i);
	            if (row != null) {
	                worksheet.removeRow(row);
	            }
	        }
	    }
	    fi.close();

        // Save the cleared sheet
	    FileOutputStream fo = new FileOutputStream(xlfile);
	    workbook.write(fo);
	    fo.close();
	    workbook.close();
	}

}
