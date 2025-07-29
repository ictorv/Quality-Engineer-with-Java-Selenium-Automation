package test.object;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.userDefinedLibraries.FetchData;
import test.userDefinedLibraries.Screenshot;

public class HomePage {

	private WebDriver driver;
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	
	@FindBy(xpath ="//*[@id='twotabsearchtextbox']")
	WebElement searchBar;
	
	@FindBy(id="sac-autocomplete-results-container")
	WebElement suggestionBox;
	
	
	//navigating to getHomePage
	public void getHomePage() {
		String searchWord = FetchData.list[1][1].toString();
		searchBar.sendKeys(searchWord);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(suggestionBox));
		//Add Screenshot after wait condition i.e after suggestion box
		Screenshot.getScreenshot(driver,"Home");
		searchBar.submit();
	}
	

}
