package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotDir = "./screenshots/";
        new File(screenshotDir).mkdirs(); // Ensure directory exists

        String filePath = screenshotDir + testName + "_" + System.currentTimeMillis() + ".png";
        File dest = new File(filePath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(filePath).getAbsolutePath(); 
    }
}
