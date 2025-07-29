package TestData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
 
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
 * DataDrivenUtils class provides utility methods for reading configuration properties
 * and writing test data into Excel files. It supports data-driven testing by allowing
 * dynamic retrieval of test parameters and logging of test results.
 * 
 * Features:
 * - Read application URL from a properties file
 * - Write test output data into specific cells of an Excel sheet
 * 
 * Dependencies:
 * - Apache POI for Excel file manipulation
 * - Java Properties for configuration file handling
 */

public class DataDrivenUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static Properties pObj;
	public static CellStyle style;
	
	public static String xfile = System.getProperty("user.dir")+ "\\src\\test\\java\\Testdata\\MiniProject_TestData.xlsx";
	public static String pfile = System.getProperty("user.dir")+ "\\src\\test\\java\\Testdata\\config.properties";
	public static String sheetName = "FlipKart TestData";
	public static FileInputStream propertiesfi;
	
	public static String getURL() {
		try {
			propertiesfi = new FileInputStream(pfile);
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
	
}