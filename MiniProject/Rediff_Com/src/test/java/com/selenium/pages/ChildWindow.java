package com.selenium.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.selenium.data.ExcelUtils;
import com.selenium.rediffUtilities.ExtentReportManager;
import com.selenium.data.Constants;


public class ChildWindow 
{
    WebDriver driver;
    ExcelUtils eu;
    ExtentReportManager erm;

    //Constructor
    public ChildWindow(WebDriver driver, ExcelUtils eu, ExtentReportManager erm) 
    {
        this.driver = driver;
        this.eu = eu;
        this.erm = erm;
    }

    // Validating Child Window Title  
    public void childWindowValidation(String expectedTitle, String sheetName, String screenshotName) throws IOException 
    {
        String actualTitle = driver.getTitle();
        eu.setCellData(sheetName, Constants.ROW_CHILD_TITLE, Constants.COL_ACTUAL, actualTitle);
        eu.validation(sheetName, Constants.ROW_CHILD_TITLE, Constants.COL_RESULT, actualTitle, expectedTitle, erm, driver, screenshotName);
    }

    // Closing Child window
    public void closeChildWindow() 
    {
        driver.close();
    }

    // Focus back to Parent Window
    public void switchToParentWindow(String parentHandle) 
    {
        driver.switchTo().window(parentHandle);
    }
}
