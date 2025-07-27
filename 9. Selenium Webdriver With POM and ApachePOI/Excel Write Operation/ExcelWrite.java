import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {
	
	public static void main(String args[])throws IOException
	{
		Scanner sc=new Scanner(System.in);
		//Fill the code
		// Create a new XSSFWorkbook instance to work with Excel
		// Prompt the user to enter the name of the worksheet
		// Create a new XSSFSheet with the provided name
		// Define the data to be written to Excel
		// Get the number of rows and columns in the data array
		// Specify the file path where the Excel file will be saved
		// Create a FileOutputStream to write the workbook to the file
		// Write the workbook data to the file
		String path="ExcelWriteOperation/EmployeeDetails.xlsx";
		FileOutputStream fo=new FileOutputStream(path);
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		System.out.println("Enter the Name of the WorkSheet:");
		
		String sheetName=sc.next();
		XSSFSheet sheet =workbook.createSheet(sheetName);
		String[][]data={
		    {
		      "EmpID","Name","Job"
		    },
		    {
		        "101","David","Software Engineer"
		    },
		    {
		        "102","Aravind","Junior Software Engineer"
		    },
		    {
		        "103","Scoot","Manager"
		    }
	    };
		int rows=data.length;
		int cols=data[0].length;
		
		for(int r=0;r<rows;r++){
		    XSSFRow row=sheet.createRow(r);
		    for(int c=0;c<cols;c++){
		        XSSFCell cell=row.createCell(c);
		        cell.setCellValue(data[r][c]);
		    }
		}
		workbook.write(fo);
		workbook.close();
		fo.close();
		System.out.println("The EmployeeDetails.xlsx file was successfully written");
		
	}

}
