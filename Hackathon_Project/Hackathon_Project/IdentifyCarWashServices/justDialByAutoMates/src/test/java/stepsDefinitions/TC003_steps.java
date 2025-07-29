package stepsDefinitions;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import testBase.CucumberBase;
import testRunner.TestRunner;
import utilities.DataReader;
import utilities.ExcelUtilityClass;

public class TC003_steps {

    private final Logger logger = CucumberBase.getLogger();
    private final WebDriver driver = TestRunner.getDriver();
    private final HomePage homePage = new HomePage(driver);

    private final String sheetName = "TC003";
    private final String path = System.getProperty("user.dir") + "\\testData\\Identify-Car-Wash-Services_TestData.xlsx";
    private ExcelUtilityClass excel;
    private HashMap<String, String> row;
    private List<String> gymSubMenuList;
    private int rowIndex;

    @When("I scroll down to Popular Categories")
    public void scrollToPopularCategories() {
        logger.info("------ Starting TC003_GymSubMenuList ------");
        homePage.clickMayBeLaterButton();
        logger.info("------ Clicked May Be Later Button ------");
        logger.info("------ Scrolled to section : Popular Categories ------");
    }

    @And("I choose the {string} category")
    public void chooseCategory(String rowIndexStr) {
        rowIndex = Integer.parseInt(rowIndexStr);

        try {
            DataReader reader = new DataReader(path, sheetName);
            row = reader.getRowData(rowIndex);
            reader.close();
            excel = new ExcelUtilityClass(path, sheetName);
        } catch (Exception e) {
            logger.error("Failed to load data from row " + rowIndexStr, e);
            Assert.fail("Could not read data from Excel");
        }

        String category = row.get("Sub Menu");
        homePage.setSubMenu(category);
        logger.info("------ Clicked the category: " + category + " ------");
    }

    @Then("I should find the subtypes of Gym and Fitness")
    public void captureSubMenuList() {
        gymSubMenuList = homePage.getGymSubMenuList();
        Assert.assertFalse(gymSubMenuList.isEmpty(), "Submenu list should not be empty");
        logger.info("------ Retrieved " + gymSubMenuList.size() + " subtypes ------");
    }

    @And("I should capture and display them")
    public void displayAndValidateSubMenus() {
        int writeRow = rowIndex;

        logger.info("------ Displaying Gym/Fitness submenus ------");
        System.out.println("--------------------------------------------------------------------------------");

        for (int i = 0; i < gymSubMenuList.size(); i++) {
            String value = gymSubMenuList.get(i);
            System.out.println((i + 1) + ". " + value);
            excel.setCellData(value, writeRow++, 1);
        }

        System.out.println("--------------------------------------------------------------------------------");

        boolean isValid = gymSubMenuList.stream().allMatch(
            item -> item.toLowerCase().contains("gym") || item.toLowerCase().contains("fitness")
        );

        if (isValid) {
            excel.setCellData("The Search result contains gym/fitness", rowIndex, 3);
            excel.setCellData("Pass", rowIndex, 4);
            logger.info("------ Test Case Passed ------");
            Assert.assertTrue(true);
        } else {
            excel.setCellData("The Search result does NOT contain gym/fitness", rowIndex, 3);
            excel.setCellData("Fail", rowIndex, 4);
            logger.error("------ Test Case Failed ------");
            Assert.fail("Results do not match gym/fitness");
        }

        logger.info("--------------------------------------------------------------------------------");
        excel.closeWorkbook();
    }
}
