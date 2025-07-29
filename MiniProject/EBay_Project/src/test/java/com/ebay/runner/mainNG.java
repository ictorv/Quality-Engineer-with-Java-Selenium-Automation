package com.ebay.runner;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestNGListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ebay.Utility.DriverManager;
import com.ebay.Utility.ExtentReportManager;
import com.ebay.Utility.ScreenShotUtility;
import com.ebay.excelData.excelUtility;
import com.ebay.pages.filterPage;
import com.ebay.pages.finalPage;
import com.ebay.pages.homePage;

public class mainNG implements ITestNGListener {
	
		public WebDriver driver;
		
		//public static String browser = "edge";
		DriverManager objDriver;
		int i, j;
		String[][] prodFilter;
		String Output;
		String path = "\\testdata\\ReadWrite.xlsx";
		String sheet = "Sheet1";
		excelUtility eds;
		
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
		homePage obj = new homePage(driver);
		obj.clickAdvanceButton();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		ScreenShotUtility.screenShotTC(driver, "01_Homepage");
	}
	
	//Testing filterPage
	@Test(dataProvider = "prodFilter",priority = 2)
	public void testfilterPage(String item, String catogery) throws IOException, InterruptedException {
		filterPage obj = new filterPage(driver);
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
			prodFilter[0][i] = excelUtility.getCellData(filePath, sheet, 1, i).toString();
		}
        return prodFilter;
    }
	
	//Testing finalPage
	@Test(priority = 3)
	public void testfinalPage() throws IOException, InterruptedException{
		finalPage obj = new finalPage(driver);
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
