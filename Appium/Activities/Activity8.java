package activities;


import static org.testng.Assert.assertTrue;
import static activities.ActionsBase.doSwipe;
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
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6{
	
AppiumDriver driver;
WebDriverWait wait;
	
	@BeforeClass
	public void andriodSetup() throws MalformedURLException, URISyntaxException {
		//set desired capabilities
		UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName("andriod")//Mandatory
				.setAutomationName("UiAutomator2")//Mandatory
				.setAppPackage("com.android.chrome")//to set a package, its always goes with setAppActivity()
				.setAppActivity("com.google.android.apps.chrome.Main")//to set a class, its always goes with setAppPackage()
				.noReset(); //it will not reset the appium
				
		//set appium url
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
				
		
		//Initialize driver
		driver = new AndroidDriver(serverURL, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//to open url
		driver.get("https://training-support.net/webelements/sliders/");
		
}
	
	@Test(priority = 1)
	public void volume75Test() {

		// Wait for page to load

		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));

		// Get the size of the screen

		Dimension dims = driver.manage().window().getSize();

		// Set the start and end points

		Point start = new Point((int) (dims.getWidth() * .51), (int) (dims.getHeight() * .77));
		Point end = new Point((int) (dims.getWidth() * .67), (int) (dims.getHeight() * .77));

		// Perform swipe

		doSwipe(driver, start, end, 2000);

		// Get the volume level

		String volumeText = driver

				.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]"))

				.getText();

		// Assertions

		assertTrue(volumeText.contains("75%"));

	}
	@Test(priority = 2)
	
	public void volume25Test() {
	
		// Wait for page to load
	
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));
	
		// Get the size of the screen
	
		Dimension dims = driver.manage().window().getSize();
	
		// Set the start and end points
	
		Point start = new Point((int) (dims.getWidth() * .50), (int) (dims.getHeight() * .77));
	
		Point end = new Point((int) (dims.getWidth() * .33), (int) (dims.getHeight() * .77));
	
		// Perform swipe
	
		doSwipe(driver, start, end, 2000);
	
 
	
		// Get the volume level
	
		String volumeText = driver
	
			.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]"))
	
			.getText();
	
 
	
		// Assertions
	
		assertTrue(volumeText.contains("25%"));
	
	}
	
 
	
	@AfterClass
	
	public void tearDown() {
	
		// Close the browser
	
		driver.quit();
	
	}

}
