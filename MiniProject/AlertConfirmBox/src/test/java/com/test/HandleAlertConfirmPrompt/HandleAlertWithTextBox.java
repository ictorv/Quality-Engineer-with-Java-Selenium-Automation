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

public class HandleAlertWithTextBox {
	
	// WebDriver instance to interact with the browser
    WebDriver driver;

    // String to hold the prompt message that will be sent to the alert textbox
    String sendPrompt;

    // Constructor initializes driver and sets the input text for the prompt
    public HandleAlertWithTextBox(WebDriver driver, String name) {
        this.driver = driver;
        sendPrompt = name;
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    // WebElement for the prompt alert button
    @FindBy(xpath = "//div[contains(@class,'col-md-12')]/div/div[2]/div[3]/button[contains(@class,'btn btn')]")
    WebElement alertBoxPrompt;

    // WebElement where the text appears after the prompt is accepted
    @FindBy(xpath = "//div[contains(@class,'col-md-12')]/div/div[2]/div[3]/p[@id='demo1']")
    WebElement textAfterOk;

    // WebElement for clicking the "Alert with Textbox" menu option
    @FindBy(xpath = "//div[contains(@class,'tabpane')]/ul/li[3]/a")
    WebElement sideAlertBoxPrompt;

    // Method to handle the alert prompt and return the output text displayed after submission
    public String handlePrompt() {
        String outputText = "";

        try {
            String fileName = "Handling_Prompt";

            // Fluent wait setup: wait up to 10 seconds, polling every 3 seconds
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);

            // Click on the tab that displays prompt alert options
            wait.until(ExpectedConditions.elementToBeClickable(sideAlertBoxPrompt));
            sideAlertBoxPrompt.click();
            System.out.println("Clicked the link: \"Alert with Textbox\".");

            // Click the button that triggers the prompt alert
            wait.until(ExpectedConditions.elementToBeClickable(alertBoxPrompt));
            alertBoxPrompt.click();
            System.out.println("Clicked the button to demonstrate the prompt box.");

            // Wait for the alert to appear and switch focus to it
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            System.out.println("Prompt box appeared. Text: " + alert.getText());

            // Send input to the prompt and accept it
            alert.sendKeys(sendPrompt);
            alert.accept();

            // Capture the result text displayed on the webpage after submission
            outputText = textAfterOk.getText();
            System.out.println("Text after accepting prompt: " + outputText);

            // Take a screenshot and save with the provided filename
            Screenshot.ScreenShot(driver, fileName);

        } catch (IOException e) {
            System.out.println("Screenshot error: " + e.getMessage());
        } catch (NoAlertPresentException e3) {
            System.out.println("No alert appeared!");
        } 
//        catch (Exception e) {
//            System.out.println("Unexpected error: " + e.getMessage());
//        }
        finally {
            System.out.println("--------------------------------------------------------------------------------------\n");
        }

        return outputText;
    }

}
