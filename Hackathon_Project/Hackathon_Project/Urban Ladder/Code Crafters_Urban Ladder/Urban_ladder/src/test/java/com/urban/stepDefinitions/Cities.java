package com.urban.stepDefinitions;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.HomePage;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cities {

    WebDriver driver = BaseClass.driver;
    HomePage home;
    Logger log = LogManager.getLogger(Cities.class);

    @When("I hover over the Stores tab")
    public void hoverOnStoresTab() {
        home = new HomePage(driver);
        log.info("Hovering over the Stores tab.");
        home.hoverOverStoresTab();
        ScreenShot.captureScreenshot(BasePage.driver, "Cities");

    }

    @Then("I should see a list of store states")
    public void storeStatesShouldBeListed() throws InterruptedException {
        List<String> states = home.getStoreStates();
        log.info("Retrieved store states: " + states);
        if (states.isEmpty()) {
            log.error("No store states found.");
            throw new AssertionError("No store states found.");
        }
    }

    @And("I write the store state names to the Excel sheet {string}")
    public void saveStatesToExcel(String sheetName) throws InterruptedException {
        List<String> states = home.getStoreStates();
        log.info("Saving store states to Excel sheet: " + sheetName);
        int row = 1;
        for (String state : states) {
            log.debug("Writing state to Excel: " + state);
            ExcelUtil.writeDataIntoExcel(sheetName, row++, 0, state);
        }
        log.info("Finished writing states to Excel.");
    }
}








































































//package com.urban.stepDefinitions;
//
//import java.util.List;
//
//import org.openqa.selenium.WebDriver;
//
//import com.urban.base.BaseClass;
//import com.urban.pageObject.HomePage;
//import com.urban.utillities.ExcelUtil;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class Cities {
//
//    WebDriver driver = BaseClass.driver;
//    HomePage home;
//
//
//    @When("I hover over the Stores tab")
//    public void hoverOnStoresTab() {
//    	home=new HomePage(driver);
//        home.hoverOverStoresTab();
//    }
//
//    @Then("I should see a list of store states")
//    public void storeStatesShouldBeListed() throws InterruptedException {
//        List<String> states = home.getStoreStates();
//        if (states.isEmpty()) {
//            throw new AssertionError("No store states found.");
//        }
//    }
//
//    @And("I write the store state names to the Excel sheet {string}")
//    public void saveStatesToExcel(String sheetName) throws InterruptedException {
//        List<String> states = home.getStoreStates();
//        int row = 1;
//        for (String state : states) {
//            ExcelUtil.writeDataIntoExcel("Cities", row++, 0, state);
//        }
//    }
//}
