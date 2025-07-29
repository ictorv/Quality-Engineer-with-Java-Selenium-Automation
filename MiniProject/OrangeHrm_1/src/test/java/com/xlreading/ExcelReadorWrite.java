package com.xlreading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelReadorWrite {
	static String filepath;
	static FileInputStream fis;
	//-----
	static File file;
	static FileOutputStream fos;
	//-----
	static Workbook wb;
	static Sheet sheet;
	static int rows;
	static int col;
	static File fp;
	static Row roww;
	static String sheetName;
	public ExcelReadorWrite(String filepath, String sheetName) {
		this.filepath = filepath;
		this.sheetName=sheetName;
		File f = new File(System.getProperty("user.dir") + "\\" + filepath);
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRowsCount() {
		rows = sheet.getPhysicalNumberOfRows();
		return rows;
	}
	
	public int getColumnCount() {
		Row row = sheet.getRow(0);
		col = row.getLastCellNum();
		return col;
	}
	
	public String getCellData(int rows, int column) {
		Cell cell = sheet.getRow(rows).getCell(column,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		return cell.toString();
	}
	
public static void setCellData(int row,int col,String data )throws IOException{
		
	fp=new File(System.getProperty("user.dir")+"\\"+filepath);	
	fis=new FileInputStream(fp);
		wb=new XSSFWorkbook(fis);;
		sheet=wb.getSheet(sheetName);
		roww=sheet.getRow(row);
		Cell cell=roww.createCell(col);
		cell.setCellValue(data);
		fos=new FileOutputStream(fp);
		wb.write(fos);
		wb.close();
		fos.close();
	}
 
	
	/*
	 * //--------------- public void setCellData(String data, int rowNum, int
	 * colNum) { try { Row row = sheet.getRow(rowNum); if (row == null) row =
	 * sheet.createRow(rowNum); Cell cell = row.getCell(colNum,
	 * Row.MissingCellPolicy.CREATE_NULL_AS_BLANK); cell.setCellValue(data);
	 * 
	 * 
	 * FileOutputStream fos = new FileOutputStream(file); wb.write(fos);
	 * fos.close(); } catch (IOException e) { e.printStackTrace(); } }
	 * //------------------
	 */	
}
