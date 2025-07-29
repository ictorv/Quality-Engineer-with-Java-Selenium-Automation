package com.urban.utillities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	static String xfile = System.getProperty("user.dir") + "\\src\\test\\resources\\ProjectData.xlsx";

	public static String readDataFromExcel(String sheetName, int r, int c) {
		String data = "";
		try {
			fi = new FileInputStream(xfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(r);
			cell = row.getCell(c);

			if (cell != null) {
				if (cell.getCellType() == CellType.NUMERIC)
					data = String.valueOf((int) cell.getNumericCellValue());
				else
					data = cell.toString();

			}
			wb.close();
			fi.close();

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return data;

	}

	public static void writeDataIntoExcel(String sheetName, int r, int c, String message) {
		try {
			fi = new FileInputStream(xfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(r);
			if (row == null)
				row = ws.createRow(r);
			cell = row.createCell(c);

			try {
				double value = Double.parseDouble(message);
				cell.setCellValue(value);

			} catch (NumberFormatException e) {

				cell.setCellValue(message);
			}

			fo = new FileOutputStream(xfile);
			wb.write(fo);
			wb.close();
			fo.close();

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public static void fillGreenColor(String sheetName, int r, int c) {
		try {
			fi = new FileInputStream(xfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(r);
			cell = row.getCell(c);

			style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);

			fo = new FileOutputStream(xfile);
			wb.write(fo);
			wb.close();
			fo.close();
			fi.close();

		} catch (IOException e) {

			System.out.println("Unable to Color");
		}

	}

	public static void fillRedColor(String sheetName, int r, int c) {
		try {
			fi = new FileInputStream(xfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(sheetName);
			row = ws.getRow(r);
			cell = row.getCell(c);

			style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);

			fo = new FileOutputStream(xfile);
			wb.write(fo);
			wb.close();
			fo.close();
			fi.close();

		} catch (IOException e) {

			System.out.println("Unable to Color");
		}
	}

	// Row Count(Tathagat)
	public static int getRowCount(String xlfile, String xlsheet) throws IOException

	{

		fi = new FileInputStream(xlfile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xlsheet);

		int rowcount = ws.getLastRowNum();

		wb.close();

		fi.close();

		return rowcount;

	}

	// Cell Count(Tathagat)
	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException

	{

		fi = new FileInputStream(xlfile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xlsheet);

		row = ws.getRow(rownum);

		int cellcount = row.getLastCellNum();

		wb.close();

		fi.close();

		return cellcount;

	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException

	{

		fi = new FileInputStream(xlfile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xlsheet);

		row = ws.getRow(rownum);

		cell = row.getCell(colnum);

		String data;

		try

		{

//			data=cell.toString();

			DataFormatter formatter = new DataFormatter();

			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of
													// the cell type.

		}

		catch (Exception e)

		{

			data = "";

		}

		wb.close();

		fi.close();

		return data;

	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException

	{

		fi = new FileInputStream(xlfile);

		wb = new XSSFWorkbook(fi);

		ws = wb.getSheet(xlsheet);

		row = ws.getRow(rownum);

		if (row == null) {

			row = ws.createRow(rownum);

		}

		row = ws.getRow(rownum);

		cell = row.createCell(colnum);

		cell.setCellValue(data);

		fo = new FileOutputStream(xlfile);

		wb.write(fo);

		wb.close();

		fi.close();

		fo.close();

	}
	
	public static void writeHelpFAQs(String filePath, String sheetName, List<String> questions, List<String> answers) {
	    try {
	        FileInputStream fis = new FileInputStream(filePath);
	        Workbook workbook = WorkbookFactory.create(fis);
	        Sheet sheet = workbook.createSheet(sheetName);
 
	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("Question");
	        header.createCell(1).setCellValue("Answer");
 
	        for (int i = 0; i < questions.size(); i++) {
	            int rowIndex = i * 2 + 1; // ensures space between entries
 
	            Row questionRow = sheet.createRow(rowIndex);
	            questionRow.createCell(0).setCellValue(questions.get(i));
 
	            Row answerRow = sheet.createRow(rowIndex + 1);
	            answerRow.createCell(1).setCellValue(i < answers.size() ? answers.get(i) : "");
	            
	            // Next iteration will skip one row, leaving a blank
	        }
 
 
	        fis.close();
	        FileOutputStream fos = new FileOutputStream(filePath);
	        workbook.write(fos);
	        workbook.close();
	        fos.close();
 
	        System.out.println("Help FAQs written to sheet: " + sheetName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void appendHelpFAQsToSheet(String filePath, String sheetName, List<String> questions, List<String> answers) {
	    try {
	        FileInputStream fis = new FileInputStream(filePath);
	        Workbook workbook = WorkbookFactory.create(fis);
	        Sheet sheet = workbook.getSheet(sheetName);
 
	        if (sheet == null) {
	            sheet = workbook.createSheet(sheetName); // fallback if Sheet2 doesn't exist
	        }
 
	        int lastRow = sheet.getLastRowNum();
 
	        for (int i = 0; i < questions.size(); i++) {
	            Row row = sheet.createRow(++lastRow);
	            row.createCell(0).setCellValue(questions.get(i));
	            row.createCell(1).setCellValue(i < answers.size() ? answers.get(i) : "");
	        }
 
	        fis.close();
	        FileOutputStream fos = new FileOutputStream(filePath);
	        workbook.write(fos);
	        workbook.close();
	        fos.close();
 
	        System.out.println("Help FAQs appended to sheet: " + sheetName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//Tathagat (end)

}
