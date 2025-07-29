package utilities;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.DataProvider;
import org.xml.sax.SAXException;

public class DataProviders {
	
	public static String path=".\\testData\\Identify-Car-Wash-Services_TestData.xlsx";//taking xl file from testData


	@DataProvider(name="TC001")
	public String[][] TC001(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC001");
		String data[][] = util.getSheetData(2,4);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC002")
	public String[][] TC002(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC002");
		String data[][] = util.getSheetData(2,1);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC003")
	public String[][] TC003(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC003");
		String data[][] = util.getSheetData(2,1);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC004")
	public String[][] TC004(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC004");
		String data[][] = util.getSheetData(2,5);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC005")
	public String[][] TC005(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC005");
		String data[][] = util.getSheetData(2,6);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC006")
	public String[][] TC006(){
		
		JSONUtil obj = new JSONUtil();
		String data[][] =obj.TC006JSON();
		return data;
	}
	
	@DataProvider(name="TC007")
	public String[][] TC007(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC007");
		String data[][] = util.getSheetData(2,1);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC008")
	public String[][] TC008(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC008");
		String data[][] = util.getSheetData(2,2);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC009")
	public String[][] TC009(){
		
		ExcelUtilityClass util = new ExcelUtilityClass(path, "TC009");
		String data[][] = util.getSheetData(2,3);
		util.closeWorkbook();
		return data;
	}
	
	@DataProvider(name="TC010")
	public String[][] TC010() throws ParserConfigurationException, SAXException, IOException{
		
		XMLUtil obj = new XMLUtil();
		String data[][] =obj.TC010XML();
		return data;
	}
	

	
}
