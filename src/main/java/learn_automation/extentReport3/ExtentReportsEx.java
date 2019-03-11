package learn_automation.extentReport3;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsEx {
	WebDriver driver;
	private static String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeMethod
	public void setUp(){
		String reportName = "./Reports/extent_"+System.currentTimeMillis()+".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportName);
 
		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
 

	}
	
	@Test
	public void verifyHomePageTitle() throws IOException
	{
				// creates a toggle for the given test, adds all log events under it    
		test = extent.createTest("verifyHomePageTitle", "Checking the Title");
		
		System.setProperty(CHROME_DRIVER_PROPERTY, "C:\\SeleniumBrowserDrivers\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
 
		// log(Status, details)
		test.log(Status.INFO, "Chrome Browser Launched Successfully");
 
		driver.get("http://total-qa.com");
		test.log(Status.INFO,"Navigated to URL");
 
		String actual = driver.getTitle();
		test.log(Status.INFO, "Actual Title returned :: " + actual);
 
		String expected = "Total-QA - Future of Software Testing";
		test.log(Status.INFO, "Expected Title returned:: "+ expected);
 
		Assert.assertEquals(actual,expected);
		
		
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.SUCCESS){
			String sspath = "./Screenshots/SS_"+System.currentTimeMillis()+".png";
			// log with snapshot
			test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(sspath).build());
			 
			// test with snapshot
			test.addScreenCaptureFromPath("screenshot.png");
	 
			// calling flush writes everything to the log file
			extent.flush();
			
		}
		driver.close();
	}
	
}
