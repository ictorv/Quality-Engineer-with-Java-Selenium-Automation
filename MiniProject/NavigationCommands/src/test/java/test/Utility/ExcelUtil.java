package test.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	String filePath;
	FileInputStream fis;
	Workbook wb;
	Sheet sheet;
	List<String> data = new ArrayList<>();

	public ExcelUtil(String excelPath, String Sheet) {
		this.filePath = excelPath;
		File fp = new File(System.getProperty("user.dir") + "\\" + filePath);
		try {
			fis = new FileInputStream(fp);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(Sheet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	public int getColumnCount() {
		Row row = sheet.getRow(0);
		int colCount = row.getPhysicalNumberOfCells();
		return colCount;
	}

	public String getCellData(int rowNum, int colNum) {
		Cell cell = sheet.getRow(rowNum).getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		String data = cell.toString();
		return data;
	}

//		ExcelTest et = new ExcelTest(excelFilePath, sheetName);
	public void excelDataFetch() {
		int i, j;
		int rows = getRowCount();
		int column = getColumnCount();
		System.out.println("Number of rows present: " + rows);
		System.out.println("Number of columns present: " + column);
		for (i = 1; i < rows; i++) {
			Row row = sheet.getRow(i);
			for (j = 0; j < column; j++) {
				Cell cell = row.getCell(j);
				DataFormatter formatter = new DataFormatter();
				String formattedValue = formatter.formatCellValue(cell);
				System.out.println("Formatted Cell Value: " + formattedValue);

				data.add(formattedValue);
				System.out.print(getCellData(i, j).toString() + "\t");

			}
			System.out.println();
		}
	}

	public List<String> getListArray() {
		return data;
	}
}
