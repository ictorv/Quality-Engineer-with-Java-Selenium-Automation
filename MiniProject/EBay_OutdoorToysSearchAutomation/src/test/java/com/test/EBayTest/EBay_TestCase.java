package com.test.EBayTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.ExcelUtil.ExcelReadAndWrite;
import com.test.Pages.EBay_FilterPage;
import com.test.Pages.EBay_ResultsPage;
import com.test.Pages.EBay_HomePage;
import com.test.Utilities.DriverManager;
import com.test.Utilities.ExtentReportManager;
import com.test.Utilities.ScreenShotUtility;

public class EBay_TestCase implements ITestNGListener {
	
		public WebDriver driver;
		
		//public static String browser = "edge";
		DriverManager objDriver;
		int i, j;
		String[][] prodFilter;
		String Output;
		String path = "\\testdata\\ReadWrite.xlsx";
		String sheet = "Sheet1";
		ExcelReadAndWrite eds;
		
		//ExtentReportManager erm;
		@BeforeTest	
		@Parameters("browser")
		public void driverConfig(String browser) {
			objDriver = new DriverManager();
			// Instantiate driver
			
			driver = objDriver.driverInstantiate(browser);
			ExtentReportManager.setDriver(driver);
			System.out.println("Opened the URL in the browser");
			// return driver;
		}
	
	//Testing Advance Button
	@Test(priority = 1)
	public void testHomepage() {
		EBay_HomePage obj = new EBay_HomePage(driver);
		obj.clickAdvanceButton();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		ScreenShotUtility.screenShotTC(driver, "01_Homepage");
	}
	
	//Testing filterPage
	@Test(dataProvider = "prodFilter",priority = 2)
	public void testfilterPage(String item, String catogery) throws IOException, InterruptedException {
		EBay_FilterPage obj = new EBay_FilterPage(driver);
		obj.eSendKeys(item);
		obj.category(catogery);
		obj.selectCheckbox1();
		obj.selectCheckbox2();
		obj.selectCheckbox3();
		obj.selectCheckbox4();
		obj.selectCheckbox5();
		obj.hitSearch();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		Thread.sleep(3000);
		ScreenShotUtility.screenShotTC(driver, "02_Filterpage");
	}
	
	//Data reading from Excel from data provider
	@DataProvider(name = "prodFilter")
	public String[][] fetchExcelData() throws IOException {
		String filePath = System.getProperty("user.dir")+path;
		//int rowCount = excelUtility.getRowCount(filePath, sheet);
		//int colCount = excelUtility.getCellCount(filePath, sheet, rowCount);
		prodFilter =new String [1][2];
		//System.out.println(rowCount);
		//System.out.println(colCount);
		for(int i = 0; i <= 1; i++) {
			prodFilter[0][i] = ExcelReadAndWrite.getCellData(filePath, sheet, 1, i).toString();
		}
        return prodFilter;
    }
	
	//Testing finalPage
	@Test(priority = 3)
	public void testfinalPage() throws IOException, InterruptedException{
		EBay_ResultsPage obj = new EBay_ResultsPage(driver);
		obj.findAllherf("toys");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		Thread.sleep(3000);
		ScreenShotUtility.screenShotTC(driver, "03_Finalpage");
	}
	
	//Closing Browser
	@AfterClass
    public void tearDown() {
	// 10. Close the browser
        if (driver != null) {
        	objDriver.driverTearDown();
        }       
    }
}
