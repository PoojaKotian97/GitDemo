package SeleniumJava.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumJava.AbstractComponents.AbstractComponent;

public class LandingPage  extends AbstractComponent { // extends keyword is used to perfrom inheritance -- all the methods in the AbstractComponents will be availble for this class (we can try creating the object also)
	WebDriver driver;   

	public LandingPage (WebDriver driver) {
		super(driver); // sending driver object to the parent class i.e AbstractComponent
		this.driver = driver; // this.driver is a local class driver
		PageFactory.initElements(driver, this); // this referes to the current class driver
	}

	// WebElement userEmails = driver.findElement(By.id("userEmail")); instead of
	// this we can use page factory to define the elements as below

	// Page Factory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;
	
	//.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error -->clicked on close icon in the error message
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatelogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatelogue productCatalouge = new ProductCatelogue(driver); // since we konow after clickin on submit it will navigate to product page , so we are creating the object here itself
		return productCatalouge;

	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
