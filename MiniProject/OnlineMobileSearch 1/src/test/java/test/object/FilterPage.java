package test.object;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.userDefinedLibraries.FetchData;
import test.userDefinedLibraries.Screenshot;

public class FilterPage {

	private WebDriver driver;
	private String searchQuery= "\""+FetchData.list[1][1].toString()+"\"";
	private String validatingString=FetchData.list[1][2].toString();
	public FilterPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath="//*[@id='a-autoid-0']/span/span/child::*")
	List<WebElement> newArrivalFilter;
	
	@FindBy(xpath ="//*[@id=\"search\"]/span/div/h1//div[1]//div[2]/h2")
	WebElement isShowingResultBarVisibile;
	
	@FindBy(xpath="//*[@class='sg-col-inner']//h2/child::span[3]")
	WebElement isSearchQueryVisibile;
	
	@FindBy(xpath="//*[@class='sg-col-inner']//div[@role='listitem']")
	List<WebElement> isPageLoaded;
	
	
	//Validate the sort By feature
	public void validateTheSortBy() {
		
		String getStr = "";
		
		for(int i=0;i<newArrivalFilter.size();i++) {
			getStr+=newArrivalFilter.get(i).getAttribute("innerText");
			if(!(i==newArrivalFilter.size()-1))
				getStr+=" ";

		}
		
		if(validatingString.equalsIgnoreCase(getStr))
			try {
				FetchData.writeIntoExcel("New Arrivals is selected", 1, 5);
			} catch (Exception e) {
		
				e.printStackTrace();
			}
		else
			try {
				FetchData.writeIntoExcel("New Arrivals is not selected", 1, 5);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
	}
	//navigating to validateTheResult
	public void validateTheResult() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		if(isShowingResultBarVisibile.isDisplayed()) {
			
			String extractQuery = isSearchQueryVisibile.getText();
			if(searchQuery.equalsIgnoreCase(extractQuery)) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOfAllElements(isPageLoaded));
				Screenshot.getScreenshot(driver,"Result");
				
					try {
						FetchData.writeIntoExcel("Page Loaded Successfully", 1, 3);
					} catch (Exception e) {
				
						e.printStackTrace();
					}
				
				
				} else
				try {
					FetchData.writeIntoExcel("Page Not Loaded Successfully", 1, 3);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		
			}
		}
		
	}

