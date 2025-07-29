package com.testing.UserDefineLibraries;

//import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;

public class UrlTest {

	static JavascriptExecutor js;
	ExcelReadOrWriteUtility erw;

	Object[][] logindata;
	int i, j;

	// Locating the elements in order to read form the excel and retrieving row
	// count and column count
	public Object[][] excelDataFetch(String filepath, String sheetName) {

		erw = new ExcelReadOrWriteUtility(filepath, sheetName);
		erw.excelRead(filepath, sheetName);
		int rows = erw.getRowCount();
		int cols = erw.getColumnCount();
		System.out.println("Number of rows of data present :" + rows);
		System.out.println("Number of columns of data present:" + cols);
		logindata = new Object[rows][cols];
		for (i = 1; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				logindata[i][j] = erw.getCellData(i, j);
			}
		}
		return logindata;
	}

}
