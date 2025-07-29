package test.Objects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangeHRMAction1 {
	WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    public OrangeHRMAction1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Form Fields
    @FindBy(name = "FullName")
    WebElement fullName;

    @FindBy(name = "Contact")
    WebElement contact;

    @FindBy(name = "JobTitle")
    WebElement jobTitle;

    @FindBy(name = "Country")
    WebElement country;

    @FindBy(name = "NoOfEmployees")
    WebElement noOfEmployees;

    @FindBy(name ="Email")
    WebElement email;

    @FindBy(name = "Comment")
    WebElement comment;

    @FindBy(name = "action_submitForm")
    WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"Nocaptcha-Form_getForm_Captcha\"]/div/div/iframe")
    WebElement captchaFrame;

    @FindBy(xpath = "//span[@id='recaptcha-anchor']")
    WebElement captchaCheckbox;

    // Methods
    public void fillForm(String Name, String phoneNumber, String JobTitle, String Country, String noOfEmp, String Email) {
        fullName.sendKeys(Name);
        contact.sendKeys(phoneNumber);
        jobTitle.sendKeys(JobTitle);
        country.sendKeys(Country);
        noOfEmployees.sendKeys(noOfEmp);
        email.sendKeys(Email);
    }

    public void msg(String msg) {
        comment.sendKeys(msg);
    }

    public void submitForm() {
        js.executeScript("arguments[0].scrollIntoView(false);", submitButton);
        js.executeScript("arguments[0].click();", submitButton);
    }

    public void handleCaptcha() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(captchaFrame));
        wait.until(ExpectedConditions.elementToBeClickable(captchaCheckbox));
        new Actions(driver).moveToElement(captchaCheckbox).click().perform();
        driver.switchTo().defaultContent();
    }

}