package com.test.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class Screenshot {
	
	// Directory path where screenshots will be saved
    public static String path = "./TestingScreenShots/";

    // Static method to capture a screenshot and return the file path
    public static String ScreenShot(WebDriver driver, String fileName) throws IOException {

        // Format to append date and time to the file name for uniqueness
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy_hh-mm-ss");
        Date date = new Date();

        // Construct the full file path for the screenshot
        String dest = path + fileName + "_" + dateFormat.format(date) + ".png";

        // Capture the screenshot from the WebDriver instance
        File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Copy the screenshot to the destination path
            FileHandler.copy(Screenshot, new File(dest));
        } catch (IOException e) {
            // Print error message if file operation fails
            System.out.println(e.getMessage());
        }

        // Return the path to the saved screenshot
        return dest;
    }
}
