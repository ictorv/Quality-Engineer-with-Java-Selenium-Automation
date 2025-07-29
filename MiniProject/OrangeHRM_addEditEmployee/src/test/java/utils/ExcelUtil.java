package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
//import java.util.Iterator;

public class ExcelUtil {
	private static Workbook workbook;
	private static Sheet sheet;
	private static String filePath;

	public static Object[][] getTestData(String path, String sheetName) throws IOException {
		filePath = path;
		FileInputStream file = new FileInputStream(filePath);

		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = getCellValue(cell);
			}
		}
		return data;
	}

	private static Object getCellValue(Cell cell) {
		if (cell == null)
			return "";
		return switch (cell.getCellType()) {
		case STRING -> cell.getStringCellValue();
		case NUMERIC -> (cell.getNumericCellValue() % 1 == 0) ? String.valueOf((int) cell.getNumericCellValue())
				: String.valueOf(cell.getNumericCellValue());
		case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
		case FORMULA -> cell.getCellFormula();
		case BLANK -> "";
		default -> throw new IllegalArgumentException("Unexpected value: " + cell.getCellType());
		};
	}
}
