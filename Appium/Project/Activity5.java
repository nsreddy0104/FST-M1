package projects;

import static activities.ActionsBase.doSwipe;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity5 {

	AppiumDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage(null);
		options.setAppActivity(null);
		options.noReset();
		
		URL serverUrl = new URI("http://127.0.0.1:4723").toURL();
		driver = new AndroidDriver(serverUrl, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
	
	@BeforeMethod
	public void loginPage() {
		
		//using UI scrollable
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"To-Do List\"));"));
		driver.get("https://training-support.net/webelements");
		
		Dimension dims = driver.manage().window().getSize();
		Point start = new Point((int)(dims.getWidth()*.40), (int)(dims.getHeight()*.90));
		Point end = new Point((int)(dims.getWidth()*.50), (int)(dims.getHeight()*.50));
		
		doSwipe(driver,start,end,200);
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Form\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Form\"]")));
		
	}
	@Test(priority = 1)
	public void successfulLogin() {
		
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys("admin");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("password");
		
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();

		
		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Welcome Back, Admin!']"))).getText();
		assertEquals(message, "Welcome Back, Admin!");
		
	}
	
	@Test( priority = 2)
	public void unSuccessfulLogin() {
		
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys("admin");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("passw");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();

		
		// Wait for success message to load and get text
		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='subheading']"))).getText();
		assertEquals(message, "Invalid credentials");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
