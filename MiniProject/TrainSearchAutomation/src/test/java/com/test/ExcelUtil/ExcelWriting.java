package com.test.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * This Utility class is used to write data into the Excel sheet and also we can apply styles.
 * The target Excel file and sheet name are provided via constructor parameters.
 * If the specified sheet does not exist, it will be created automatically.
 */


public class ExcelWriting {
	String filePath;
	String sheetName;
	FileOutputStream fos;
	FileInputStream fis;
	Workbook wrbk;
	Sheet sht;
	File file;
	Row rowObj;
	
	
	public ExcelWriting(String excelPath,String sheetName) {
		filePath = excelPath;
		this.sheetName = sheetName;
		file = new File(System.getProperty("user.dir") + "\\" + filePath);
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
	
	public void setCellData(int row,int col,String data) throws IOException{
		file = new File(System.getProperty("user.dir") + "\\" + filePath);
		fis = new FileInputStream(file);
		wrbk = new XSSFWorkbook(fis);
		if(wrbk.getSheet("Results") == null) {
			sht = wrbk.createSheet("Results");
		}
		sht = wrbk.getSheet("Results");
		rowObj = sht.createRow(row);
		Cell cell = rowObj.createCell(col);
		cell.setCellValue(data);
		fos = new FileOutputStream(file);
		wrbk.write(fos);
	}
	
	public void setColorCellData(int row,int col) throws IOException{
		file = new File(System.getProperty("user.dir") + "\\" + filePath);
		fis = new FileInputStream(file);
		wrbk = new XSSFWorkbook(fis);
		
		if(wrbk.getSheet("Results") == null) {
			sht = wrbk.createSheet("Results");
		}else {
			sht = wrbk.getSheet("Results");
		}
		
		rowObj = sht.getRow(row);
		if(rowObj == null) {
			rowObj = sht.createRow(row);
		}
		
		Cell cell = rowObj.getCell(col);
		if(cell == null) {
			cell = rowObj.createCell(col);
		}
		
		CellStyle style = wrbk.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fos = new FileOutputStream(file);
		wrbk.write(fos);
	}
	
	public void closeFile() {
		try {
			fis.close();
			fos.close();
			wrbk.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
