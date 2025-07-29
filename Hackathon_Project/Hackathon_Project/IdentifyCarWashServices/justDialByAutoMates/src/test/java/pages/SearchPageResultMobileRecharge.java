package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchPageResultMobileRecharge extends BasePage{
	
	public SearchPageResultMobileRecharge(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@id='num_rc_mob']")
	WebElement mobileInput;
	
	@FindBy(xpath="//input[@id='operator_rc_mob']")
	WebElement networkInput;
	
	@FindBy(id="mobile_plan")
	WebElement newPlansButton;
	
	@FindBy(xpath="//input[@id='amt_rc_mob']")
	WebElement rechargeAmount;
	
	@FindBy(linkText="Recommended Packs")
	WebElement recommendedPacksection;
	
	@FindBy(xpath="//li[starts-with(@class, 'planTabs')]/a")
	List<WebElement> plans;
	
	@FindBy(xpath="//li[@class='br_wrap plansli']")
	List<WebElement> planList;
	
	//actions
	public void setMobileNumber(String num) {
		mobileInput.sendKeys(num);
	}
	
	public void clickNewPlansButton() {
		newPlansButton.click();
	}
	
	public void clickRechargeAmount() {
		rechargeAmount.click();
	}
	
	public void setPlan(String plan) {
		for(WebElement x:plans) {
			if(x.getText().equalsIgnoreCase(plan)) {
				x.click();
			}
		}
	}
	
	public List<String> getPlanDetails() {
		int count = 0;
	    List<String> details = new ArrayList<>();
	    for (WebElement list : planList) {
	    	if(count==5) {
	    		break;
	    	}
	        String text = list.getText().trim();
	        if (!text.isEmpty()) {
	        	String temp[] = text.split("\n");
	            details.add(temp[0] + " , " + temp[2]);
	            count++;
	        }
	    }
	    return details;
	}

	public List<Integer> getAmountsFromData() {
		List<String> plans = getPlanDetails();
	    List<Integer> amounts = new ArrayList<>();

	    for (String line : plans) {
	        line = line.trim();
	        if (line.startsWith("Amount:")) {
	            String numberOnly = line.replaceAll("[^0-9]", "");
	            if (!numberOnly.isEmpty()) {
	                amounts.add(Integer.parseInt(numberOnly));
	            }
	        }
	    }
	    
	    return amounts;
	}

	
	
}
