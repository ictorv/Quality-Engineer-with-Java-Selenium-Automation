package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='PIM']//parent::a")
    private WebElement pimButton;
    
    @FindBy(xpath = "//a[normalize-space(text())='Add Employee']")
    private WebElement addEmpButton;
    
    @FindBy(xpath = "//a[normalize-space(text())='Employee List']")
    private WebElement empListButton;
    
    @FindBy(xpath = "//div[@id='oxd-toaster_1']/div")
    private WebElement promptDiv;
    
    public void PimButtonWait() {
    	wait.until(ExpectedConditions.visibilityOf(pimButton)).isDisplayed();
    }
    
    public void resultPopup() {
    	wait.until(ExpectedConditions.visibilityOf(promptDiv));
    }
    public void popupDisappear() {
    	wait.until(ExpectedConditions.invisibilityOf(promptDiv));
    }
    
    public void goToAddEmployee() {
    	wait.until(ExpectedConditions.elementToBeClickable(pimButton)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(addEmpButton)).click();
    }
    
    public void goToEmployeeList() {
    	wait.until(ExpectedConditions.elementToBeClickable(pimButton)).click();
    	wait.until(ExpectedConditions.elementToBeClickable(empListButton)).click();
    }
    
}
