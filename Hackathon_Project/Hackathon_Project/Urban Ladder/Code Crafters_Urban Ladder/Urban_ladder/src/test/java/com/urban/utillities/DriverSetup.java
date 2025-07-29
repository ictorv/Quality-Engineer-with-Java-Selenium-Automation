// Source code is decompiled from a .class file using FernFlower decompiler.
package com.urban.utillities;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
   private static WebDriver driver;

   public DriverSetup() {
   }

   public static WebDriver initializeBrowser(String browser) {
      if (browser.equalsIgnoreCase("chrome")) {
         driver = new ChromeDriver();
         System.out.println("Chrome Browser Launched");
      } else if (browser.equalsIgnoreCase("edge")) {
         driver = new EdgeDriver();
         System.out.println("Edge Browser Launched");
      } else {
         System.out.println("Invalid browser specified in config.properties. Defaulting to Chrome.");
         driver = new ChromeDriver();
      }

      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();
      driver.get("https://urbanladder.com");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50L));
      return driver;
   }

   public static WebDriver getDriver() {
      return driver;
   }

   public static void quitDriver(WebDriver driver) {
      if (driver != null) {
         ScreenShot.captureScreenshot(driver, "Before_Browser_Close");
         driver.quit();
         System.out.println("Browser Closed");
         driver = null;
      }

   }
}
