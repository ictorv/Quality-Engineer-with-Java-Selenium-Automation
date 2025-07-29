//// Source code is decompiled from a .class file using FernFlower decompiler.
//package com.urban.pageObject;
//
//import java.time.Duration;
//import java.util.Iterator;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class SocialMediaPage {
//   WebDriver driver;
//   WebDriverWait wait;
//   @FindBy(
//      className = "close-reveal-modal"
//   )
//   WebElement popupClose;
//   @FindBy(
//      id = "footer-social"
//   )
//   WebElement footerSocial;
//
//   public SocialMediaPage(WebDriver driver) {
//      this.driver = driver;
//      PageFactory.initElements(driver, this);
//      this.wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
//   }
//
//   public void openSite() {
//      this.driver.get("https://www.urbanladder.com/");
//   }
//
//   public void closePopupIfPresent() {
//      try {
//         ((WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.popupClose))).click();
//      } catch (Exception var2) {
//      }
//
//   }
//
//   public void scrollToFooter() throws InterruptedException {
//      ((JavascriptExecutor)this.driver).executeScript("arguments[0].scrollIntoView(false);", new Object[]{this.footerSocial});
//      Thread.sleep(1500L);
//   }
//
//   public boolean validateSocialLink(String name, String xpath) throws InterruptedException {
//      String originalWindow = this.driver.getWindowHandle();
//
//      try {
//         WebElement link = (WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
//         link.click();
//         Thread.sleep(2000L);
//         Iterator var6 = this.driver.getWindowHandles().iterator();
//
//         while(true) {
//            String handle;
//            if (var6.hasNext()) {
//               handle = (String)var6.next();
//               if (handle.equals(originalWindow)) {
//                  continue;
//               }
//
//               this.driver.switchTo().window(handle);
//            }
//
//            Thread.sleep(3000L);
//            handle = this.driver.getCurrentUrl().toLowerCase();
//            String title = this.driver.getTitle().toLowerCase();
//            boolean var8 = handle.contains(name) || title.contains(name);
//            return var8;
//         }
//      } catch (Exception var13) {
//      } finally {
//         Iterator var10 = this.driver.getWindowHandles().iterator();
//
//         while(true) {
//            if (!var10.hasNext()) {
//               this.driver.switchTo().window(originalWindow);
//            } else {
//               String win = (String)var10.next();
//               if (!win.equals(originalWindow)) {
//                  this.driver.close();
//               }
//            }
//         }
//      }
//
//      return false;
//   }
//}
