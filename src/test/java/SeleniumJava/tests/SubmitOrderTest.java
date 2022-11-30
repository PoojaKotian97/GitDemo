package SeleniumJava.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumJava.TestComponents.BaseTest;
import SeleniumJava.pageobjects.ConfirmationPage;
import SeleniumJava.pageobjects.MyCarts;
import SeleniumJava.pageobjects.OrdersPage;
import SeleniumJava.pageobjects.PaymentPage;
import SeleniumJava.pageobjects.ProductCatelogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	// TODO Auto-generated method stub
	@Test(dataProvider ="getData" ,  groups = {"Purchase"}) // we can add multiple groups also
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatelogue productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));

		// ProductCatelogue productCatalouge = new ProductCatelogue(driver); instead
		// creating objects for each page in the main we can create that in the page
		// files itself:above line-->This is called as encapsulation
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("product"));
		MyCarts myCarts = productCatalouge.goToCartPage();

		// MyCarts myCarts = new MyCarts(driver);
		Boolean match = myCarts.VerifyProductIsDisplaying(input.get("product"));
		Assert.assertTrue(match); // Validation should not go in page object files
		PaymentPage paymentPage = myCarts.performCheckOut();

		// PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.selectCountry("india");
		ConfirmationPage confirmationPage = paymentPage.submitOrder();

		// ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To verify the "ZARA COAT 3" is displaying in the Orders page-->but it is
	// dependent on submitOrder test

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws IOException, InterruptedException {

		ProductCatelogue productCatalouge = landingPage.loginApplication("poojakotian60@gmail.com", "Pooja@7859");
		OrdersPage ordersPage = productCatalouge.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map = new HashMap<String, String>();
//
//		map.put("email", "poojakotian60@gmail.com");
//		map.put("password", "Pooja@7859");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//
//		map1.put("email", "poojakotian97@gmail.com");
//		map1.put("password", "Pooja@7859");
//		map1.put("product", "ADIDAS ORIGINAL"); -->instead of manually creating the  Hasmap we can make use of json file to create Hashmaps

		// return new Object [][] {{map},{"poojakotian97@gmail.com","Pooja@7859","ADIDAS
		// ORIGINAL"}}; // Object is a datatype which accepts any kind of data-->instead of directly providing the data we can use Hashmap
		
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//SeleniumJava//data//PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
