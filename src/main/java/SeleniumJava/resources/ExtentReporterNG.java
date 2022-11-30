package SeleniumJava.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports//index.html";//one report folder will be created under project user directory
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); // this is reponsible for to create one html file and used for the configuation-->act as helper class to Extent report
		
		reporter.config().setReportName("Web Automation results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports(); // reponsible for drive all the reporting execution
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pooja Kotian");
		
		return extent;
	}


}
