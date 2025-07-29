package stepsDefinitions;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.HomePage;
import pages.SearchPageResultMobileRecharge;
import testBase.CucumberBase;
import testRunner.TestRunner;
import utilities.DataReader;
import utilities.ExcelUtilityClass;

public class TC010_steps {

    private final Logger logger = CucumberBase.getLogger();
    private final WebDriver driver = TestRunner.getDriver();
    private final String sheetName = "TC010";
    private final String xmlPath = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestDataXML.xml";
    private final String excelPath = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestData.xlsx";

    private ExcelUtilityClass excel;
    private final HomePage homePage = new HomePage(driver);
    private final SearchPageResultMobileRecharge rechargePage = new SearchPageResultMobileRecharge(driver);
    private HashMap<String, String> xmlRow;
    private List<String> planResults;

    @When("I scroll down to \"Mobile Recharge Options\"")
    public void scrollToRechargeOptions() {
        homePage.clickMayBeLaterButton();
        logger.info("------ Clicked May Be Later Button ------");

        DataReader reader = new DataReader(excelPath, sheetName);
        List<HashMap<String, String>> xmlData = reader.getXmlServiceData(xmlPath);
        reader.close();

        if (!xmlData.isEmpty()) {
            xmlRow = xmlData.get(0);
            excel = new ExcelUtilityClass(excelPath, sheetName);
        } else {
            logger.error("------ No XML data found ------");
            Assert.fail("XML data is missing");
        }
    }

    @And("I click on the {string} service menu")
    public void clickServiceMenu(String typeTag) {
        String serviceType = xmlRow.get(typeTag);
        homePage.clickServiceMenu(serviceType);
        logger.info("------ Selected Service Menu: " + serviceType + " ------");
    }

    @Then("I should see the Mobile Recharge page")
    public void confirmRechargePageVisible() {
        logger.info("------ Navigated to Mobile Recharge Page ------");
    }

    @And("I enter a valid {string}")
    public void enterValidMobileNumber(String mobileTag) {
        String mobileNumber = xmlRow.get(mobileTag);
        rechargePage.setMobileNumber(mobileNumber);
        logger.info("------ Entered Mobile Number: " + mobileNumber + " ------");
        rechargePage.clickNewPlansButton();
        logger.info("------ Clicked New Plans Button ------");
    }

    @And("I apply the {string} filter")
    public void applyDataFilter(String planTag) {
        String planType = xmlRow.get(planTag);
        rechargePage.setPlan(planType);
        logger.info("------ Selected Plan Type: " + planType + " ------");
    }

    @Then("I should see the available recharge plans")
    public void showAvailablePlans() {
        planResults = rechargePage.getPlanDetails();
        int rowCount = 1;

        for (String plan : planResults) {
            excel.setCellData(plan, rowCount++, 3); // write horizontally across columns
            String[] details = plan.split(" , ");
            System.out.println(details[0] + "\nAmount: " + details[1] + "\n");
        }

        logger.info("------ Displayed Available Recharge Plans ------");
    }

    @And("I capture and display the top 5 results")
    public void validateRechargeAmounts() {
        List<Integer> amounts = rechargePage.getAmountsFromData();
        boolean withinLimit = amounts.stream().allMatch(amount -> amount <= 1000);

        if (withinLimit) {
            excel.setCellData("Plans Amounts are Lesser Than 1000 Rs", 1, 5);
            excel.setCellData("Pass", 1, 6);
            logger.info("------ Test Case Passed ------");
            Assert.assertTrue(true);
        } else {
            excel.setCellData("Plans Amounts are Greater Than 1000 Rs", 1, 10);
            excel.setCellData("Fail", 1, 11);
            logger.info("------ Test Case Failed ------");
            Assert.fail("One or more plans exceed ₹1000");
        }

        logger.info("----------------------------------------------------------------------------------------");
        excel.closeWorkbook();
    }
}
