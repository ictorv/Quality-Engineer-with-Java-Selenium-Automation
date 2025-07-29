package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TouristPlacesPage extends BasePage {

	public TouristPlacesPage(WebDriver driver) {
		super(driver);
	}

	// locators
//	@FindBy(xpath = "//i[@class='closeicon mr-5']")
//	WebElement closeButtonForLocation;
	
	@FindBy(xpath = "//div[@class='locationbutton']/button[2]")
	WebElement closeButtonForLocation;

	@FindBy(xpath = "//a[@class='redirect__a color111']//div[@class='csheading']")
	List<WebElement> hoteNameslList;

	@FindBy(xpath = "//a[@class='redirect__a color111']//div[@class='csheading']/following-sibling::div[contains(@class,'ratingcount')]")
	List<WebElement> hotelRatingList;

	@FindBy(xpath = "//div[@id='section-8']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']")
	List<WebElement> restaurantNamesList;

	@FindBy(xpath = "//div[@id='section-8']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']/following-sibling::div[contains(@class,'ratingcount')]")
	List<WebElement> restaurantRatingList;

	@FindBy(xpath = "//div[@id='section-9']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']")
	List<WebElement> coffeeShopNamesList;

	@FindBy(xpath = "//div[@id='section-9']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']/following-sibling::div[contains(@class,'ratingcount')]")
	List<WebElement> coffeeShopRatingList;

	@FindBy(xpath = "//div[@id='section-10']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']")
	List<WebElement> travelAgentsList;

	@FindBy(xpath = "//div[@id='section-10']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']/following-sibling::div[contains(@class,'ratingcount')]")
	List<WebElement> travelAgentsRating;

	@FindBy(xpath = "//div[@id='section-11']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']")
	List<WebElement> thingsToDoList;

	@FindBy(xpath = "//div[@id='section-11']//div[@aria-label='swiper']//a[@class='redirect__a color111']//div[@class='csheading']/following-sibling::div[contains(@class,'ratingcount')]")
	List<WebElement> thingsToDoRating;

	// actions
	public void clickCloseButtonForLocation() {
		closeButtonForLocation.click();
	}
	
	public List<String> getHotelDetails(){
		int count = 0;
		List<String> result = new ArrayList<>();
		for(int i = 0;i<hoteNameslList.size();i++) {
			if(count==5) {
				break;
			}
			result.add(hoteNameslList.get(i).getText()+" , "+hotelRatingList.get(i).getText());
			count++;
		}
		return result;
	}
	
	public List<String> getRestaurantDetails(){
		int count = 0;
		List<String> res = new ArrayList<>();
		for(int i = 0;i<restaurantNamesList.size();i++) {
			if(count==5) {
				break;
			}
			res.add(restaurantNamesList.get(i).getText()+" , "+restaurantRatingList.get(i).getText());
			count++;
		}
		return res;
	}
	
	public List<String> getCoffeeShop(){
		int count = 0;
		List<String> res = new ArrayList<>();
		for(int i = 0;i<coffeeShopNamesList.size();i++) {
			if(count==5) {
				break;
			}
			res.add(coffeeShopNamesList.get(i).getText()+" , "+coffeeShopRatingList.get(i).getText());
			count++;
		}
		return res;
	}
	
	public List<String> getTravelAgentDetails(){
		int count = 0;
		List<String> res = new ArrayList<>();
		for(int i = 0;i<travelAgentsList.size();i++) {
			if(count==5) {
				break;
			}
			res.add(travelAgentsList.get(i).getText()+" , "+travelAgentsRating.get(i).getText());
			count++;
		}
		return res;
	}
	
	public List<String> getThingsToDo(){
		int count = 0;
		List<String> res = new ArrayList<>();
		for(int i = 0;i<thingsToDoList.size();i++) {
			if(count==5) {
				break;
			}
			res.add(thingsToDoList.get(i).getText()+" , "+thingsToDoRating.get(i).getText());
			count++;
		}
		return res;
	}
	
}
