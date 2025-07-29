package roughPackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TrailClass {
	
public void moveSlider(WebDriver driver, WebElement inputElement, WebElement slider, WebElement sliderBar, int min, int max, int target) {
		
		inputElement.sendKeys(Keys.CONTROL + "a");
		inputElement.sendKeys(String.valueOf(min));
		slider.click();
		
		int sliderWidth = sliderBar.getSize().width;
		int xOff = (target - min) * sliderWidth /(max - min);
		
		Actions move = new Actions(driver);
		move.clickAndHold(slider)
			.moveByOffset(xOff, 0)
			.release().perform();
	}
	
	
	public void moveSlider(WebDriver driver, WebElement inputElement, WebElement slider, WebElement sliderBar, double min, double max, double target) {
		
		inputElement.sendKeys(Keys.CONTROL + "a");
		inputElement.sendKeys(String.valueOf(min));
		slider.click();
		int sliderWidth = sliderBar.getSize().width;
		
		double percent = (target - min)/ (max - min);
		int xOff = (int)Math.round(percent * sliderWidth);
	
		Actions move = new Actions(driver);
		move.clickAndHold(slider)
			.moveByOffset(xOff, 0)
			.release().perform();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://emicalculator.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id=\"menu-item-dropdown-2696\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"menu-item-2423\"]/a")).click();
		EMICalculator obj = new EMICalculator();
		WebElement loanAmountInput = driver.findElement(By.id("loanamount"));
		WebElement laSliderBar = driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]"));
		WebElement laSlider = driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]/span"));
		obj.moveSlider(driver,loanAmountInput,laSlider,laSliderBar,0.000001,20000000.0,25);
		WebElement interestrate = driver.findElement(By.id("loaninterest"));
		WebElement irSliderBar = driver.findElement(By.id("loaninterestslider"));
		WebElement Slider = driver.findElement(By.xpath("//div[@id='loaninterestslider']/span"));
		obj.moveSlider(driver,interestrate,Slider,irSliderBar,0,20,7.5);
		WebElement LoanTenure = driver.findElement(By.id("loanterm"));
		WebElement ltSliderBar = driver.findElement(By.id("loantermslider"));
		WebElement ltSlider = driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
		obj.moveSlider(driver,LoanTenure,ltSlider,ltSliderBar,0.1,30,15);
		WebElement FeesandCharges = driver.findElement(By.id("loanfees"));
		WebElement fcSliderBar = driver.findElement(By.id("loanfeesslider"));
		WebElement fcSlider = driver.findElement(By.xpath("//*[@id=\"loanfeesslider\"]/span"));
		obj.moveSlider(driver,FeesandCharges,fcSlider,fcSliderBar,0,100000,25000);
		driver.quit();


 
		


 
	}
		
		
}
	
