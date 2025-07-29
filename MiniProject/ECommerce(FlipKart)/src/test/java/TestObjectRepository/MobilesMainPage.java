package TestObjectRepository;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * MobilesMainPage class represents the FlipKart mobiles listing page and provides methods
 * to interact with various UI elements such as filters, sorting options, and product listings.
 * It uses PageFactory to initialize web elements and supports actions like applying filters,
 * sorting products, extracting product details, and validating price constraints.
 * 
 * Features:
 * - Apply price slider and OS filters
 * - Select Android version
 * - Sort products by newest
 * - Extract and write product names and prices to Excel
 * - Validate product price against a limit
 * 
 * Dependencies:
 * - Selenium WebDriver
 * - PageFactory for element initialization
 * - DataDrivenUtils for writing data to Excel
 */


import TestData.DataDrivenUtils;

public class MobilesMainPage {

    public WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    
    public MobilesMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(className = "retry_btn")
    WebElement retrybtn;
    
    @FindBy(className = "Oyj7AF67")
    WebElement slider;
    
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[1]/div/div[1]/div/section[24]/div[1]")
    WebElement osSection;
    
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[1]/div/div[1]/div/section[25]/div[1]/div")
    WebElement versionDropdown;
    
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[1]/div/div[1]/div/section[25]/div[2]/div[2]/span")
    WebElement expandButton;
    
    @FindBy(xpath = "//section[@class='-5qqlC _2OLUF3']//div[text()='Operating System Version Name']/following::div[@title='Pie']//input//following-sibling::div[1]")
    WebElement pie;
    
    @FindBy(xpath = "//*[@id='container']/div/div[3]/div/div[2]/div[1]/div/div/div[2]/div[5]")
    WebElement newest;
    
    
    @FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div/div[2]/div[1]/div/div/span")
    WebElement vait;
    
    @FindBy(xpath = "(//*[@id='container']//div[@class='yKfJKb row']/div[contains(@class,'col col')]/div[@class='KzDlHZ'])[position()<=5]")
    List<WebElement> names;
    
    @FindBy(xpath = "(//*[@id='container']/div/div[3]/div/div[2]/div/div/div/div/a/div[2]/div[2]/div[1]/div[1]/div[1])[position()<=5]")
    List<WebElement> prices;

    Wait<WebDriver> fwait = new FluentWait<>(driver)
    	    .withTimeout(Duration.ofSeconds(30))       // Maximum wait time
    	    .pollingEvery(Duration.ofSeconds(5))       // Frequency of checks
    	    .ignoring(NoSuchElementException.class)
    	    .ignoring(ElementClickInterceptedException.class);  // Ignore this exception


    public void applyPriceSlider() {
        new Actions(driver).clickAndHold(slider).moveByOffset(-50, 0).release().perform();
    }

    
		public void applyOperatingSystemFilter() {
	    // Scroll into view
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", osSection);
         // Explicit wait for the element to be visible
         driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		}

    public void selectAndroidVersion() throws InterruptedException {
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(versionDropdown)).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(13));
        wait.until(ExpectedConditions.elementToBeClickable(expandButton)).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pie);
        wait.until(ExpectedConditions.elementToBeClickable(pie));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pie);
        wait.until(ExpectedConditions.visibilityOf(vait));
   }


    public void sortByNewest() throws InterruptedException {
    	while(true) {
    		try {
            	fwait.until(ExpectedConditions.elementToBeClickable(newest));
                newest.click();
                break;
            }
            catch(Exception e){
            	driver.navigate().refresh();
            } 
    	}
    }
    
    public List<WebElement> getProductNames() {
        return names;
    }

    public List<WebElement> getProductPrices() {
        return prices;
    }
    

    public void writeFirstFiveProducts(List<WebElement> names, List<WebElement> prices) throws IOException {
        int size = Math.min(5, Math.min(names.size(), prices.size())); // Limit to first 5

        System.out.println("Writing top " + size + " products to Excel:");
        for (int i = 0; i < size; i++) {
            String name = names.get(i).getText();
            String price = prices.get(i).getText();
            String productInfo = "Product: " + name + " | Price: " + price;

            DataDrivenUtils.writeDataIntoExcel(i+1, 4, productInfo);
            System.out.println("Product " + (i + 1) + ": " + productInfo);
        }

        System.out.println("Successfully written first 5 products to Excel!");
    }
    
    
    
    public void checkFirstProductPriceUnderLimit(List<WebElement> prices, int priceLimit) {
        if (prices == null || prices.isEmpty()) {
            System.out.println("Price list is empty. Cannot perform check.");
            return;
        }

        try {
            String priceText = prices.get(0).getText().replace("₹", "").replace(",", "").trim();
            int firstPrice = Integer.parseInt(priceText);

            if (firstPrice <= priceLimit) {
                System.out.println("First product price (" + firstPrice + ") is less than or equal to ₹" + priceLimit);
            } else {
                System.out.println("First product price (" + firstPrice + ") is greater than ₹" + priceLimit);
            }
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse price: " + e.getMessage());
        }
    }


}
