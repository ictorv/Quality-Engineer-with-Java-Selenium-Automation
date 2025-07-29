package com.test.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.Utilities.DriverSetup;
import com.test.Utilities.ExtentReportManager;
import com.test.Utilities.ScreenShot;
import com.test.data.ExcelDataSheet;
import com.test.objectRepository.rediffCreateAccountPage;
import com.test.objectRepository.rediffHomePage;
import com.test.objectRepository.rediffTermsAndConditionsPage;

public class Main {	
		WebDriver driver;
		DriverSetup setup = new DriverSetup();
		ExcelDataSheet es;
		ExtentReportManager erm;
		
		@BeforeClass
		@Parameters("browser")
		public void setUp(String browser) throws Exception {
			// Instantiate excel data manager
			String excelFileName = "MiniProjectTestData.xlsx";
			String sheetName = "ReDiff Link Count";
			es = new ExcelDataSheet(excelFileName, sheetName);
			DriverSetup.es = es;
			// Instantiate driver
			driver = setup.getDriver(browser);
			ExtentReportManager.setDriver(driver);
			System.out.println("Opened the URL in the browser");
		}
		
		@Test(priority=1)
		public void rediffHomePage() {
			//Open rediff Website
			rediffHomePage rediffHP = new rediffHomePage(driver);
			//Take screenshot of home page
			ScreenShot.screenShotTC(driver, "01_Homepage");
			//Click create account button in home page
			rediffHP.click_create_account();
		}
		
		@Test(priority=2)
		public void createAccountPage() {
			//Initialize rediffCreateAccountPage Page Object
			rediffCreateAccountPage rediffCAP = new rediffCreateAccountPage(driver);
			//Passing ExcelSheet object
			rediffCreateAccountPage.es = es;
			//Check if Create account page is open
			rediffCAP.checkIfCAOpened();
			//Take screenshot of create account page
			ScreenShot.screenShotTC(driver, "01_CreateAccountPage");
			
			//Count the number of links containing in create account page
			rediffCAP.linkCount();
			//Clicks the terms and condition link
			rediffCAP.clickTC();
			//Checks if the terms and conditions window is open
			rediffCAP.checkIfTCOpened();
			//Switches from parent window to the terms and conditions window
			rediffCAP.switchWindowToTC();
		}
		
		@Test(priority=3)
		public void termAndConditionsPage() {
			//Initialize rediffTermsAndConditionsPage Page Object
			rediffTermsAndConditionsPage rediffTCP = new rediffTermsAndConditionsPage(driver);
			//Passing ExcelSheet object
			rediffTermsAndConditionsPage.es = es;
			//Passing ExtentReportManager object
			rediffTermsAndConditionsPage.em = erm;
			//Take screenshot of Terms and condition window
			ScreenShot.screenShotTC(driver, "01_Terms&ConditionsWindow");
			
			//Checks if the title of terms and conditions window matches with the expected title
			rediffTCP.checkTitle();
			//Closes the terms and condition window
			rediffTCP.closeWindow();
		}
		
		@AfterClass
		public void tearDown() {
			//Quits the driver
			if (driver != null) {
				DriverSetup.driverTearDown();
			}	
			//Closes the ExcelSheet file
			es.closeFile();
		}
		
		
	}

