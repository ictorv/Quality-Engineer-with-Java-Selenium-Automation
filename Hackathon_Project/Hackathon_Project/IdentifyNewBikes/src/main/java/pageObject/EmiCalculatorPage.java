package pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
 
public class EmiCalculatorPage {
	public WebDriver driver;
	public EmiCalculatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);

	}

	@FindBy(xpath="//*[@id='makeId']")
	WebElement makeType;

	@FindBy(xpath="//*[@id='modelId']")
	WebElement modelType;
	
	@FindBy(xpath="//*[@id='variantId']")
	WebElement variantType;

	@FindBy(xpath="//*[@id='mmvSelectForm']/div[6]/input[2]")
	WebElement checkEmi;
	
	public void setMakeType(String make) {
		Select makeBrand = new Select(makeType);
		makeBrand.selectByVisibleText(make);
		System.out.println("Set :"+make);
		
	}

	public void setModelType(String model) {
		Select models  = new Select(modelType);
		models.selectByVisibleText(model);
		System.out.println("Set :"+model);
		
	}

	public void setVariantType(String variant) {
		Select variants =new Select(variantType);
		variants.selectByVisibleText(variant);
		System.out.println("Set :"+variant);
	}

	public void getCalculator() {
		checkEmi.click();
	}

}

 