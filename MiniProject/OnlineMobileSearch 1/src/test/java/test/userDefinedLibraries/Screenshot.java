package test.userDefinedLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshot {

	
	private static File screenshotFolder=new File("./Screenshots");
	
	
	//method to generate screenshot name
	private static String getScreenshotName() {
		
		Date date = new Date();
		return String.valueOf(date).replace(" ","_").replace(":","_");
	}
	
	
	//method to generate screenshot
	public static String getScreenshot(WebDriver driver, String name) {
        if (!screenshotFolder.exists())
            screenshotFolder.mkdir();
        String fileName = name + getScreenshotName() + ".png";
        File destination = new File(screenshotFolder.getAbsolutePath() + "\\" + fileName);
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination.getAbsolutePath();
    }
}
