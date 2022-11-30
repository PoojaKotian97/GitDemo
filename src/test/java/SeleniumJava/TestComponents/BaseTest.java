package SeleniumJava.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumJava.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver IntializeDriver() throws IOException {

		// Properties class --> we can decide in which browser to run the tests in a
		// global level
		Properties prop = new Properties();

		// System.getProperty("user.dir") =
		// "C:\\Users\\Pooja\\eclipse-workspace\\SeleniumFrameworkDesign" -- >Project
		// path
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//SeleniumJava//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ?  System.getProperty("browser") : prop.getProperty("browser"); //to check whether mvn command is there to run the tests in other browsers

		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
            
			ChromeOptions options = new ChromeOptions(); 
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless"); // over here we are controlling headless mode from the mvn command so we are checking whether mvn command contains headless 
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); // full screen mode

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// edge i.e System.setProperty(); and WebDriver driver = new EdgeDriver();
			//System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");//-->This is now optional in  selenium 4.6.0 version-->(Beta Phase)

			 driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		return driver;

	}

	@BeforeMethod(alwaysRun = true) // because it will scans the methods in the parent class also.
	public LandingPage launchApplication() throws IOException {
		driver = IntializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true) // -->because if grouping is used these methods also be skipped
	public void taerDown() {
		driver.close();
	}

	// Here driver argument is not having life-->we have to get it when we are
	// calling this function
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"; // returns the path where the
																						// screenshot has been stored in
																						// the local system
	}

	// extent report -- >Utility used to generate the html reports

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);// 2nd argument is
																									// encoding format

		// string to HashMap Jakson DataBind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				}); // convert to List of Hash map i.e {map},{map}
		return data;
	}

}
