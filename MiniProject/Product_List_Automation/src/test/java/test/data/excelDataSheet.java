package test.data;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class excelDataSheet 
{
	
	String fileName;
	static FileInputStream fis;
	static FileOutputStream fos;
	static Workbook wb;
	Sheet sheet;

	public static Sheet ws;
	public static Row row;
	public static Cell cell;
	public static String filepath;
	public static String sheetName;

	excelDataSheet eds;
	public excelDataSheet(String excelName, String sheetName) 
	{
		this.fileName = excelName;
		File filep = new File(System.getProperty("user.dir")+"\\"+excelName);
		
		try {
			fis = new FileInputStream(filep);
			try {
				wb = new XSSFWorkbook(fis);
				sheet = wb.getSheet(sheetName);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int getRowCount() {
		int rowcount = sheet.getPhysicalNumberOfRows();
		
		return rowcount;
	}

	public int getColumnCount() {
		Row row = sheet.getRow(0);
		int columnum = row.getLastCellNum() - 1;
		return columnum;
	}

	public String getCellData(int rownum, int columnum) {
		Cell cell = sheet.getRow(rownum).getCell(columnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String data = cell.toString();
		return data;
	}

	public static void setCellData(String filepath, String sheetName, int rownum, int colnum, String data)
			throws IOException {
		new excelDataSheet(filepath, sheetName);
		fis = new FileInputStream(filepath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		if(ws.getRow(rownum)==null) {
			row=ws.createRow(rownum);
		}
		row = ws.getRow(rownum);
 
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(filepath);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
 
	}

	public void closeFile() {
		try {
			fos.close();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}


