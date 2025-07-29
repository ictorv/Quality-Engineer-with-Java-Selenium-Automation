package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarLoanPage extends BasePage{
	
	public CarLoanPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "loanamount")
	WebElement loanInput;
	@FindBy(id = "loanamountslider")
	WebElement loanSliderBar;
	@FindBy(xpath = "//*[@id='loanamountslider']/span")
	WebElement carLoanAmt;
	
	
	@FindBy(id = "loaninterest")
	WebElement interestInput;
	@FindBy(id = "loaninterestslider")
	WebElement interestSliderBar;
	@FindBy(xpath = "//*[@id='loaninterestslider']/span")
	WebElement interestRate;
	
	
	@FindBy(id = "loanterm")
	WebElement termInput;
	@FindBy(id = "loantermslider")
	WebElement tenureSliderBar;
	@FindBy(xpath = "//*[@id='loantermslider']/span")
	WebElement loanTenure;
	
	
	@FindBy(id = "emitotalinterest")
	WebElement totalInterest;
	
	@FindBy(id = "emitotalamount")
	WebElement totalPayment;
	
	// Set Car Loan Amount to target value using slider
	public void setLoanAmtSlider(int target) {
		
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		iMin = 0;
		iMax = 2000000;
		moveSlider(loanInput, carLoanAmt, loanSliderBar, iMin, iMax, target);
	}
	
	//Set Car Interest rate to target value using slider
	public void setInterestRateSlider(double target) {
		
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 5;
		fMax = 20;
		moveSlider(interestInput, interestRate, interestSliderBar, fMin, fMax, target);
	}
	
	//Set Tenure to target value using slider
	public void setTenureSlider(double target) {
		
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0.1;
		fMax = 7;
		moveSlider(termInput, loanTenure, tenureSliderBar, fMin, fMax, target);
	}
	
	//Display the Interest Amount and Principal Amount
	public String getInterest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return totalInterest.getText();
	}
	public String getPrincipalAmount() {
		return totalPayment.getText();
	}
	
	@FindBy(id = "menu-item-dropdown-2696")
	WebElement menu;
	
	@FindBy(xpath = "//*[contains(@class,'dropdown-menu show')]/li/a")
	List<WebElement> menuData;
	
	
	public void selectDropDown(String value) throws StaleElementReferenceException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0);");
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(menu)));
		menu.click();
		for(WebElement element : menuData) {
			if(element.getText().contains(value)) {
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
				element.click();
				System.out.println(element.getText());
			}
		}

	}
	
	
}
