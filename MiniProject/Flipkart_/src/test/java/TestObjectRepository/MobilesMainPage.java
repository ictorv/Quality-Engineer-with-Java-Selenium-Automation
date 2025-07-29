package TestObjectRepository;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestData.DataDrivenUtils;
import UserDefinedLibraries.WaitUtils;

public class MobilesMainPage {

    public WebDriver driver;
    private WaitUtils waitObj;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    private Wait<WebDriver> fwait;
    public String sheetName = "FlipKart TestData";
    

    public MobilesMainPage(WebDriver driver) {
        this.driver = driver;
        waitObj = new WaitUtils();
        fwait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(ElementClickInterceptedException.class);
        PageFactory.initElements(driver, this);
        

    }
    
    @FindBy(className = "retry_btn")
    WebElement retrybtn;
    
    @FindBy(className = "Oyj7AF")
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
    
    @FindBy(xpath = "(//*[@id='container']//div[@class='yKfJKb row']/div[contains(@class,'col col')]/div[@class='KzDlHZ'])[position()<=5]")
    List<WebElement> names;
    
    @FindBy(xpath = "(//*[@id='container']/div/div[3]/div/div[2]/div/div/div/div/a/div[2]/div[2]/div[1]/div[1]/div[1])[position()<=5]")
    List<WebElement> prices;
    

    public void applyPriceSlider() {
        new Actions(driver).clickAndHold(slider).moveByOffset(-50, 0).release().perform();
    }

		public void applyOperatingSystemFilter() {
	    // Scroll into view
         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", osSection);
         driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		}

		@FindBy(xpath = "(//*[contains(@class,\"fxf7w6 rgHxCQ\")])[21]")
		WebElement discount;
		
		@FindBy(xpath = "(//*[contains(@class,\"fxf7w6 rgHxCQ\")])[22]")
		WebElement operatingSystem;
		
		@FindBy(xpath = "(//div[contains(@class,\"e+xvXX KvHRYS\")])[2]")
		WebElement more12;
		
		@FindBy(xpath = "//div[contains(text(),'Pie')]")
		WebElement pie2;
		
		@FindBy(xpath="//*[contains(@class,'fxf7w6 rgHxCQ')]")
		WebElement toScroll;
		
    public void selectAndroidVersion() {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='_6tw8ju' and text()='Min-₹10000']"))));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",toScroll);
		
		//Wait until pie is visible
		wait.until(ExpectedConditions.visibilityOf(operatingSystem));
		operatingSystem.click();
		
		wait.until(ExpectedConditions.visibilityOf(more12));
		
		more12.click();
		pie2.click();
   }

    

    public void sortByNewest() throws InterruptedException {
    	driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(newest));
        newest.click();
    }
    
    @FindBy(xpath = "//a[@class='CGtC98']//div[@class='KzDlHZ']")
	List<WebElement> mobileNames;
	
	@FindBy(xpath = "//a[@class='CGtC98']//div[@class='Nx9bqj _4b5DiR']")
	List<WebElement> mobilePrice;
	public Map<WebElement,WebElement> getData() {
		Map<WebElement, WebElement> productMap = new HashMap<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		for(int i=0;i<mobileNames.size();i++) {
			
			js.executeScript("arguments[0].scrollIntoView(false);",mobileNames.get(i));
			WebElement name = mobileNames.get(i);
			WebElement price = mobilePrice.get(i);
			
			productMap.put(name, price);
		}
		
		return productMap;
		
		
	}
	
	public void writeFirstFiveProducts(List<WebElement> names, List<WebElement> prices) throws IOException {
		
		int size = Math.min(5, Math.min(names.size(), prices.size()));
				
		 System.out.println("Writing top " + size + " products to Excel:");
		 for (int i = 0; i <5; i++) {
	            String name = names.get(i).getText().trim();
	            String price = prices.get(i).getText().trim();
	            String productInfo = "Product: " + name + " | Price: " + price;

	            DataDrivenUtils.writeDataIntoExcel(i+1, 3, productInfo);
	            
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
