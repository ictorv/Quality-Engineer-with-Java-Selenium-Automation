package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterUtil {

	public static void prepareOutputSheet(String filePath, String sheetName) {
		try {
			Workbook workbook;
			Sheet outputSheet;
			File file = new File(filePath);

			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);
				fis.close();

				outputSheet = workbook.getSheet("EmployeeResult");
				if (outputSheet != null) {
					int index = workbook.getSheetIndex(outputSheet);
					workbook.removeSheetAt(index);
				}
			} else {
				workbook = new XSSFWorkbook();
			}
			outputSheet = workbook.createSheet("EmployeeResult");
			createHeaderRow(outputSheet);

			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error file excel something something");
		}

	}

	public static void writeRow(String filePath, List<String> rowData) throws IOException {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fis);
		fis.close();

		Sheet sheet = workbook.getSheet("EmployeeResult");
		if (sheet == null) {
			sheet = workbook.createSheet("EmployeeResult");
			createHeaderRow(sheet);
		}

		int lastRow = sheet.getLastRowNum();
		Row row = sheet.createRow(lastRow + 1);

		for (int i = 0; i < rowData.size(); i++) {
			row.createCell(i).setCellValue(rowData.get(i));
		}

		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		workbook.close();
	}

	private static void createHeaderRow(Sheet sheet) {
		Row header = sheet.createRow(0);
		String[] headers = { "First Name", "Middle Name", "Last Name", "UserName", "Password", "EmpId" };

		for (int i = 0; i < headers.length; i++) {
			header.createCell(i).setCellValue(headers[i]);
		}
	}
}
