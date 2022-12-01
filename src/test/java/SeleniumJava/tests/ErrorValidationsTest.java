package SeleniumJava.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumJava.TestComponents.BaseTest;
import SeleniumJava.TestComponents.Retry;
import SeleniumJava.pageobjects.MyCarts;
import SeleniumJava.pageobjects.ProductCatelogue;

public class ErrorValidationsTest extends BaseTest {

	@Test (groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";


		ProductCatelogue productCatalouge = landingPage.loginApplication("poojakotian6@gmail.com", "Pooja@785"); //  productCatalouge object is not needed overhere
		System.out.println(productCatalouge);
		System.out.println("productCatalouge GIT new changes");
		
		
		
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage()); //Incorrect email or password.
	}		
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatelogue productCatalouge = landingPage.loginApplication("poojakotian97@gmail.com", "Pooja@7859");
		List<WebElement> products = productCatalouge.getProductList();
		System.out.println(products);
		productCatalouge.addProductToCart(productName);
		MyCarts myCarts = productCatalouge.goToCartPage();

		// MyCarts myCarts = new MyCarts(driver);
		Boolean match = myCarts.VerifyProductIsDisplaying("ZARA COAT 33");
		Assert.assertFalse(match); // Validation should not go in page object files-->over here we are expectiong false
		
	}
	
}
