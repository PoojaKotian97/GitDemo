package SeleniumJava.pageobjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SeleniumJava.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver; // this.driver is a local class driver
		PageFactory.initElements(driver, this); // this referes to the current class driver
	}

	
	// Page Factory
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
//	@FindBy(css = ".totalRow button")
//	WebElement checkOut;

	public Boolean VerifyOrderDisplay(String ProductName) {
		Boolean match = productNames.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName)); // anyMatch returns true if finds matching element 																																											
		return match;		
	}
	


}
