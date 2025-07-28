import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.*;
import java.text.DecimalFormat;

public class ReadingExcel {
	
	
	public static String[][] ReadExcelOperation(String fileName) throws IOException
    {
        // Open the Excel file
        // Create a workbook instance
        // Get the first sheet from the workbook
        // Get the number of rows and columns in the Excel sheet
        // Create a 2D array to store the Excel data
        // Iterate through each row and column to read the cell data
        // Return the 2D array containing Excel data
        FileInputStream file=new FileInputStream(fileName);
        XSSFWorkbook wb=new XSSFWorkbook(file);
        XSSFSheet sheet=wb.getSheetAt(0);
        
        int tRows=sheet.getLastRowNum();
        int tCol=sheet.getRow(0).getLastCellNum();
        // DecimalFormat formatter=new DecimalFormat("#");
        String [][] matrix=new String[tRows+1][tCol];
        for(int i=0;i<=tRows;i++){
            XSSFRow rw=sheet.getRow(i);
            for(int j=0;j<tCol;j++){
                String value=rw.getCell(j).toString();
                if(i>0 && j==0){
                    double temp=Double.parseDouble(value);
                    int t=(int)temp;
                    value=String.valueOf(t);
                }
                // XSSFCell cell=rw.getCell(c);
                matrix[i][j]=value;
            }
        }
    return matrix;
    
    }    
	public static void main(String args[]) 
	{
		//Fill the code
		// Path to the Excel file
		// Call the method to read Excel data
		// Get the number of rows and columns in the Excel sheet
		// Print the data from the Excel sheet
        String fileName="ExcelReadOperation/EmployeeDetails.xlsx";
        String [][]array=null;
        
        try{
            array=ReadExcelOperation(fileName);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        for(int r=0;r<array.length;r++){
            for(int c=0;c<array[r].length;c++){
                System.out.print(array[r][c]+"\t");
            }
            System.out.println();
        }
	}	

}
