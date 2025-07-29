package utilityFiles;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	public static void waitForVisibility(WebDriver driver, List<WebElement> searchList, int seconds) throws TimeoutException {

        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfAllElements(searchList));

    }
 
    public static void waitForClickability(WebDriver driver, WebElement element, int seconds) throws TimeoutException {

        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(element));

    }
    public static void waitForVisibility(WebDriver driver, WebElement element, int seconds) throws TimeoutException {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.visibilityOf(element));
    }

}
