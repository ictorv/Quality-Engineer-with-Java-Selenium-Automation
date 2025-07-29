package com.test.object.ProductListAutomation;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.utility.ProductListAutomation.ScreenShot;

public class ProductListingPage
{
	private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    @FindBy(xpath = "[@class='clip-catg-title text-sm font-medium text-truncate-2 ctg-white-space paddingTop-8']")
    private List<WebElement> itemTitles;

    @FindBy(xpath = "//div[@class='clip-catg-content text-xs font-medium color-secondary ng-star-inserted']")
    private List<WebElement> itemCounts;

    @FindBy(xpath = "//span[text()='7 options in']")
    private WebElement finalCount;
    
    public ProductListingPage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    List<String> list=new ArrayList<String>();
    public List<String> printProductCounts() {
        for (int i = 1; i < itemCounts.size(); i++)
        {
            System.out.println("Count of "+itemTitles.get(i).getText() + ":" + itemCounts.get(i).getText().split(" ")[0]);
            
            list.add(itemTitles.get(i).getText() + ":" + itemCounts.get(i).getText().split(" ")[0]);
        }
        return list;
    }

    public String printFinalCount()
    {
        
        wait.until(ExpectedConditions.visibilityOf(finalCount));
           
        System.out.println("Count of benches after filtering:" + finalCount.getText().split(" ")[0]);
        return finalCount.getText().split(" ")[0];
    }
    
    
    
}
