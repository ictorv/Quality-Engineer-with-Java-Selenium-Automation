package com.selenium.objectRepositories;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.selenium.utilities.DataDrivenUtils;

public class FilterPage {
	public WebDriver driver;
	public static List<String> dataSet;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	public Actions action;
	
	public FilterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements( driver,this);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		action = new Actions(driver);
	}
	
	public static void scroolTo(WebElement opt) {
		js.executeScript("arguments[0].scrollIntoView(true);", opt);
	}
	
	public static void jsclick(WebElement opt) {
		js.executeScript("arguments[0].click();", opt);
	}
	
	public static void waitUntil(WebElement opt) {
//		wait.until(ExpectedConditions.invisibilityOfAllElements(opt));
		wait.until(ExpectedConditions.elementToBeClickable(opt));
	}
	
	@FindBy(xpath="//*[@id='filters-content']/button[1]")
	WebElement setMonths;
	
	@FindBy(xpath="//*[@class='styles__MonthTile-sc-e7674c24-4 bMGRrT']//button[not(contains(@class, 'disabled'))]")
	List<WebElement> months;
	
	@FindBy(xpath="//*[@id='filter-modal']/section/button")
	WebElement seeResults;
	
	@FindBy(xpath="//*[@id='filters-content']/button[2]")
	WebElement destination;
	
	@FindBy(xpath="//div[@class='components__ButtonWrap-sc-6abd413c-1 gBFaFs']")
	List<WebElement> destOpt;
	
	@FindBy(xpath="//*[@id='filters-content']/button[3]")
	WebElement departure;
	
	@FindBy(xpath="//*[@class='components__ButtonWrap-sc-6abd413c-1 gBFaFs']")
	List<WebElement> deportOpt;
	
	@FindBy(xpath="/html/body/div[1]/div[3]/div/div/div[2]/div[2]/div/button[4]")
	WebElement setNights;
	
	
	@FindBy(xpath="//*[@class='NightsSelector__OverPill-sc-c59cb1b1-2 hiorfi']")
	List<WebElement> nightOpt;
	
	@FindBy(xpath="//*[@id='results-dropdown-sort']/div[2]/div/button")
	WebElement sort;
	
	@FindBy(xpath="//*[@id='results-dropdown-sort']/div[2]/div/div/div/ul/li[2]")
	WebElement lowToHigh;
	
	@FindBy(xpath="//div[contains(@id,'cruise-card_')]")
	List<WebElement> cards;
	
	@FindBy(xpath="//*[@id='search-modal']/section[2]")
	WebElement scrolToArea;
	
	@Test(priority=1)
	public String[] setMonths() {
		String[] monthsList = new String[4];
		try {
		setMonths.click();
		System.out.println("Available months: "+months.size());
		scroolTo(scrolToArea);
		System.out.println("Scrolling");
		System.out.println("Waiting for button to be clickable");
		waitUntil(seeResults);
		System.out.println("Wait Finished");
	
		Set<Integer> num = DataDrivenUtils.getRandNumSet(months.size());
		System.out.println(num);
		
		
		int j=0;
		for(int i: num) {
			WebElement opt = months.get(i);
//			scroolTo(opt);
			waitUntil(opt);
			monthsList[j++] = opt.getText();
			System.out.println("Select Month :"+monthsList[j-1]);
//			action.moveToElement(opt).click().build().perform();
			jsclick(opt);
//			opt.click();
		}
//		 Closing the Crouse Dates
		seeResults.click();
		System.out.println("Closing Months");
		
		
		}catch(Exception e) {
			e.getStackTrace();
		}
		return monthsList;
	}
	
	public String setDestionation() {
		String dest;
		destination.click();

		WebElement opt = destOpt.get(DataDrivenUtils.getRandNum(destOpt.size()));
		dest = opt.getText();
		System.out.println("Destination:" + dest);
		js.executeScript("arguments[0].scrollIntoView(true);", opt);
		waitUntil(opt);
//			opt.click();
		js.executeScript("arguments[0].click();", opt);

		seeResults.click();
		System.out.println("Clsoing Destination");
		return dest;

	}

	public String setDeparture() {
		departure.click();
		System.out.println("Departure Size:"+deportOpt.size());
		WebElement opt = deportOpt.get(DataDrivenUtils.getRandNum(deportOpt.size()));
		String d = opt.getText();
		System.out.println("Selecting Departure:" + d);

		//div[@class='components__ButtonWrap-sc-6abd413c-1 gBFaFs']//*[contains(text(),'Tamp')]

		scroolTo(opt);
		waitUntil(opt);
		int i=0;
		while(i<4) {
			try {
				System.out.println("Trying to find Element"+d);
				WebElement dp = driver.findElement(By.xpath("//div[@class='components__ButtonWrap-sc-6abd413c-1 gBFaFs']//*[contains(text(),'"+d+"')]"));
				js.executeScript("arguments[0].scrollIntoView(true);", dp);
				
				js.executeScript("arguments[0].click();", dp);
				break;
			}catch(StaleElementReferenceException e) {
				i++;
			}
		}
	
//		js.executeScript("arguments[0].scrollIntoView(true);", opt);
//		js.executeScript("arguments[0].click();", opt);
////		opt.click();
//		waitUntil(seeResults);
		seeResults.click();
		System.out.println("Closing departure");
		return d;

	}

	public String setTravelNight() {
		System.out.println("Selecting Travel nights");
		setNights.click();

		WebElement opt = nightOpt.get(DataDrivenUtils.getRandNum(nightOpt.size()));
		String d = opt.getText();
		System.out.println("Seting Travel Nights:" + d);
//			scroolTo(opt);
		js.executeScript("arguments[0].scrollIntoView(true);", opt);
		js.executeScript("arguments[0].click();", opt);
		waitUntil(opt);
		opt.click();

		seeResults.click();
		System.out.println("Closing Travel Nights");
		return d;
	}

	public void setSort() {
		System.out.println("Setting Sort....");
		sort.click();
		waitUntil(lowToHigh);
		lowToHigh.click();
		System.out.println("Sort Set");
	}

	public String getSearchResutls() {
		System.out.println("Getting the Result count.");
		System.out.println("Total Results:" + cards.size());
		return String.valueOf(cards.size());
	}

}
