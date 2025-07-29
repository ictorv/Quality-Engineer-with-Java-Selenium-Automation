package excelTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.BrowserConfiguration.DriverConfig;

public class ExcelTest {
	
	// Fields for file paths and streams
    String filePathRead1;
    String filePathRead2;
    FileInputStream fis1;
    FileInputStream fis2;
    FileOutputStream fos1;

    // Apache POI objects for handling Excel files
    Workbook wb1;
    Workbook wb2;
    Sheet sheet1;
    Sheet sheet2;

    // Data structure to hold Excel data
    Object[][] excelData;

    // Constructor to initialize and read Excel file from the provided path and sheet
    public ExcelTest(String excelPathRead, String Sheet) {
        this.filePathRead1 = excelPathRead;

        File fp1 = new File(System.getProperty("user.dir") + "\\" + filePathRead1);
        System.out.println("readed excel file");

        try {
            fis1 = new FileInputStream(fp1); // Open file stream
            wb1 = new XSSFWorkbook(fis1);     // Load workbook
            sheet1 = wb1.getSheet(Sheet);     // Load specified sheet
        } catch (FileNotFoundException e) {
            System.out.println("jjj");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("jjj");
            e.printStackTrace();
        }
    }

    // Method to return the number of physical (non-empty) rows in the sheet
    public int getRowCount() {
        int rowCount = sheet1.getPhysicalNumberOfRows();
        return rowCount;
    }

    // Method to return the number of physical (non-empty) cells in the first row
    public int getColumnCount() {
        Row row = sheet1.getRow(0);
        int colCount = row.getPhysicalNumberOfCells();
        return colCount;
    }

    // Fetch a single cell value and return it as a string
    public String getCellData(int rowNum, int colNum) {
        Cell cell = sheet1.getRow(rowNum).getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        String data = cell.toString();
        return data;
    }

    // Read entire Excel data into a 2D Object array and print it
    public void excelDataFetch() {
        int i, j;
        int rows = getRowCount();
        int column = getColumnCount();
        System.out.println("Number of rows present: " + rows);
        System.out.println("Number of columns present: " + column);

        excelData = new Object[rows][column];

        for (i = 1; i < rows; i++) {
            for (j = 0; j < column; j++) {
                excelData[i][j] = getCellData(i, j);
                System.out.print(excelData[i][j].toString() + "\t");
            }
            System.out.println();
        }

        // Use data from cell at [1][0] as URL in DriverConfig
        DriverConfig.setUrl(excelData[1][0].toString());
        System.out.println("----------------------------------------------------");
    }

    // Getter to provide access to the fetched Excel data
    public Object[][] getExcel() {
        return excelData;
    }

    // Write a list of strings into the Excel file and save it
    public void excelDataWrite(List<String> dataToWrite, String excelPathWrite) {
        this.filePathRead2 = excelPathWrite;
        File fp2 = new File(System.getProperty("user.dir") + "\\" + filePathRead2);

        try {
            fis2 = new FileInputStream(fp2);     // Load the file for writing
            wb2 = new XSSFWorkbook(fis2);        // Load workbook
            sheet2 = wb2.getSheetAt(0);          // Target first sheet
            System.out.println("Writing data");

            int sizeOfData = dataToWrite.size();

            // Iterate and write each item to row (i+1), column index 2
            for (int i = 0; i < sizeOfData; i++) {
                Row row = sheet2.getRow(i + 1);
                if (row == null) {
                    row = sheet2.createRow(i + 1);
                }
                Cell cell = row.createCell(2);
                System.out.println("data is writing in " + i + " " + dataToWrite.get(i));
                cell.setCellValue(dataToWrite.get(i));
            }

            // Save to new file after writing
            fos1 = new FileOutputStream("testDataExcelWrite.xlsx");
            wb2.write(fos1);

            // Cleanup
            wb2.close();
            fis2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
