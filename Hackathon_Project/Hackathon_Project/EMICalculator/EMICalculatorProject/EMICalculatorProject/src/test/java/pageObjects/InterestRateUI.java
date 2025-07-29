package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class InterestRateUI extends BasePage {
	
	public InterestRateUI(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "loanamount")
	WebElement loanamt;
	@FindBy(id = "loanamountslider")
	WebElement loanamtSliderBar;
	@FindBy(xpath = "//*[@id='loanamountslider']/span")
	WebElement loanamtSlider;
	
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
	
	public void setLoanAmtSlider(int target) {
		//moveSlider(driver, element, slider, sliderBar, min, max, target);
		iMin = 2500;
		iMax = 20000000;
		moveSlider(loanamt,loanamtSlider,loanamtSliderBar, fMin, fMax, target);
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
	
}
