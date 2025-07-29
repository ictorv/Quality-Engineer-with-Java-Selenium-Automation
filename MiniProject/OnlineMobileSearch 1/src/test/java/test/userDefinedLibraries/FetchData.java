package test.userDefinedLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
 
 
public class FetchData {
 
	public static Object[][] list = null;
	private static XSSFWorkbook book;
	private static File file;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static XSSFSheet sheet;
	
	//method to read from the Excel file
	public static void extractFromExcel() {
	    try {

	         sheet = book.getSheetAt(0);
 
	        int rowCount = sheet.getPhysicalNumberOfRows();
 
	        list = new Object[rowCount][];
 
	        for (int i = 0; i < rowCount; i++) {
	            XSSFRow row1 = sheet.getRow(i);
	            list[i] = new Object[row1.getLastCellNum()];
	            for (int j = 0; j < row1.getLastCellNum(); j++) {
	                String cell1 = row1.getCell(j).toString();
	                list[i][j] = cell1;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }

	}

	public static void intializeTheFile() throws Exception{
		 file = new File(System.getProperty("user.dir") + "\\" + "src\\test\\java\\test\\data\\data.xlsx");
         fis = new FileInputStream(file);
        book = new XSSFWorkbook(fis);

	}

//method to write into the Excel file
	public static void writeIntoExcel(String element, int startRow, int startColumn) throws Exception {
	    sheet = book.getSheetAt(0);
	   
 
	    XSSFRow row = sheet.getRow(startRow);
	    if (row == null) {
	        row = sheet.createRow(startRow);
	    }
 
	    XSSFCell cell = row.getCell(startColumn);
	    if (cell == null) {
	        cell = row.createCell(startColumn);
	    }
 
	    cell.setCellValue(element);
 

	}
 
	
	public static void closeWorkBook() {
		try {
			fos = new FileOutputStream(file);
			book.write(fos);
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

 
 
}
 
 

