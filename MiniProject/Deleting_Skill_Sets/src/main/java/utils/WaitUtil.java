/**
 * Utility Class: WaitUtil.java
 * 
 * Purpose:
 * - Provides utility functions to handle explicit waits in Selenium WebDriver.
 * - Ensures elements are fully visible before interaction, preventing timing issues.
 * - Enhances test stability by waiting for both single and multiple elements dynamically.
 *
 * Methods:
 * - waitForOneElement(WebDriver driver, WebElement element): Waits for a single element to be visible.
 * - waitForMultipleElement(WebDriver driver, List<WebElement> elements): Waits for multiple elements to be visible.
 */


package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	public static void waitForOneElement(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForMultipleElement(WebDriver driver,List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
}
