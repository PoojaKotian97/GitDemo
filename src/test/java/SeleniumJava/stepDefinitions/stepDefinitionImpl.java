package SeleniumJava.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumJava.TestComponents.BaseTest;
import SeleniumJava.pageobjects.ConfirmationPage;
import SeleniumJava.pageobjects.LandingPage;
import SeleniumJava.pageobjects.MyCarts;
import SeleniumJava.pageobjects.PaymentPage;
import SeleniumJava.pageobjects.ProductCatelogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatelogue productCatalouge;
	ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication(); // driver instansiation and opeining the url and it returns landing page

	}

	@Given("^Logged in with username (.+) and password (.+)$") // since it has dynamic inputs we have use (.+) -->since
																// we are using regular expression--> SW -->^ EW-->$
	public void logged_in_username_and_password(String userName, String password) {
		productCatalouge = landingPage.loginApplication(userName, password);
	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String ProductName) throws InterruptedException {
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(ProductName);
	}

	@When("^Checkout (.+) and submit the Order$")
	public void checkout_submit_Order(String ProductName) {

		MyCarts myCarts = productCatalouge.goToCartPage();

		// MyCarts myCarts = new MyCarts(driver);
		Boolean match = myCarts.VerifyProductIsDisplaying(ProductName);
		Assert.assertTrue(match); // Validation should not go in page object files
		PaymentPage paymentPage = myCarts.performCheckOut();

		// PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.selectCountry("india");
		confirmationPage = paymentPage.submitOrder();
	}

	@Then("{string} message is dispalyed on ConfirmationPage")
	public void message_dispalyed_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed.$") //we can use {string} or regular expression like this
	public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1, landingPage.getErrorMessage()); // Incorrect email or password.
		driver.close();
	}

}
