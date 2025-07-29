package pageObjects;


import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	int iMin, iMax, iTarget;
	double fMin, fMax, fTarget;
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void moveSlider(WebElement inputElement, WebElement slider, WebElement sliderBar, int min, int max, int target) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);", inputElement);
		inputElement.sendKeys(Keys.CONTROL + "a");
		inputElement.sendKeys(String.valueOf(min));
		slider.sendKeys(Keys.ENTER);
		
		int sliderWidth = sliderBar.getSize().width;
		int xOff = (target - min) * sliderWidth /(max - min);
		
		js.executeScript("arguments[0].scrollIntoView(false);", slider);
		Actions move = new Actions(driver);
		move.clickAndHold(slider)
			.moveByOffset(xOff, 0)
			.release().perform();
	}
	
	
	public void moveSlider(WebElement inputElement, WebElement slider, WebElement sliderBar, double min, double max, double target) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);", inputElement);
		inputElement.sendKeys(Keys.CONTROL + "a");
		inputElement.sendKeys(String.valueOf(min));
		slider.sendKeys(Keys.ENTER);
		int sliderWidth = sliderBar.getSize().width;
		
		double percent = (target - min)/ (max - min);
		int xOff = (int)Math.round(percent * sliderWidth);
		
		js.executeScript("arguments[0].scrollIntoView(false);", slider);
		Actions move = new Actions(driver);
		move.clickAndHold(slider)
			.moveByOffset(xOff, 0)
			.release().perform();
	}
	
	
}
