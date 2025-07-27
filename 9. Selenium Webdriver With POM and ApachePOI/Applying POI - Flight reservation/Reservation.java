import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reservation { //Do not change the class name

	//Use this declaration to store values parsed from excel
    public static String[] customerData=new String[5];
    
    public static String[] readExcelData(String fileName) throws IOException{   //Do not change the method signature
    
        //Implement code to read data from excel file. Store the values in 'customerData' array. Return the array.
    	FileInputStream fi=new FileInputStream(fileName);
    	XSSFWorkbook workbook=new XSSFWorkbook(fi);
    	XSSFSheet sheet = workbook.getSheet("reservation_details");
    	int rows=sheet.getLastRowNum();
    	int cols=sheet.getRow(0).getLastCellNum();
    	for(int r=0;r<=rows;r++){
    	    XSSFRow row=sheet.getRow(r);
    	    for(int c=0;c<cols;c++){
    	        String val=row.getCell(c).toString();
    	        if(c==1){
    	            double temp3=Double.parseDouble(val);
    	            long temp=(long)temp3;
    	            val=Long.toString(temp);
    	        }
    	        else if(c==2){
    	            double temp1=Double.parseDouble(val);
    	            int s= (int)temp1;
    	            val=Integer.toString(s);
    	        }
    	        customerData[c]=val;
    	    }
    	}
    	workbook.close();
    	fi.close();
        return customerData;
  }
}
