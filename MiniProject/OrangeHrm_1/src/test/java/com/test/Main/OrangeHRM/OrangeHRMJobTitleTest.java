package com.test.Main.OrangeHRM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.test.Utilitylibraries.OrangeHRM.DriverSetup;
import com.test.objects.AdminJobTitlePage;
import com.test.objects.LoginPage;
import com.xlreading.ExcelDataFetch;


public class OrangeHRMJobTitleTest {
	
	static String sheet = "Orange";
	static String path = "src\\test\\java\\test\\Scripts\\projectOrangeHrm.xlsx";
	static WebDriver driver;
	private static String user;
	private static String password;
	static String jobTitle;
	
    public static void main(String[] args) throws IOException {
        DriverSetup ds = new DriverSetup();
        
        ExcelDataFetch edf = new ExcelDataFetch();
        Object[][] val = edf.excelDataFetch(path, sheet);
        DriverSetup.url = val[1][0].toString();
        //System.out.println(DriverSetup.url);
        driver=ds.driverInstantiate("Chrome");
        user = val[1][1].toString();
        password = val[1][2].toString();
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user, password);
 
        // Go to Admin > Job Titles
        AdminJobTitlePage jobPage = new AdminJobTitlePage(driver);
        jobPage.goToJobTitles();
 
        // Add job title if not exists
        jobTitle = val[1][3].toString();
        if (!jobPage.isJobTitlePresent(jobTitle)) {
            jobPage.addJobTitle(jobTitle);
            System.out.println("Job Title '" + jobTitle + "' added.");
        } else {
            System.out.println("Job Title '" + jobTitle + "' already exists.");
        }
 
        // Logout
        jobPage.logout();
 
        // Close browser
       // driver.quit();
    }




}
