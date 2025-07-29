package testScenario;

import org.openqa.selenium.WebDriver;

import testObjectRepositary.Flipkart_AppliancesPage;
import testObjectRepositary.Flipkart_CartPage;
import testObjectRepositary.Flipkart_HomePage;
import testObjectRepositary.Flipkart_ProductDetailsPage;
import userDefinedLibraries.DriverSetup;
import userDefinedLibraries.ExcelUtility;
import userDefinedLibraries.FlipkartValidation;

public class FlipKart {

    public WebDriver driver;
    public static String browser = "chrome";
    static DriverSetup objDriver;
    public static String url = "https://www.flipkart.com/";

    public WebDriver driverConfig(String browser) {
        objDriver = new DriverSetup();
        driver = objDriver.driverInstantiate(browser,url);
        System.out.println("Opened the URL in the browser");
        return driver;
    }

    public static void main(String[] args) {

        FlipKart objTestMainFK = new FlipKart();
        WebDriver driver = objTestMainFK.driverConfig(browser);

        String xlfile = System.getProperty("user.dir") + "//src/test/resources//testData//filpkartTestData.xlsx";
        String xlsheet = "Sheet1";
        int testRow = 1;

        try {
            String searchKeyword = ExcelUtility.getCellData(xlfile, xlsheet, testRow, 1);

            Flipkart_HomePage objHomePage = new Flipkart_HomePage(driver);
               
            objHomePage.setAndClickSearchBox(searchKeyword);
            
            Flipkart_AppliancesPage objAppliancesPage = new Flipkart_AppliancesPage(driver);
            objAppliancesPage.click_First_Product();
            
            Flipkart_ProductDetailsPage objProductDetailsPage = new Flipkart_ProductDetailsPage(driver);
            objProductDetailsPage.scrollTo_addToCartBtn();
            objProductDetailsPage.click_addToCartbtn();


            Flipkart_CartPage objCartPage = new Flipkart_CartPage(driver);
            String Item1_total = objCartPage.getTotalAmount();
            System.out.println("Total for 1 item: " + Item1_total);
            

            objDriver.closeCurrentPage();

            objAppliancesPage.click_Next_Product();
            
            objProductDetailsPage.scrollTo_addToCartBtn();
            objProductDetailsPage.click_addToCartbtn();
            

            String Actualprice = objCartPage.getPrice();
            String discount = objCartPage.getDiscount();
            String coupon = objCartPage.getCoupons();
            String DeliveryCharge = objCartPage.getDeliveryCharges();
            String protectFee = objCartPage.getProtectFee();
            String Item2_total = objCartPage.getTotalAmount();
            System.out.println("Total for 2 items: " + Item2_total);
            String handlingFee = objCartPage.getHandlingCharges();
            String buy_save_More_String=objCartPage.getBuyMoreSaveMore();

            FlipkartValidation fValidation = new FlipkartValidation();
            int Total_1_Item=fValidation.convertStringToInt(Item1_total);
            int calculatedTotal = fValidation.validateTotal(Actualprice, discount, coupon, DeliveryCharge, protectFee, handlingFee,buy_save_More_String);
            System.out.println("Expected Total: " + calculatedTotal);
            int Total_2_Items = fValidation.convertStringToInt(Item2_total);
            

            ExcelUtility.setCellData(xlfile, xlsheet, testRow, 1,  Total_1_Item+ "");
            ExcelUtility.setCellData(xlfile, xlsheet, testRow, 2, Total_2_Items + "");
            ExcelUtility.setCellData(xlfile, xlsheet, testRow, 3, calculatedTotal + "");
            

            if (calculatedTotal == Total_2_Items) {
                ExcelUtility.setCellData(xlfile, xlsheet, testRow, 4, "PASS");
                System.out.println("[PASS] Total calculated successfully...");
            } else {
                ExcelUtility.setCellData(xlfile, xlsheet, testRow, 4, "FAIL");
                System.out.println("[FAIL] Total calculation mismatch...");
            }

        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
        } finally {
            //objDriver.driverTearDown();
        }
    }
}



