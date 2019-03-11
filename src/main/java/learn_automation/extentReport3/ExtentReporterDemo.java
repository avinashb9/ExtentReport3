package learn_automation.extentReport3;

import java.io.IOException;

import org.openqa.selenium.By;
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
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utility.DriverUtil;


public class ExtentReporterDemo {
	private static String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	ExtentReports extent;
	ExtentTest logger;
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp(){
		String reportName = "./Reports/extent_"+System.currentTimeMillis()+".html";
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(reportName);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest("LoginTest");
	}
	
	@Test
	public void LoginTest(){
		System.setProperty(CHROME_DRIVER_PROPERTY, "C:\\SeleniumBrowserDrivers\\chromedriver_win32\\chromedriver.exe");		
		driver=new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("userName")).sendKeys("avinb");
		driver.findElement(By.name("password")).sendKeys("avinb@test");
		driver.findElement(By.name("login")).click();
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("flight"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			String temp = DriverUtil.getScreenShot();
			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			
		}
		extent.flush();
		driver.quit();
		
		
	}
}
