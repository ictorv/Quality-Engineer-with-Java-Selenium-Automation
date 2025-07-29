package com.test.HandleAlertConfirmPrompt;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.test.screenshot.Screenshot;
public class HandleAlertWithOkCancel {
	
	// WebDriver instance to control browser interactions
    public WebDriver driver;

    // Constructor to initialize WebDriver and bind WebElements using PageFactory
    public HandleAlertWithOkCancel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locates the "OK/Cancel" alert button on the page
    @FindBy(xpath = "//div[contains(@class,'col-md-12')]/div/div[2]/div[2]/button[contains(@class,'btn btn')]")
    WebElement alertBoxOkCancel;

    // Locates the sidebar link to open the "Alert with OK & Cancel" section
    @FindBy(xpath = "//div[contains(@class,'tabpane')]/ul/li[2]/a")
    WebElement sideAlertBoxOkCancel;

    // Locates the paragraph element that displays result text after the alert interaction
    @FindBy(xpath = "//div[contains(@id,'CancelTab')]/p[@id='demo']")
    WebElement cancelText;

    // Method to handle alert that has both OK and Cancel options
    public String handleAlertOKAndCancel() {
        String outputText = "";

        try {
            String fileName = "Handling_Confirm";

            // Create a FluentWait to wait for elements or alerts with retry polling
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);

            // Wait for the sidebar option to be visible and click it
            wait.until(ExpectedConditions.visibilityOf(sideAlertBoxOkCancel));
            sideAlertBoxOkCancel.click();
            System.out.println("Clicked on the Link named as \"Alert with OK & Cancel\" in the left menu option.");

            // Wait for the main button to be visible and click it
            wait.until(ExpectedConditions.visibilityOf(alertBoxOkCancel));
            alertBoxOkCancel.click();
            System.out.println("Clicked on the Button named as \"click the button to display a confirm box\".");

            // Wait for the alert to appear and switch to it
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert1 = driver.switchTo().alert();

            System.out.println("Confirm box is present successfully after clicking the button and the text in Confirm box is: " + alert1.getText());

            // Dismiss the alert (click "Cancel")
            alert1.dismiss();

            // Capture the result text displayed on the page
            outputText = cancelText.getText();
            System.out.println("The text present after clicking the Confirm Box is: '" + outputText + "'");

            // Capture screenshot after performing the action
            Screenshot.ScreenShot(driver, fileName);

        } catch (IOException e) {
            // Handle file-related exceptions gracefully
            System.out.println(e.getMessage());

        } catch (NoAlertPresentException e) {
            // Handle case where expected alert did not appear
            System.out.println("No alert appeared!");
        } finally {
            // Visual separator for console logs
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println();
        }

        // Return the result text displayed on the webpage
        return outputText;
    }

}
