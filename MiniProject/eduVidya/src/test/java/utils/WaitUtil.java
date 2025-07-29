/**
 * File Name: WaitUtil.java
 * Description:
 * This utility class provides methods to apply explicit waits for web elements during test execution.
 * It ensures that elements are visible before interacting with them, improving test stability.

 * Purpose:
 * Helps manage synchronization between the test script and the web page by waiting for elements to load.

 * Methods:
 * - waitForOneElement: Waits until a single element is visible.
 * - waitForMultipleElement: Waits until all elements in the list are visible.
 */

package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	public static void waitForOneElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForMultipleElement(WebDriver driver, List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
}
