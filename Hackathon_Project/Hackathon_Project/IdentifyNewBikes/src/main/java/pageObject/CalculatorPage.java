package pageObject;


import java.time.Duration;
import java.util.List; 
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class CalculatorPage {

	public WebDriver driver;
	public Actions act;
	public JavascriptExecutor js;
	public WebDriverWait wait;
	
	public CalculatorPage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver,this);
		act = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		js = (JavascriptExecutor)driver;
	}

	@FindBy(xpath="//*[@id='calculatorHolder1']/div[2]/div[2]/div[6]/div[1]/div[2]/span")
	WebElement emiAmt;

	@FindBy(xpath="//*[@id='slider-down-payment']/a")
	WebElement downPayment;

	@FindBy(xpath="//*[@id='calculatorHolder1']/div[2]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[1]/div")
	WebElement tenures;

	@FindBy(xpath="//*[@id='slider-range-min-two']/a")
	WebElement intrestRate;

	@FindBy(xpath="//*[@id='slider-down-payment']/span[1]/span[2]")
	WebElement minDownPayment;

	@FindBy(xpath="//*[@id='down_payment']")
	WebElement dwnPayment;

	@FindBy(xpath="//*[@id='calculatorHolder1']/div[2]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[1]/div")
	List<WebElement> Tenures;

	@FindBy(xpath="//*[@id='slider-range-min-two']/a")
	WebElement IntrestSlider;

	@FindBy(xpath="//*[@id='interestRate']")
	WebElement IntrestRange;

	@FindBy(xpath="//*[@id='interestRate']")
	WebElement RateText;

	@FindBy(xpath="//*[@id='calculatorHolder1']/div[2]/div[2]/div[6]/div[1]/div[2]/span")
	WebElement MonthlyEmi;
 
	public void setDownPayment(String payment) {

		js.executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", downPayment);
		wait.until(ExpectedConditions.elementToBeClickable(downPayment));
		String minDownPay = minDownPayment.getAttribute("data-value");

		while(true) {
			if(payment.length()<minDownPay.length()) {
				System.out.println("downpayment is Out of range.");
				System.out.println(payment + " " + minDownPay);
				return;
			}

			act.moveToElement(downPayment).clickAndHold(downPayment).moveByOffset(7, 0).release().perform();
			wait.until(ExpectedConditions.elementToBeClickable(dwnPayment));
			System.out.println("Setting the down payment amount to " + payment+ " Closest value:"
					+ dwnPayment.getAttribute("value"));
			String comp = dwnPayment.getAttribute("value");
			if (comp.contains(payment.substring(0, 2))) {
				System.out.println("breaking loop");
				break;
			}
		}
	}
	
	// Setting Tenure for the loan
	public void setTenure(String tenure) {
		System.out.println("Setting Tenure of the loan:");
		for (WebElement i : Tenures) {
			js.executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", i);
			wait.until(ExpectedConditions.elementToBeClickable(i));
			if (i.getText().equals(tenure)) {
				System.out.println(i.getText());
				js.executeScript("arguments[0].click()", i);
				break;
			}
		}
	}


	public void setIntrestRate(String intrest) {
		double minrate = Double.parseDouble((RateText.getAttribute("data-min")));
		double maxrate = Double.parseDouble((RateText.getAttribute("data-max")));
		System.out.println("(Max:Min) Rate: "+minrate+":"+maxrate);
		System.out.println("Sliding the Silder to match the Intrest Rate");
		while(true) {
			if(minrate<=Double.parseDouble(intrest) && maxrate>=Double.parseDouble(intrest)) {
				String r = (String)js.executeScript("return document.getElementById('interestRate').value;");
				System.out.println("=>"+r);
				if(r.equals(intrest)) {
					System.out.println("Rate Set");
					break;
				}

				if(Double.parseDouble(intrest)>=Double.parseDouble(r)) {
					System.out.println("Slider + "+intrest.compareTo(r));
					act.moveToElement(IntrestSlider).clickAndHold(IntrestSlider).moveByOffset(10, 0).release().perform();
				}else {
					System.out.println("Slider - "+intrest.compareTo(r));
					act.moveToElement(IntrestSlider).clickAndHold(IntrestSlider).moveByOffset(-10, 0).release().perform();
				}
				wait.until(ExpectedConditions.elementToBeClickable(IntrestSlider));
			}
		}
	}
	
	public String getMonthlyEmi() {
		String emi=MonthlyEmi.getText();
		return emi;
	}
}
 
