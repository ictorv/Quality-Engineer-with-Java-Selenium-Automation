package com.facebook.util;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelReadWrite {
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static String[][] data;
	static File file;

	public void setupExcel() {
		try {
			String filePath = System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx";
			file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
		} catch (IOException e) {
			System.err.println("Error setting up Excel file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void readExcel() {
		try {
			int rowCount = sheet.getLastRowNum();
			int colCount = (sheet.getRow(0) != null) ? sheet.getRow(0).getLastCellNum() : 0;

			//System.out.println("Excel Reading: Row Count = " + (rowCount + 1) + ", Column Count = " + colCount);

			data = new String[rowCount + 1][colCount];

			for (int i = 0; i <= rowCount; i++) {
				XSSFRow currentRow = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					XSSFCell cell = (currentRow != null) ? currentRow.getCell(j) : null;
					data[i][j] = (cell == null) ? "" : cell.toString();
				}
			}

			//System.out.println("Data from Excel:");
//			for (String[] row : data) {
//				for (String cellValue : row) {
//					System.out.print(cellValue + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("-----------------------");

		} catch (Exception e) {
			System.err.println("Error reading Excel data: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String[] getUserDataForForm() {
		if (data == null) {
			System.err.println("Excel data has not been read yet. Call readExcel() first.");
			return new String[0];
		}

		DataFormatter formatter = new DataFormatter();

		String firstName = formatter.formatCellValue(sheet.getRow(1).getCell(0));
		String lastName = formatter.formatCellValue(sheet.getRow(1).getCell(1));
		String day = formatter.formatCellValue(sheet.getRow(1).getCell(2));
		String month = formatter.formatCellValue(sheet.getRow(1).getCell(3));
		String year = formatter.formatCellValue(sheet.getRow(1).getCell(4));
		String gender = formatter.formatCellValue(sheet.getRow(1).getCell(5));
		String mobileNumber = formatter.formatCellValue(sheet.getRow(1).getCell(6));

		return new String[] { firstName, lastName, day, month, year, gender, mobileNumber };
	}

	public void writeExcel(int rowIndex, String mobileError, String passwordError) {
		if (sheet == null) {
			System.err.println("Sheet is not initialized. Cannot write to Excel.");
			return;
		}

		XSSFRow targetRow = sheet.getRow(rowIndex);
		if (targetRow == null) {
			targetRow = sheet.createRow(rowIndex);
		}

		targetRow.createCell(7).setCellValue(mobileError);
		targetRow.createCell(8).setCellValue(passwordError);

		System.out.println("Written to Row " + rowIndex + ": '" + mobileError + "', '" + passwordError + "'");
	}

	public void saveExcelChanges() {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			wb.write(fos);
			System.out.println("Excel file saved successfully to: " + file.getAbsolutePath());
		} catch (IOException e) {
			System.err.println("Error saving Excel file: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (wb != null)
					wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
