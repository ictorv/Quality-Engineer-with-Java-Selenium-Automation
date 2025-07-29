package com.test.object.ProductListAutomation;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.utility.ProductListAutomation.ScreenShot;

public class FilterPanel
{
	private WebDriver driver;
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    @FindBy(xpath = "//*[@class='button-container button-size--small button-outline ng-star-inserted']/div/span")
    private List<WebElement> filterTabs;

    @FindBy(xpath = "//label[@class='checkbox-label']")
    private List<WebElement> checkboxOptions;

    @FindBy(xpath = "//span[text()='APPLY']")
    private WebElement applyButton;
    
    @FindBy(xpath="//span[text()='Express Shipping in']")
    private WebElement scroll;

    public FilterPanel(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void applyMaterialMetalFilter()
    {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,400)");
        try 
        {
			ScreenShot.screenShotTC(driver, "04_FilterPage");
		} 
        catch (IOException e)
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (WebElement tab : filterTabs)
        {
            if (tab.getText().contains("Material")) 
            {
                tab.click();
                break;
            }
        }

        for (WebElement option : checkboxOptions)
        {
            if (option.getText().contains("Metal"))
            {
                option.click();
                break;
            }
        }

        applyButton.click();
        
        
    }



}
