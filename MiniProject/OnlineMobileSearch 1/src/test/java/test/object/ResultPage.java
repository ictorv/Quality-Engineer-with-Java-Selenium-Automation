package test.object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.userDefinedLibraries.FetchData;
import test.userDefinedLibraries.Screenshot;

public class ResultPage {
	private  WebDriver driver;
	
	public ResultPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "a-autoid-0-announce") //a-autoid-0-announce
	WebElement filterOption;
	
	@FindBy(xpath ="//*[@class='a-popover-wrapper']/div//li")
	List<WebElement> listOfOption;

	//@FindBy(xpath="//*[@id=\"s-result-sort-select_4\"]")
	@FindBy(xpath="//*[@id=\"s-result-sort-select_4\"]")
	WebElement newArrivals;
	
	//Method to filter the result
	public void filter() {
		filterOption.click();
		//Add ScreenShot
		Screenshot.getScreenshot(driver,"Result_new_arrivals");
	
		try {
			FetchData.writeIntoExcel(String.valueOf(listOfOption.size()), 1, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newArrivals.click();
	}
	
}
