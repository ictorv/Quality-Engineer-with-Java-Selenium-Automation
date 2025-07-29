package utilities;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtilityClass {

	    private String filePath;
	    private String sheetName;
	    private Workbook workbook;
	    private Sheet sheet;

	    public ExcelUtilityClass(String filePath, String sheetName) {
	        this.filePath = filePath;
	        this.sheetName = sheetName;
	        loadWorkbook();
	    }

	    private void loadWorkbook() {
	        try (FileInputStream fis = new FileInputStream(filePath)) {
	            workbook = new XSSFWorkbook(fis);
	            sheet = workbook.getSheet(sheetName);
	            if (sheet == null) {
	                sheet = workbook.createSheet(sheetName);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public int getRowCount() {
	        return sheet.getPhysicalNumberOfRows();
	    }

	    public int getColumnCount() {
	        Row headerRow = sheet.getRow(0);
	        return (headerRow != null) ? headerRow.getPhysicalNumberOfCells() : 0;
	    }

	    public String getCellData(int rowNum, int colNum) {
	        Row row = sheet.getRow(rowNum);
	        if (row == null) return "";
	        Cell cell = row.getCell(colNum);
	        if (cell == null) return "";
	        DataFormatter formatter = new DataFormatter();
	        return formatter.formatCellValue(cell);
	    }

	    public void setCellData(String value, int rowNum, int colNum) {
	        Row row = sheet.getRow(rowNum);
	        if (row == null) row = sheet.createRow(rowNum);

	        Cell cell = row.getCell(colNum);
	        if (cell == null) cell = row.createCell(colNum);

	        cell.setCellValue(value);
	        saveWorkbook();  // Write changes immediately after setting cell
	    }

	    public String[][] getSheetData(int row,int column) {
	    	int rowCount = row;
	        int colCount = column;

	        String[][] data = new String[rowCount - 1][colCount];

	        for (int i = 1; i < rowCount; i++) {
	            for (int j = 0; j < colCount; j++) {
	                data[i - 1][j] = getCellData(i, j);
	            }
	        }
	        return data;
	    }

	    private void saveWorkbook() {
	        try (FileOutputStream fos = new FileOutputStream(filePath)) {
	            workbook.write(fos);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void closeWorkbook() {
	        try {
	            if (workbook != null) {
	                workbook.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


