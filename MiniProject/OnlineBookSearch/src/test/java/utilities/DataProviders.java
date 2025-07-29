package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="SearchData")
	public String[][] getData() throws Exception{
		String path = ".\\testData\\OnlineBookSearch_TestData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("InputData"); 
		int totalcols = xlutil.getCellCount("InputData",1);
		
		String searchData[][] = new String[totalrows][totalcols];
	
		for(int i=1; i<=totalrows; i++) {
			for(int j = 0; j<totalcols; j++) {
				searchData[i-1][j] = xlutil.getCellData("InputData",i,j);
			}
		}
		return searchData;
	}
	
}
