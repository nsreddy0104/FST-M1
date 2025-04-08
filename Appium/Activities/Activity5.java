package activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	@BeforeClass()
	public void setUp() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options()
				.setAutomationName("UiAutomator2")
				.setPlatformName("android")
				.setAppPackage("com.google.android.contacts")//to set a package, its always goes with setAppActivity()
				.setAppActivity("com.android.contacts.activities.PeopleActivity")
				.noReset();
		
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		
		//Initialize driver
		driver = new AndroidDriver(serverURL, options);
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
				
	}
	
	@Test
	public void createContact() {
		
		driver.findElement(AppiumBy.accessibilityId("Create contact")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@text=\"First name\"]")));
		//firstname
		WebElement firstName = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"First name\"]"));
		WebElement lastName = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Last name\"]"));
		WebElement PhoneNum = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"+91\"]"));
		
		firstName.sendKeys("Test");
		lastName.sendKeys("Appium");
		PhoneNum.sendKeys("9999900000");
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Save\"]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.contacts:id/large_title")));
		String name = driver.findElement(AppiumBy.id("com.google.android.contacts:id/large_title")).getText();
		assertEquals(name, "Test Appium");
	}
	
	@AfterClass
	public void closure() {
		
		driver.quit();
		
	}
}
