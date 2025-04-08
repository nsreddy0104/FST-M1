package activities;

import java.net.URL;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class Activity1 {
	
	AppiumDriver driver;
	
	@BeforeClass
	public void andriodSetup() throws MalformedURLException, URISyntaxException {
		//set desired capabilities
		UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName("andriod")//Mandatory
				.setAutomationName("UiAutomator2")//Mandatory
				.setAppPackage("com.miui.calculator")//to set a package, its always goes with setAppActivity()
				.setAppActivity(".cal.CalculatorActivity")//to set a class, its always goes with setAppPackage()
				.noReset(); //it will not reset the appium
				
		//set appium url
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
				
		
		//Initialize driver
		driver = new AndroidDriver(serverURL, options);
		
	}
	
	@Test
	public void variousLocators() {
		//using accesibilty id,
		driver.findElement(AppiumBy.accessibilityId("clear")).click();
		
		driver.findElement(AppiumBy.id("com.miui.calculator:id/btn_8_s")).click();
		
		driver.findElement(AppiumBy.accessibilityId("multiply")).click();
		
		driver.findElement(AppiumBy.id("com.miui.calculator:id/btn_3_s")).click();
		
		driver.findElement(AppiumBy.accessibilityId("equals")).click();
		
		String res = driver.findElement(AppiumBy.xpath("//android.widget.ViewText[@resource-id='result")).getText();
		assertEquals(res, "= 24");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
