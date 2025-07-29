package com.selenium.pages;

import java.io.IOException;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.data.ExcelUtils;
import com.selenium.rediffUtilities.ExtentReportManager;
import com.selenium.rediffUtilities.WaitUtils;
import com.selenium.data.Constants;



public class ParentWindow 
{
    WebDriver driver;
    public String parentHandle;
    ExcelUtils eu;
    ExtentReportManager erm;

    @FindBy(xpath = "//a[@title='Create Rediffmail Account']")
    WebElement createNewAccount;
 
    @FindBy(xpath = "//a")
    List<WebElement> links_on_website;
    
    //Constructor
    public ParentWindow(WebDriver driver, ExcelUtils eu, ExtentReportManager erm) 
    {
        this.driver = driver;
        this.eu = eu;
        this.erm = erm;
        PageFactory.initElements(driver, this);
    }
    
    
    // Clicking on create account
    public void createAcccountClick() 
    {
        createNewAccount.click();
    }

    // Validating the create account is opened or not
    public void createAccountValidation(String expectedLink, String sheetName, String screenshotName) throws IOException 
    {
        String actualLink = driver.getCurrentUrl();
        eu.setCellData(sheetName, Constants.ROW_CREATE_ACCOUNT, Constants.COL_ACTUAL, actualLink);
        eu.validation(sheetName, Constants.ROW_CREATE_ACCOUNT, Constants.COL_RESULT, actualLink, expectedLink, erm, driver, screenshotName);
    }

    // Finding total available links in the website & Printing it on Excel
    public void noOfLinks(String sheetName) throws IOException 
    {
        eu.setCellData(sheetName, 1, 0, String.valueOf(links_on_website.size()));
        int flag = 1;
        for (WebElement ele : links_on_website) 
        {
            eu.setCellData(sheetName, flag, 1, ele.getDomAttribute("href"));
            flag++;
        }
    }

    // Clicking on Terms and Condition
    public void termsAndCondition() throws InterruptedException 
    {
        parentHandle = driver.getWindowHandle();
        for (WebElement ele : links_on_website) 
        {
            if ((ele.getText()).contains("terms and conditions")) 
            {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", ele);
                WaitUtils.waitForDuration(driver,3);
                ele.click();
            }
        }
    }

    
    // Switching to Child Window and Validating
    public void switchToChildWindow(String expectedLink, String sheetName, String screenshotName) throws IOException 
    {
        Set<String> windowids = driver.getWindowHandles();
        for (String hd : windowids) 
        {
            if (!hd.equalsIgnoreCase(parentHandle)) 
            {
                driver.switchTo().window(hd);
                break;
            }
        }
        String actualLink = driver.getCurrentUrl();
        eu.setCellData(sheetName, Constants.ROW_CHILD_WINDOW, Constants.COL_ACTUAL, actualLink);
        eu.validation(sheetName, Constants.ROW_CHILD_WINDOW, Constants.COL_RESULT, actualLink, expectedLink, erm, driver, screenshotName);
    }
}
