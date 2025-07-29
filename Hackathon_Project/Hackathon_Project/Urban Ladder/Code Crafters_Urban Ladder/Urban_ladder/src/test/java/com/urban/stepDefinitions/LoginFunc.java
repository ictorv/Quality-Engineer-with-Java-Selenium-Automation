package com.urban.stepDefinitions;
 
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import com.urban.base.BaseClass;
import com.urban.pageObject.BasePage;
import com.urban.pageObject.HomePage;
import com.urban.pageObject.Login;
import com.urban.utillities.ExcelUtil;
import com.urban.utillities.ScreenShot;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
 
public class LoginFunc {
 
    private static final Logger logger = LogManager.getLogger(LoginFunc.class);
 
    HomePage home;
    Login login;
 
    @Given("User loads login data")
    public void user_loads_login_data_from_excel() {
        logger.info("Navigating to login screen.");
        home = new HomePage(BaseClass.getDriver());
        home.hoverOverProfileIcon();
        home.clickLogin();
        logger.info("Login screen loaded successfully.");
    }
 
    @When("User logs in with the following credentials:")
    public void user_logs_in_with_credentials(DataTable dataTable) {
        login = new Login(BaseClass.getDriver());
        List<Map<String, String>> loginData = dataTable.asMaps(String.class, String.class);
 
        int rowIndex = 1;
 
        for (Map<String, String> row : loginData) {
            String email = row.get("email");
            String password = row.get("password");
            String scenario = row.get("scenario").trim().toLowerCase();
 
            logger.info("Running login test for row " + rowIndex + " - Scenario: " + scenario);
 
            login.clearEmailField();
            login.clearPasswordField();
 
            String resultMessage = "";
            String testOutcome = "";
 
            try {
                switch (scenario) {
                    case "missing_email":
                        login.enterPassword(password);
                        break;
                    case "missing_password":
                        login.enterEmail(email);
                        break;
                    default:
                        login.enterEmail(email);
                        login.enterPassword(password);
                        break;
                }
 
                login.submitLogin();
 
                switch (scenario) {
                    case "missing_email":
                        resultMessage = login.getEmailErrorMessage();
                        break;
                    case "missing_password":
                        resultMessage = login.getPasswordErrorMessage();
                        break;
                    case "invalid_login":
                        resultMessage = login.getErrorMessage();
                        break;
                    case "valid_login":
                        try {
                            String msg = login.getErrorMessage();
                            resultMessage = "Unexpected Error: " + msg;
                            testOutcome = "Failed";
                        } catch (Exception e) {
                            resultMessage = "Login Passed";
                            home.hoverOverProfileIcon();
                            ScreenShot.captureScreenshot(BasePage.driver, "Login");

                            home.clickLogout();
                            testOutcome = "Passed";
                        }
                        break;
                }
 
                if (!scenario.equals("valid_login")) {
                    testOutcome = "Passed";
                }
 
            } catch (Exception e) {
                resultMessage = "Exception: " + e.getMessage();
                testOutcome = "Failed";
                logger.error("Exception during login scenario execution: ", e);
            }
 
            ExcelUtil.writeDataIntoExcel("Login", rowIndex, 3, resultMessage);
            ExcelUtil.writeDataIntoExcel("Login", rowIndex, 4, testOutcome);
 
            logger.info("Login result for row " + rowIndex + ": " + testOutcome);
            rowIndex++;
        }
    }
}
 
 