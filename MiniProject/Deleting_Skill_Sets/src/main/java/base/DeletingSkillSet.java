/**
 * Test Class: DeletingSkillSet.java
 * 
 * Purpose:
 * - Automates the process of adding and deleting job categories, locations, and qualification skills 
 *   in the OrangeHRM application using Selenium and TestNG.
 * - Uses TestNG annotations for structured execution flow.
 * - Reads input test data dynamically from an Excel file using `ExcelUtil.java`.
 * - Generates test reports using Extent Reports and integrates screenshots for debugging.
 * - Supports multiple browser execution using parameterized inputs (`Chrome`, `Edge`).
 *
 * Methods:
 * - openBrowser(String browser): Initializes WebDriver and sets up page objects.
 * - loginToWebsite(): Performs login validation and stores results.
 * - createData(): Reads test data using `@DataProvider` from an Excel sheet.
 * - jobCategoriesOperation(): Validates the add/delete functionality of job categories.
 * - organizationLocationOperation(): Validates the add/delete functionality of locations.
 * - qualificationSkillsOperation(): Validates the add/delete functionality of skills.
 * - writeIntoExcel(): Logs test execution results into the Excel sheet.
 * - logoutOfWebsite(): Performs logout and verifies successful navigation back to login screen.
 */

package base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import config.DriverSetup;
import pages.AdminPage;
import pages.JobPage;
import pages.Login;
import pages.OrganizationPage;
import pages.QualificationPage;
import utils.ExcelUtil;
import utils.ExtentReportManager;

import org.testng.annotations.*;

public class DeletingSkillSet {

    WebDriver driver;
    Login loginObj;
    AdminPage adminPage;
    JobPage jobPage;
    OrganizationPage organizationPage;
    QualificationPage qualificationPage;
    String results[][];
    int a=0,b=0,c=0;
    String filePath = System.getProperty("user.dir") + "\\Test_Data\\2403763_DeletingSkillSet.xlsx";

    @BeforeClass
    @Parameters("browser")
    public void openBrowser(String browser) throws IOException {
        driver = DriverSetup.getDriver(browser); 
        ExtentReportManager.setDriver(driver); 
        loginObj = new Login(driver);
        adminPage = new AdminPage(driver);
        jobPage = new JobPage(driver);
        organizationPage = new OrganizationPage(driver);
        qualificationPage = new QualificationPage(driver);
        int rowCount = ExcelUtil.getRowCount(filePath, "Sheet1");
        results = new String[rowCount][5];
        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results[i].length; j++) {
                results[i][j] = ""; // Initialize empty values instead of null
            }
        }
        
    }

    
    
    @Test(priority=-1)
    public void loginToWebsite() throws IOException {
    	
        if (!loginObj.loginToWebsite()) {
        	 results[0][0] = "Successfully Logged in";
        } else {
            results[0][0] = "Login Failed";
            Assert.fail("Login Failed, aborting test execution!");
        }
        System.out.println(results);
    }

    @DataProvider(name = "dp")
    public Object[][] createData() throws IOException {
        int rowCount = ExcelUtil.getRowCount(filePath, "Sheet1");
        Object[][] data = new Object[rowCount][4];
        
        for (int i = 1; i <=rowCount ; i++) {
            data[i - 1] = new Object[] {
                ExcelUtil.getCellValue(filePath, "Sheet1", i, 0),
                ExcelUtil.getCellValue(filePath, "Sheet1", i, 1),
                ExcelUtil.getCellValue(filePath, "Sheet1", i, 2),
                ExcelUtil.getCellValue(filePath, "Sheet1", i, 3)
            };
        }
        
        return data;
    }

    @Test(priority = 1, dataProvider = "dp")
    public void jobCategoriesOperation(String jobName, String locationName, String country, String skillName) throws IOException, InterruptedException {
    	adminPage.navigateToAdmin();
        adminPage.navigateToPage("Job", "Job Categories");
        if (jobPage.addAndDeleteElement(jobName)) {
        	 results[a][1] = "Adding and Deleting the Job was Succesful";
             a++;
        } else {
        	results[a][1] = "Adding and Deleting the Job was Unsuccesful";
            a++;
            Assert.fail("Job operation failed!");
        }
    }

    @Test(priority = 2, dataProvider = "dp")
    public void organizationLocationOperation(String jobName, String locationName, String country, String skillName) throws IOException {
        //adminPage.navigateToAdmin();
        adminPage.navigateToPage("Organization", "Locations");
        if (organizationPage.addAndDeleteElement(locationName, country)) {
        	 results[b][2] = "Adding and Deleting the Location was Succesful";
             b++;
        } else {
        	 results[b][2] = "Adding and Deleting the Location was Unsuccesful";
             b++;
             Assert.fail("Location operation failed!");
        }
    }

    @Test(priority = 3, dataProvider = "dp")
    public void qualificationSkillsOperation(String jobName, String locationName, String country, String skillName) throws IOException {
        adminPage.navigateToPage("Qualifications", "Skills");
        if (qualificationPage.addAndDeleteElement(skillName)) {
            results[c][3] = "Adding and Deleting the Skill was Succesful";
            c++;
        } else {
            results[c][3] = "Adding and Deleting the Skill was Unsuccesful";
            c++;
            Assert.fail("Skill operation failed!");
        }
    }
    
    @AfterTest
    public void writeIntoExcel() throws IOException {
    	for(int i=0;i<results.length;i++) {
    		ExcelUtil.setCellValue(filePath, "Sheet1", i+1, 5, results[0][0]);
    		ExcelUtil.setCellValue(filePath, "Sheet1", i+1, 7, results[i][1]);
    		ExcelUtil.setCellValue(filePath, "Sheet1", i+1, 9, results[i][2]);
    		ExcelUtil.setCellValue(filePath, "Sheet1", i+1, 11, results[i][3]);
    		ExcelUtil.setCellValue(filePath, "Sheet1", i+1, 13, results[0][4]);
    	}
    }

    @AfterClass
    public void logoutOfWebsite() throws IOException {
        if (adminPage.logout()) {
        	results[0][4] = "Successfully Logged out";
        	Assert.assertTrue(true);
        	}
        else {
        	results[0][4] = "Logout Failed";
            Assert.fail("Logout Failed!");
        }
        driver.quit();
    }
}
