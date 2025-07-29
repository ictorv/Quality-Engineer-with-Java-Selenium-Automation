package com.test.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * It is primarily used for reading data from Excel and data-driven testing scenarios 
 * where test inputs are maintained in Excel format. The class supports '.xlsx' files
 * and uses the XSSF API from Apache POI.
 */


public class ExcelReading {
	String filePath;
	FileInputStream fis;
	Workbook wrbk;
	Sheet sht;
	ExcelReading exclReadObj;
	Object[][] excelData;
	
	public ExcelReading(String excelPath, String sheetName) {
		filePath = excelPath;
		File file = new File(System.getProperty("user.dir") + "\\" + filePath);
		try {
			fis = new FileInputStream(file);
			wrbk = new XSSFWorkbook(fis);
			sht = wrbk.getSheet(sheetName);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		int rowCount = sht.getPhysicalNumberOfRows();
		return rowCount;
	}
	
	public int getColCount() {
		Row row = sht.getRow(0);
		int colCount = row.getLastCellNum();
		return colCount;
	}
	
	public String getCellData(int rowNum, int colNum) {
		Cell cell = sht.getRow(rowNum).getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		return cell.toString();
	}
	
	public Object[][] testDataRead(String filepath,String sheetName){
		exclReadObj = new ExcelReading(filepath,sheetName);
		int rows = exclReadObj.getRowCount();
		int col = exclReadObj.getColCount();
		excelData = new Object[rows][col];
		for(int i=1;i< rows;i++) {
			for(int j=0;j<col;j++) {
				excelData[i][j] = exclReadObj.getCellData(i,j);
			}
		}
		return excelData;
	}
	
	public void closeFile() {
		try {
			fis.close();
			wrbk.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
