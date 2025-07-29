package UserDefinedLibraries;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/**
 * ScreenShot class provides utility methods to capture and save screenshots during test execution.
 * Screenshots are saved with a timestamp in a designated directory for easy reference and reporting.
 * 
 * Features:
 * - Captures screenshots using Selenium WebDriver
 * - Saves screenshots with unique filenames based on date and time
 * - Returns the file path of the saved screenshot for use in reports
 * 
 * Dependencies:
 * - Selenium WebDriver
 * - Java IO and Date libraries
 */

public class ScreenShot {

	public static String filepath = "./TCscreenshots/";
	public static String screenShotTC(WebDriver scdriver,String fileName)throws IOException{
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();
		File src=((TakesScreenshot)scdriver).getScreenshotAs(OutputType.FILE);       
		String destination = filepath + fileName + "_" + dateFormat.format(date) + ".png";
		//String reporting = "./screenshots/"
		File dest = new File(destination);
		try { 
			// now copy the screenshot to desired location using copyFile method
			FileHandler.copy(src, dest);
			return destination;

		} catch (IOException e) {
			throw new RuntimeException("Screenshot capture failed: " + e.getMessage());
		}
	}

}
