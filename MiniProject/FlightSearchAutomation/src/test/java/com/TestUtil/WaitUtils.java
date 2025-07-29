package com.TestUtil;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
//Utility class for handling different types of waits during Selenium test execution
public class WaitUtils {
	
	// Waits for a specific WebElement to become visible on the page
    public static void waitForVisibility(WebDriver driver, WebElement element, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not visible after " + seconds + " seconds");
        }
    }
 // Waits for a WebElement to become clickable
    public static void waitForClickability(WebDriver driver, WebElement element, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable after " + seconds + " seconds");
        }
    }
 // Introduces a fixed wait for a given number of seconds
    public static void waitForDuration(WebDriver driver, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(d -> false); // Intentional pause
        } catch (TimeoutException ignored) {
            // No-op: timeout triggers the wait
        }
    }
}
