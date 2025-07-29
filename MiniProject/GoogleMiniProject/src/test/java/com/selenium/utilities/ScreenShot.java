package com.selenium.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	public static String takeElementScreenshot(WebElement element, String stepName) throws IOException {
		Date currentDate = new Date();
		String strDate = currentDate.toString().replace(" ", "-").replace(":", "-");
	    File screenshot = element.getScreenshotAs(OutputType.FILE);
	    String destination = System.getProperty("user.dir")+"\\ScreenShots\\" + stepName + "_" + strDate + ".png";
	    FileHandler.copy(screenshot, new File(destination));
	    return destination;
	}
	
	public static String takeScreenshot(WebDriver driver, String stepName) throws IOException {
	    Date currentDate = new Date();
	    String strDate = currentDate.toString().replace(" ", "-").replace(":", "-");
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String destination = System.getProperty("user.dir")+"\\ScreenShots\\" + stepName + "_" + strDate + ".png";
	    FileHandler.copy(screenshot, new File(destination));
	    return destination;
	}

}
