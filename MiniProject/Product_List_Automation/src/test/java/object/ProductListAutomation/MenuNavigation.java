package object.ProductListAutomation;



import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MenuNavigation
{
	private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath = "//div[@class='main-header-container']//ul[@class='hd-menu-main-list-container']/li")
    private List<WebElement> menuItems;

    @FindBy(xpath = "//*[@id='meta-Furniture']//div[contains(@class,'hd-menu-dropdown-container')]//li[contains(@class,'font-heading')]//a")
    private List<WebElement> submenuItems;

    public MenuNavigation(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickSetteesUnderFurniture()
    {
        for (WebElement menuItem : menuItems)
        {
            if (menuItem.getText().contains("Furniture")) 
            {
                actions.moveToElement(menuItem).perform();
                wait.until(ExpectedConditions.visibilityOfAllElements(submenuItems));
                for (WebElement submenuItem : submenuItems) 
                {
                    if (submenuItem.getDomProperty("href").contains("settees-and-benches"))
                    {
    
                        wait.until(ExpectedConditions.elementToBeClickable(submenuItem)).click();
                        
                        return;
                    }
                }
            }
        }
    }
}
