package SeleniumJava.tests;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import SeleniumJava.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";

		WebDriverManager.chromedriver().setup(); // It will directly download the Chrome driver with current browser version (System set property)
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		//LandingPage landingPage=new LandingPage(driver);
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("poojakotian60@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pooja@7859");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		
		
		//findFirst() is used to find or get the first matched element in the list or else get the null
		
		WebElement prod =products.stream().filter(product ->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElseGet(null); // css selector b-> finds the b tag inisde product driver scope
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // click on  add to cart 
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // validating whether "added to cart sucessfully" message appears or not
		
		// we have to check whether that loading icon is disappeared or not
		
     	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); //  we can ask the developers for the id if it is difficult to find
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // reduces the performance issue
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".cartSection h3"))));
		List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)); // anyMatch returns true if finds matching element 
		System.out.println(match);
		
	    Assert.assertTrue(match); // fail if match = false
	    
	    
	    // if match found click on check out button
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    
	    
	    // we have to select the country : before that provide inida text
	    
	    Actions a = new Actions(driver); // no need to use the actions class-->practice purpose
	    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    
	    // now select the "india" from the dropdown options css-->.ta-item:nth-of-type(2) 
	    
	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	    
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    
	    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue( confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    driver.close();
	    
	    
	}

}
