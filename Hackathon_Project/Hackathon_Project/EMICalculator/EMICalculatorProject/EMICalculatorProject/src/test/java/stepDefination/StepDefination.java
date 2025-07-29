package stepDefination;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePage;
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

public class StepDefination {
	
	public WebDriver driver;
	BasePage objDriver;
	LandingPage landingPageObj;
	CarLoanPage carLoanObj;
	HomeLoanEMIPage homeLoanObj;
	HomeLoanPage homeObj;
	PersonalLoanPage persObj;
	LoanAmountUi loanUIObj;
	EMICalculatorUI emiUIObj;
	LoanTenureUI tenureObj;
	InterestRateUI iRateObj;
	
	ExcelUtilities excel;
	String yearOnFile = "HomeLoanYearOnTable.xlsx";
	
	XMLUtilities xml;
	
	JSONUtilities json;
	static String[] xmlData;
	String xmlFilePath = "testData/HomeLoanData.xml";
	String jsonFile = "testData/SlidersData.json";
	
	static JSONObject jsonData;
	JSONObject obj;
	
	private static final Logger logger = LogManager.getLogger(StepDefination.class);
	
	@Given("Read data")
	public void testReadData() {
		try {
			xml = new XMLUtilities();
			xmlData = xml.getXMLData(xmlFilePath);
			
			json = new JSONUtilities();
			jsonData = json.readJSONData(jsonFile);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		logger.info("Data readed from JSON and XML files");
	}

	@When("the user opens the landing page")
	public void loadLandingPage() {
		landingPageObj = new LandingPage(Hooks.getDriver());
		DriverSetup.getLogger().info("User is on landing page");
	}
	@Then("the landing page should display Home Loan Amount")
    public void verify_landing_page() {
        landingPageObj = new LandingPage(Hooks.getDriver());
        String result = landingPageObj.getConfirmMsg();
        Assert.assertEquals(result, "Home Loan Amount");
        logger.info("Landing page is verified");
    }

    // Priority 2
    @Then("the user verifies Home Loan EMI and amount")
    public void verify_home_loan() {
        homeObj = new HomeLoanPage(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on Home Loan page");
        obj = jsonData.getJSONObject("Home Loan");

        homeObj.setLoanAmtSlider(obj.getInt("amount"));
        homeObj.setInterestRateSlider(obj.getInt("interestRate"));
        homeObj.setTenureSlider(obj.getInt("tenure"));
        DriverSetup.getLogger().info("All Slider Value Updated on Home Loan");
        
        Hooks.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(homeObj.getEMI(), obj.getString("validString"));
        System.out.println("Home Loan EMI: " + homeObj.getEMI());
        System.out.println("Total Amount: " + homeObj.getPrincipalAmount());

        obj.clear();
        homeObj.clickPersonalloan();
        DriverSetup.getLogger().info("User Successfully verified Home Loans");
    }

    // Priority 3
    @Then("the user verifies Personal Loan EMI and amount")
    public void verify_personal_loan() {
    	
        persObj = new PersonalLoanPage(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on Page Loan page");
        obj = jsonData.getJSONObject("Personal Loan");

        persObj.setLoanAmtSlider(obj.getInt("amount"));
        persObj.setInterestRateSlider(obj.getInt("interestRate"));
        persObj.setTenureSlider(obj.getInt("tenure"));
        DriverSetup.getLogger().info("All Slider Value Updated on Car Loan");

        Assert.assertEquals(persObj.getEMI(), obj.getString("validString"));
        System.out.println("Personal Loan EMI: " + persObj.getEMI());
        System.out.println("Total Amount: " + persObj.getPrincipalAmount());
        DriverSetup.getLogger().info("All Slider Value Updated on Personal Loan");

        obj.clear();
        persObj.clickCarloan();
    }

    // Priority 4
    @Then("the user verifies Car Loan interest and amount")
    public void verify_car_loan() {
        carLoanObj = new CarLoanPage(Hooks.getDriver());
        obj = jsonData.getJSONObject("Car Loan");
        DriverSetup.getLogger().info("User is on Car Loan page");

        carLoanObj.setLoanAmtSlider(obj.getInt("amount"));
        carLoanObj.setInterestRateSlider(obj.getInt("interestRate"));
        carLoanObj.setTenureSlider(obj.getInt("tenure"));
        DriverSetup.getLogger().info("All Slider Value Updated on Car Loan");
        Assert.assertTrue(carLoanObj.getInterest().contains(obj.getString("validString")));
        System.out.println("Interest Amount: " + carLoanObj.getInterest());
        System.out.println("Principal Amount: " + carLoanObj.getPrincipalAmount());

        obj.clear();
        try {
			carLoanObj.selectDropDown("Home Loan EMI Calculator");
		}
		catch(Exception e) {
			System.out.println();
		}	
    }

    // Priority 5
    @Then("the user verifies Home Loan EMI Calculator details")
    public void verify_home_loan_emi_calculator() {
        homeLoanObj = new HomeLoanEMIPage(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on Home Loan EMI Calculator page");
        
        homeLoanObj.setHomePrice(xmlData[0]);
        homeLoanObj.setDownPayment(xmlData[1]);
        homeLoanObj.setLoanInsurance(xmlData[2]);
        homeLoanObj.setLoanAmount();
        homeLoanObj.setInterestRate(xmlData[3]);
        homeLoanObj.setLoanFees(xmlData[4]);
        homeLoanObj.setStartDate(xmlData[5]);
        homeLoanObj.setHomeInsurance(xmlData[6]);
        homeLoanObj.setMaintenanceExpenses(xmlData[7]);
        
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
		DriverSetup.getLogger().info("Year on year table successfully written to excel");
		
		try {
			homeLoanObj.selectDropDown("Loan Calculator");
		}
		catch(Exception e) {
			System.out.println();
		}

        //Assert.assertEquals(homeLoanObj.getConfirmation(), xmlData[8]);
    }


    // Priority 7
    @Then("the user checks EMI Calculator UI")
    public void check_emi_calculator_ui() {
        emiUIObj = new EMICalculatorUI(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on EMI Calculator UI page");
        emiUIObj.firstClick();
        emiUIObj.setLoanAmtSlider(2500000);
        emiUIObj.setInterestRateSlider(7.5);
        DriverSetup.getLogger().info("EMI Calculator UI checked Successfully");
        emiUIObj.clickNext();
    }

    // Priority 8
    @Then("the user checks Loan Amount UI")
    public void check_loan_amount_ui() {
        loanUIObj = new LoanAmountUi(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on Loan Amount UI page");
        loanUIObj.setLoanAmtSlider(25000);
        loanUIObj.setInterestRateSlider(7.5);
        loanUIObj.setLoanTenureSlider(15);
        loanUIObj.clickNext();
        DriverSetup.getLogger().info("Loan Amount UI checked Successfully");
    }

    // Priority 9
    @Then("the user checks Loan Tenure UI")
    public void check_loan_tenure_ui() {
        tenureObj = new LoanTenureUI(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on Loan Tenure UI page");
        tenureObj.setLoanAmtSlider(2500000);
        tenureObj.setEMISlider();
        tenureObj.setInterestRateSlider(0);
        DriverSetup.getLogger().info("Loan Tenure UI checked Successfully");
        tenureObj.clickNext();
    }

    // Priority 10
    @Then("the user checks Interest Rate UI")
    public void check_interest_rate_ui() {
        iRateObj = new InterestRateUI(Hooks.getDriver());
        DriverSetup.getLogger().info("User is on Interest Rate UI page");
        iRateObj.setLoanAmtSlider(2500000);
        iRateObj.setLoanFeesSlider(50000);
        DriverSetup.getLogger().info("Interest Rate UI checked Successfully");
    }

    /*@AfterAll
    public static void tearDown() {
        DriverSetup objDriver = new DriverSetup();
        objDriver.driverTearDown();
    }*/
}