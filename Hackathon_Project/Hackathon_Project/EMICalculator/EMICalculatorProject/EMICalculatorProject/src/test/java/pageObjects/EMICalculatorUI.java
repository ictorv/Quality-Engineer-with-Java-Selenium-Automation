package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EMICalculatorUI extends BasePage{
	
	
	public EMICalculatorUI(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(id = "loanamount")
	WebElement loanAmountInput;
	@FindBy(id = "loanamountslider")
	WebElement laSliderBar;
	@FindBy(xpath = "//*[@id='loanamountslider']/span")
	WebElement laSlider;
	
	@FindBy(id = "loaninterest")
	WebElement interestrate;
	@FindBy(id = "loaninterestslider")
	WebElement irSliderBar;
	@FindBy(xpath = "//div[@id='loaninterestslider']/span")
	WebElement Slider;
	
	@FindBy(id = "loanterm")
	WebElement loanTenure;
	@FindBy(id = "loantermslider")
	WebElement ltSliderBar;
	@FindBy(xpath = "//*[@id='loantermslider']/span")
	WebElement ltSlider;
	
	@FindBy(id = "loanfees")
	WebElement charges;
	@FindBy(id = "loanfeesslider")
	WebElement fcSliderBar;
	@FindBy(xpath = "//*[@id='loanfeesslider']/span")
	WebElement fcSlider;
	
	@FindBy(xpath = "//*[@id='emi-calc']/a[1]")
	WebElement emiClick;
	public void firstClick() {
		emiClick.click();
	}
	
	
	public void setLoanAmtSlider(int target) {
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0.000001;
		fMax = 20000000;
		moveSlider(loanAmountInput, laSlider, laSliderBar, fMin, fMax, target);
	}
	
	public void setInterestRateSlider(double target) {
		
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0;
		fMax = 20;
		moveSlider(interestrate,Slider,irSliderBar, fMin, fMax, target);
	}
	
	public void setLoanTenureSlider(int target) {
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		fMin = 0.1;
		fMax = 30;
		moveSlider(loanTenure,ltSlider,ltSliderBar, fMin, fMax, target);
	}
	
	public void setLoanFeesSlider(int target) {
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		iMin = 0;
		iMax = 100000;
		moveSlider(charges,fcSlider,fcSliderBar, fMin, fMax, target);
	}
	
	@FindBy(xpath = "//*[@id='loan-amount-calc']/a")
	WebElement loanClick;
	public void clickNext() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0);");
		loanClick.click();
	}
}
