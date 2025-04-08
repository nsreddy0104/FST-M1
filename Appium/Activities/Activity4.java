package activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity3 {
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	@BeforeClass()
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options()
				.setAutomationName("UiAutomator2")
				.setPlatformName("android")
				.setAppPackage("com.vivo.calculator")//to set a package, its always goes with setAppActivity()
				.setAppActivity(".Calculator")
				.noReset();
		
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		
		//Initialize driver
		driver = new AndroidDriver(serverURL, options);
		
	}
	
	@BeforeMethod
	public void clearInput() {
		//clears by clicking on AC on app
		driver.findElement(AppiumBy.accessibilityId("Clear")).click();
	}
	
	@Test(priority = 1)
	public void additionTest() {
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_5")).click();
		
		driver.findElement(AppiumBy.accessibilityId("Plus")).click();
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_9")).click();
		
		driver.findElement(AppiumBy.accessibilityId("=")).click();
		
		String res = driver.findElement(AppiumBy.id("com.vivo.calculator:id/formula")).getText();
		System.out.println("The result of addition is "+ res);
		assertEquals(res, "14");
		
	}
	
	@Test(priority = 2)
	public void subTractionTest() {
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_1")).click();
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_0")).click();
		
		driver.findElement(AppiumBy.accessibilityId("Minus")).click();
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_5")).click();
		
		driver.findElement(AppiumBy.accessibilityId("=")).click();
		
		String res = driver.findElement(AppiumBy.id("com.vivo.calculator:id/formula")).getText();
		System.out.println("The result of Subtraction is "+ res);
		assertEquals(res, "5");
		
	}
	
	@Test(priority = 3)
	public void MultiplyTest() {
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_5")).click();
		
		
		driver.findElement(AppiumBy.accessibilityId("Multiply")).click();
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_1")).click();
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_0")).click();
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_0")).click();
		
		driver.findElement(AppiumBy.accessibilityId("=")).click();
		
		String res = driver.findElement(AppiumBy.id("com.vivo.calculator:id/formula")).getText();
		System.out.println("The result of Multiplication is "+ res);
		assertEquals(res, "500");
		
	}
	
	@Test(priority = 4)
	public void DivideTest() {
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_5")).click();
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_0")).click();
		
		driver.findElement(AppiumBy.accessibilityId("Divide")).click();
		
		driver.findElement(AppiumBy.id("com.vivo.calculator:id/digit_2")).click();
		
		driver.findElement(AppiumBy.accessibilityId("=")).click();
		
		String res = driver.findElement(AppiumBy.id("com.vivo.calculator:id/formula")).getText();
		System.out.println("The result of Division is "+ res);
		assertEquals(res, "25");
		
	}
	
	@AfterClass
	public void closure() {
		driver.quit();
	}
		
}
	
