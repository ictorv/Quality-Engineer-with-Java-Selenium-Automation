package utilityFiles;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	 private static WebDriver driver;
	    private static final String EXCEL_PATH = "src/test/resources/testData/testData.xlsx";
	    private static final String SHEET_NAME = "dataSheet";
	    private static String baseUrl;

	    // Load the URL from Excel using ExcelUtil
	    static {
	        try {
	            baseUrl = ExcelUtil.getCellData(EXCEL_PATH, SHEET_NAME, 1, 0); 
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to read URL from Excel file", e);
	        }
	    }

	    public WebDriver initializeDriver(String browser) {
	        
	    	switch (browser.toLowerCase()) {
	            case "chrome" : driver = new ChromeDriver();
	            				break;
	            case "edge" : driver = new EdgeDriver();
	            			  break;
	            case "firefox" : driver = new FirefoxDriver();
	            			     break;
	            default : throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }

	        driver.manage().window().maximize();
	        //driver.manage().deleteAllCookies();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.get(baseUrl);
	        return driver;
	    }

	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
