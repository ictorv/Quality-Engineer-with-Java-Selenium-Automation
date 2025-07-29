package object.ProductListAutomation;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='desktop-header-login']/div/div[1]/div/a")
    private WebElement loginPopup;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   
    public void navigateToSite(String url) {
        driver.get(url);
    }
    public String validateTitle(String expected) {
        String actual = driver.getTitle();
        System.out.println(expected.equals(actual) ? "Title Matched" : "Title Unmatched");
        return expected.equals(actual) ? "Title Matched" : "Title Unmatched";
    }

    public void handleLoginPopup() {
        loginPopup.click();
        driver.navigate().refresh();
    }
}
