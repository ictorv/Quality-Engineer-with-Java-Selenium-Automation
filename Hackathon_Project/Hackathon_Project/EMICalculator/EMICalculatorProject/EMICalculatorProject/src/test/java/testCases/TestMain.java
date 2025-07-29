package testCases;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.CarLoanPage;
import pageObjects.EMICalculatorUI;
import pageObjects.HomeLoanEMIPage;
import pageObjects.HomeLoanPage;
import pageObjects.InterestRateUI;
import pageObjects.LandingPage;
import pageObjects.LoanAmountUi;
import pageObjects.LoanTenureUI;
import pageObjects.PersonalLoanPage;
import utilities.DriverSetup;
import utilities.ExcelUtilities;
import utilities.JSONUtilities;
import utilities.XMLUtilities;

public class TestMain {
	public WebDriver driver;
	
	DriverSetup objDriver;
	XMLUtilities xml;
	ExcelUtilities excel;
	JSONUtilities json;
	String[] xmlData;
	String xmlFilePath = "testData/HomeLoanData.xml";
	String yearOnFile = "HomeLoanYearOnTable.xlsx";
	String jsonFile = "testData/SlidersData.json";
	
	JSONObject jsonData;
	JSONObject obj;
	
	LandingPage landingPageObj;
	CarLoanPage carLoanObj;
	HomeLoanEMIPage homeLoanObj;
	HomeLoanPage homeObj;
	PersonalLoanPage persObj;
	LoanAmountUi loanUIObj;
	EMICalculatorUI emiUIObj;
	LoanTenureUI tenureObj;
	InterestRateUI iRateObj;
	
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws IOException {
		objDriver = new DriverSetup();
		DriverSetup.url = "https://emicalculator.net/";
		driver = DriverSetup.driverInstantiate(browser);
		System.out.println("Opened the URL in the browser");
	}
	
	@BeforeTest
	public void testReadData() {
		try {
			xml = new XMLUtilities();
			xmlData = xml.getXMLData(xmlFilePath);
			
			json = new JSONUtilities();
			jsonData = json.readJSONData(jsonFile);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@AfterClass
	public void tearDown() {
		objDriver.driverTearDown();;
	}
	
	@Test(priority = 1)
	public void loadLandingPage() {
		landingPageObj = new LandingPage(driver);
		String result = landingPageObj.getConfirmMsg();
		
		Assert.assertEquals(result, "Home Loan Amount");
		System.out.println("Successfully Navigated to Landing Page");
	}
	
	@Test(priority = 2)
	public void verifyHomeLoan() {
		homeObj = new HomeLoanPage(driver);
		
		obj = jsonData.getJSONObject("Home Loan");
		
		homeObj.setLoanAmtSlider(obj.getInt("amount"));
		homeObj.setInterestRateSlider(obj.getInt("interestRate"));
		homeObj.setTenureSlider(obj.getInt("tenure"));
		
		String emi = homeObj.getEMI();
		String amount = homeObj.getPrincipalAmount();
		
		Assert.assertEquals(emi, obj.getString("validString"));
		System.out.println("Home Loan EMI: "+emi);
		System.out.println("Total Amount: "+amount);
		obj.clear();
		homeObj.clickPersonalloan();
	}
	
	@Test(priority = 3)
	public void verifyPersonalLoan() {
		
		persObj = new PersonalLoanPage(driver);
		
		obj = jsonData.getJSONObject("Personal Loan");
		
		persObj.setLoanAmtSlider(obj.getInt("amount"));
		persObj.setInterestRateSlider(obj.getInt("interestRate"));
		persObj.setTenureSlider(obj.getInt("tenure"));
		
		String emi = persObj.getEMI();
		String amount = persObj.getPrincipalAmount();
		
		Assert.assertEquals(emi, obj.getString("validString"));
		System.out.println("Home Loan EMI: "+emi);
		System.out.println("Total Amount: "+amount);
		obj.clear();
		persObj.clickCarloan();
		
	}
	
	@Test(priority = 4)
	public void verifyCarLoan() {
		carLoanObj = new CarLoanPage(driver);
		
		obj = jsonData.getJSONObject("Car Loan");
		
		carLoanObj.setLoanAmtSlider(obj.getInt("amount"));
		carLoanObj.setInterestRateSlider(obj.getInt("interestRate"));
		carLoanObj.setTenureSlider(obj.getInt("tenure"));
		
		String interest = carLoanObj.getInterest();
		String principalAmount = carLoanObj.getPrincipalAmount();
		
		
		Assert.assertTrue(interest.contains(obj.getString("validString")),interest);
		
		System.out.println("Interest Amount: "+interest);
		System.out.println("Principal Amount: "+principalAmount);
		System.out.println("Successfully EMI is Calculated for Car Loan");
		obj.clear();
		try {
			carLoanObj.selectDropDown("Home Loan EMI Calculator");
		}
		catch(Exception e) {
			System.out.println();
		}
	}
	@Test(priority = 5)
	public void verifyHomeLoanEMI() {		
		homeLoanObj = new HomeLoanEMIPage(driver);
		homeLoanObj.setHomePrice(xmlData[0]);
		homeLoanObj.setDownPayment(xmlData[1]);
		homeLoanObj.setLoanInsurance(xmlData[2]);
		homeLoanObj.setLoanAmount();
		homeLoanObj.setInterestRate(xmlData[3]);
		homeLoanObj.setLoanFees(xmlData[4]);
		homeLoanObj.setStartDate(xmlData[5]);
		homeLoanObj.setHomeInsurance(xmlData[6]);
		homeLoanObj.setMaintenanceExpenses(xmlData[7]);
		
		String confirmMsg = homeLoanObj.getConfirmation();
		Assert.assertEquals(confirmMsg, xmlData[8]);
		//System.out.println(confirmMsg);
	}
	
	@Test(priority = 6, dependsOnMethods = "verifyHomeLoan")
	public void retrieveYearTable() {
		List<List<String>> tableData = homeLoanObj.getYearOnYearTable();
		for(List<String> rows : tableData) {
			for(String cell : rows) {
				System.out.print(cell+"\t");
			}
			System.out.println();
		} 
		
		try {
			excel = new ExcelUtilities();
			excel.writeTableToExcel(yearOnFile, tableData);
			System.out.println("Year on Year Table Succesfully Written into Excel");
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			homeLoanObj.selectDropDown("Loan Calculator");
		}
		catch(Exception e) {
			System.out.println();
		}
	}
	
	
	@Test(priority = 7)
	public void checkEMICalculatorUI() {
		emiUIObj = new EMICalculatorUI(driver);
		
		emiUIObj.firstClick();
		emiUIObj.setLoanAmtSlider(2500000);
		emiUIObj.setInterestRateSlider(7.5);
		//emiUIObj.setLoanTenureSlider(15);
		
		emiUIObj.clickNext();
	}
	
	@Test(priority = 8)
	public void checkLoanAmountUI() {
		loanUIObj = new LoanAmountUi(driver);
		
		loanUIObj.setLoanAmtSlider(25000);
		loanUIObj.setInterestRateSlider(7.5);
		loanUIObj.setLoanTenureSlider(15);
		
		loanUIObj.clickNext();
	}
	
	@Test(priority = 9)
	public void checkLoanTenureUI() {
		tenureObj = new LoanTenureUI(driver);
		
		tenureObj.setLoanAmtSlider(2500000);
		tenureObj.setEMISlider();
		tenureObj.setInterestRateSlider(0);
		
		loanUIObj.clickNext();
	}
	
	@Test(priority = 10)
	public void checkInterestRateUI() {
		iRateObj = new InterestRateUI(driver);
		
		iRateObj.setLoanAmtSlider(2500000);
		iRateObj.setLoanFeesSlider(50000);
		
	}
	
}
