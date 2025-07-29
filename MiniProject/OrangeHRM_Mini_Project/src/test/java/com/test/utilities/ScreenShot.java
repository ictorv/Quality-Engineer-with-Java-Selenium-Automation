package com.test.utilities;
/**
 * Utility class for capturing browser screenshots during test execution.
 * Saves timestamped PNG files in a dedicated /screenshots folder.
 * Helps with debugging, reporting, and visual traceability.
 */


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class ScreenShot {

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);
	        String directoryPath = System.getProperty("user.dir") + "/screenshots";
	        File directory = new File(directoryPath);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String filePath = directoryPath + "/" + screenshotName + "_" + timeStamp + ".png";
	        File dest = new File(filePath);
	        FileUtils.copyFile(src, dest);
	        System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
	        return filePath;
	    } catch (IOException e) {
	        System.err.println("Failed to capture screenshot: " + e.getMessage());
	        return null;
	    }
	}
}
