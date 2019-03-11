package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import learn_automation.extentReport3.ExtentReporterDemo;

public class DriverUtil extends ExtentReporterDemo{
	
public static String getScreenShot(){
	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir")+"/Screenshots/"+"SS_"+System.currentTimeMillis()+".png";
	File dest = new File(path);
	try {
		FileUtils.copyFile(src, dest);
	} catch (IOException e) {
		
		System.out.println("Capture Failed "+e.getMessage());
	}
	return path;	
}	
	
	
}
