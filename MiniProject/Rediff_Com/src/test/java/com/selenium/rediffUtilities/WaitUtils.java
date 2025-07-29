package com.selenium.rediffUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils 
{
    public static void waitForDuration(WebDriver driver, int seconds) 
    {
        try 
        {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(d -> false); // Always false, just to wait
        } catch (TimeoutException e) {
        	//e.printStackTrace();
        }
    }
}
