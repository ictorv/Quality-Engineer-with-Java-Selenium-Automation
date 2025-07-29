package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	FileOutputStream file;
	XSSFWorkbook workbook;
	Sheet sheet;
	
	public void writeTableToExcel(String filePath, List<List<String>> tableData) throws IOException{
		
		file = new FileOutputStream(filePath);
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("YearOnYearTable");

		for(int r=0;r<tableData.size();r++) {
			Row row = sheet.createRow(r);
			List<String> rowData = tableData.get(r);
			
			for(int c=0;c<rowData.size();c++) {
				Cell cell = row.createCell(c);
				cell.setCellValue(rowData.get(c));
			}
		}
		
		workbook.write(file);
		workbook.close();
	}
}
