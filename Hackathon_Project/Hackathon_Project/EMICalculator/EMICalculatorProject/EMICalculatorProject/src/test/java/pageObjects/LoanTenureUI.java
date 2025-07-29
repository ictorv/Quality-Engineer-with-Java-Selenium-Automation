package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanTenureUI extends BasePage{
	
	
	public LoanTenureUI(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "loanamount")
	WebElement loanamt;
	@FindBy(id = "loanamountslider")
	WebElement loanamtSliderBar;
	@FindBy(xpath = "//*[@id='loanamountslider']/span")
	WebElement loanamtSlider;
	
	public void setLoanAmtSlider(int target) {
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0.1;
		fMax = 20000000;
		moveSlider(loanamt,loanamtSlider,loanamtSliderBar, fMin, fMax, target);
	}
	
	@FindBy(id = "loanemi")
	WebElement emi;
	@FindBy(id = "loanemislider")
	WebElement emiSliderBar;
	@FindBy(xpath = "//div[@id='loanemislider']/span")
	WebElement emiSlider;
	public void setEMISlider() {
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0.10;
		fMax = 186696.27;
		//moveSlider(emi,emiSlider,emiSliderBar, fMin, fMax, target);
		emi.click();
	}
	
	@FindBy(id = "loaninterest")
	WebElement interestrate;
	@FindBy(id = "loaninterestslider")
	WebElement irSliderBar;
	@FindBy(xpath = "//*[@id='loaninterestslider']/span")
	WebElement irSlider;
	public void setInterestRateSlider(double target) {
		
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0;
		fMax = 20;
		moveSlider(interestrate,irSlider,irSliderBar, fMin, fMax, target);
	} 
	
	@FindBy(xpath = "//*[@id='interest-rate-calc']/a")
	WebElement loanClick;
	public void clickNext() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0);");
		loanClick.click();
	}
	
	
}
