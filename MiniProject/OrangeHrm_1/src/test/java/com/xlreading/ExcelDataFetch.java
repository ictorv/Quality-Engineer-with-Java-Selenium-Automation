package com.xlreading;

public class ExcelDataFetch {
	ExcelReadorWrite erw;
    Object[][] loginData;
    public static int rows;
    public static int cols;
    public Object[][] excelDataFetch(String path, String sheet) {
    	erw = new ExcelReadorWrite(path, sheet);
    	
    	rows = erw.getRowsCount();
    	cols = erw.getColumnCount();
    	loginData = new Object[rows][cols];
    	System.out.println("Rows: " + rows);
    	System.out.println("Columns: " + cols);

    	for(int i=1; i<rows; i++) {
    		for(int j=0;j<cols; j++) {
    			loginData[i][j] = erw.getCellData(i, j);
    			System.out.println(loginData[i][j]);
    		}
    		
    	}
		return loginData;
    }
}

 
 
