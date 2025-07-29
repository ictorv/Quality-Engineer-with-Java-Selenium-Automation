package testMain;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import driverSetup.DriverSetup;
import testObjectRepository.Rediff_RegistrationPage;
import utilityClass.ExcelUtility;
import utilityClass.ScreenShot;


public class Rediff_Main_Test {
	
	public static WebDriver driver;
	public static String fName = (System.getProperty("user.dir")+"\\src\\test\\resources\\Rediff_dataFile.xlsx");
	public static String sName = "data";
	static List<String> countryList;
	public static DriverSetup objDriverSetup;
	public static Rediff_RegistrationPage objRediffReg;
	public static String availableMsg;
	public static boolean flag;
	public static boolean countryValidation;
	public int row=1;
	
	// Set up WebDriver and launch Rediff page before executing tests
	@BeforeClass
	@Parameters("browser")
	public void driverConfig(String browser) {
		
		objDriverSetup = new DriverSetup();
		driver = objDriverSetup.getDriver(browser);
		ScreenShot.takeScreenshot(driver,"Rediff launch page");
		System.out.println("driver has been configured and Rediff launch page is opened");
	}
	
	// Fetch test data from Excel using DataProvider
	@DataProvider(name="rediffData")
	public String[][] data() throws IOException {
			
		String[][] rediffData = new String[ExcelUtility.getLastRowNumber(fName,sName)][ExcelUtility.getLastCellNumber(fName,sName,0)-1];
		
		for(int r=0;r<rediffData.length;r++) {
			for(int c=0;c<rediffData[r].length;c++) {
				rediffData[r][c]= ExcelUtility.getCellvalue(fName,sName,r+1,c);
			}
		}
		return rediffData;
	}
	
	// Main test method for Rediff registration flow
	@Test(dataProvider="rediffData")
	public void rediffMainTest(String name,String email,String password,String dateOfBirth,String country) throws IOException{
		
		flag = true;
		objRediffReg= new Rediff_RegistrationPage(driver);
		
		// Navigate to create account section
		objRediffReg.clickCreateNewAccountLink();
		ScreenShot.takeScreenshot(driver,"Rediff create new account page");
		System.out.println("Entered into Rediff create new account page");
		
		// Fill registration fields
		try{objRediffReg.enterName(name);
			objRediffReg.enterEmail(email);
			
			// Check email availability
			availableMsg=objRediffReg.checkAvailability();
			ScreenShot.takeScreenshot(driver,"Checking email availability");
			System.out.println("Checking email availabilltiy");
			
			// If given email not available, select suggested alternative
			if(!(availableMsg.contains("available"))) {
				
				objRediffReg.selectAutoSuggesstion();
				
				objRediffReg.checkAvailability();
				}
		}
		catch(Exception e) {
//			flag=false;
			System.out.println("Invalid username format or Email format");
		}
		
		 // Complete remaining form fields
		objRediffReg.enterPassword(password);
		
		objRediffReg.enterDateOfBirth(dateOfBirth);
		
		// Retrieve and print country list
		countryList = objRediffReg.getCountrys();
		objRediffReg.printCountryCount();
		
		//Selecting country
		objRediffReg.selectCountry(country);
		ScreenShot.takeScreenshot(driver,"selecting country");
		objRediffReg.getSelectedCountry();
		
		// clicking Alternative email checkBox and capture screenshot
		objRediffReg.clickCheckBox();
		ScreenShot.takeScreenshot(driver,"clicking chechbox");
		
		// Validate country dropdown and log result to Excel
		countryValidation = objRediffReg.ValidateCountryDropDown();
		
		if(countryValidation==true && flag==true) {
			ExcelUtility.setCellvalue(fName,sName,row,5,"Pass");
			Assert.assertTrue(true);
		}
		else {
			ExcelUtility.setCellvalue(fName,sName,row,5,"Fail");
			Assert.assertTrue(false);
		}
		row++;
		//Navigate to launch page to test next set of data
		driver.navigate().back();
		
	}
	
	// Tear down and quit browser after tests
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
		objDriverSetup.quit();
		}
	}
	
	 // Getter for WebDriver
	public static WebDriver getDriver() {
		return driver;
	}

}
