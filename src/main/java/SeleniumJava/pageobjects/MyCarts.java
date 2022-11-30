package SeleniumJava.pageobjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SeleniumJava.AbstractComponents.AbstractComponent;

public class MyCarts extends AbstractComponent {
	WebDriver driver;

	public MyCarts(WebDriver driver) {
		super(driver);
		this.driver = driver; // this.driver is a local class driver
		PageFactory.initElements(driver, this); // this referes to the current class driver
	}

	
	// Page Factory
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartSection;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public Boolean VerifyProductIsDisplaying(String ProductName) {
		Boolean match = cartSection.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName)); // anyMatch returns true if finds matching element 																																											
		return match;		
	}
	
	public PaymentPage performCheckOut() {
		checkOut.click();
		return new PaymentPage(driver);
	}

}
