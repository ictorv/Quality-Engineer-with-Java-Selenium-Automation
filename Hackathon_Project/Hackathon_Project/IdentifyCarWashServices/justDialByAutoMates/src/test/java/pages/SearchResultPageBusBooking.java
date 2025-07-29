package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPageBusBooking extends BasePage {

	public SearchResultPageBusBooking(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(id = "tBusType")
	WebElement busType;

	@FindBy(id = "btype_1")
	WebElement nonAcFilterCheckBox;

	@FindBy(id = "btype_3")
	WebElement sleeperFilterCheckBox;

	@FindBy(xpath = "//tbody[@class = 'bgc']//td[@class = 'rup']/span[2]")
	List<WebElement> busTicketFare;

	@FindBy(xpath = "//tbody[@class = 'bgc']//td[contains(@class, 'space') and contains(@class, 'trvnm')]")
	List<WebElement> busTravelsName;

	@FindBy(xpath = "//tbody[@class = 'bgc']//span[@title = 'Departure Time']")
	List<WebElement> busDepartureTime;
	
	// actions
	public void clickBusType() {
		busType.click();
	}
	
	public WebElement getNonAcFilter() {
		return nonAcFilterCheckBox;
	}
	
	public WebElement getSleeperFilterCheckBox() {
		return sleeperFilterCheckBox;
	}

	public void clickNonAcFilterCheckBox() {
		nonAcFilterCheckBox.click();
	}

	public void clickSleeperFilterCheckBox() {
		sleeperFilterCheckBox.click();
	}

	public List<String> getBusDetails() {
		List<String> results = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < busTicketFare.size(); i++) {
			if (count == 5) {
				break;
			}
			int value = Integer.parseInt(busTicketFare.get(i).getText().split("\n")[0]);
			
			results.add( busTravelsName.get(i).getText() + " , "+ busDepartureTime.get(i).getText() + " , Rs:" + value);
;
			count++;

		}
		return results;
	}

}
