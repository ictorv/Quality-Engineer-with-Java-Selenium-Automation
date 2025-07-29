package roughPackage;
import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

public class EMICalculator {

	public void moveSlider(WebDriver driver, WebElement inputElement, WebElement slider, WebElement sliderBar, int min, int max, int target) {

			inputElement.sendKeys(Keys.CONTROL + "a");

			inputElement.sendKeys(String.valueOf(min));

			slider.sendKeys(Keys.ENTER);

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

			slider.sendKeys(Keys.ENTER);

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
			driver.findElement(By.id("interest-rate-calc")).click();
			
			EMICalculator obj = new EMICalculator();
			WebElement loanamt = driver.findElement(By.id("loanamount"));
			WebElement loanamtSliderBar = driver.findElement(By.id("loanamountslider"));
			WebElement loanamtSlider = driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]/span"));
			obj.moveSlider(driver,loanamt,loanamtSlider,loanamtSliderBar,100000,20000000,2600000);
			
			WebElement emi = driver.findElement(By.id("loanemi"));
			WebElement emiSliderBar = driver.findElement(By.id("loanemislider"));
			WebElement emiSlider = driver.findElement(By.xpath("//*[@id=\"loanemislider\"]/span"));
			obj.moveSlider(driver,emi,emiSlider,emiSliderBar,21617.29,100000.00,27498.66);
			
			WebElement loanTenure = driver.findElement(By.id("loanterm"));
			WebElement ltSliderBar = driver.findElement(By.id("loanemislider"));
			WebElement ltSlider = driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));
			obj.moveSlider(driver,loanTenure,ltSlider,ltSliderBar,0.1,30,10);
			
			WebElement feesandcharges = driver.findElement(By.id("loanfees"));
			WebElement fcSliderBar = driver.findElement(By.id("loanfeesslider"));
			WebElement fcSlider = driver.findElement(By.xpath("//*[@id=\"loanfeesslider\"]/span"));
			obj.moveSlider(driver,feesandcharges,fcSlider,fcSliderBar,0,100000,50000);
		}
	 
	}

/*
WebElement emi = driver.findElement(By.id("loanemi"));

WebElement emiSliderBar = driver.findElement(By.id("loanemislider"));

WebElement emiSlider = driver.findElement(By.xpath("//*[@id=\"loanemislider\"]/span"));

obj.moveSlider(driver,emi,emiSlider,emiSliderBar,0.1,100000.01,50499.99);


WebElement loan = driver.findElement(By.id("loaninterest"));

WebElement loanSliderBar = driver.findElement(By.id("loaninterestslider"));

WebElement loanSlider = driver.findElement(By.xpath("//*[@id=\"loaninterestslider\"]/span"));

obj.moveSlider(driver,loan,loanSlider,loanSliderBar,0,20,10);


WebElement loanTenure = driver.findElement(By.id("loanterm"));

WebElement ltSliderBar = driver.findElement(By.id("loantermslider"));

WebElement ltSlider = driver.findElement(By.xpath("//*[@id=\"loantermslider\"]/span"));

obj.moveSlider(driver,loanTenure,ltSlider,ltSliderBar,0.1,30,10);*/
