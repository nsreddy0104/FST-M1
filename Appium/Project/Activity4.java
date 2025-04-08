package projects;
import static activities.ActionsBase.doSwipe;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumBy.ByAndroidUIAutomator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {
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
		
		driver.get("https://training-support.net/webelements");
	}
	
	@Test
	public void tasks() {
		
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"To-Do List\"));"));
		
		Dimension dims = driver.manage().window().getSize();
		Point start = new Point((int)(dims.getWidth()*.40), (int)(dims.getHeight()*.90));
		Point end = new Point((int)(dims.getWidth()*.50), (int)(dims.getHeight()*.50));
		
		doSwipe(driver,start,end,50);
		
		
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'To-Do List')]")).click();
		
		// Wait for the page to load
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='todo-input']")));

		WebElement input = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]"));
		WebElement addbutton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]"));
		
		input.sendKeys("Add tasks to list");
		addbutton.click();
		input.sendKeys("Get number of tasks");
		addbutton.click();
		input.sendKeys("Clear the list");
		addbutton.click();
		
		List<WebElement> listOfTasks = driver.findElements(AppiumBy.xpath("//android.widget.ListView/android.view.View"));
		for(WebElement task : listOfTasks) {
        	task.findElement(AppiumBy.xpath("//android.view.View/android.widget.CheckBox")).click();
        }
		
		listOfTasks = driver.findElements(AppiumBy.xpath("//android.widget.ListView/android.view.View"));
		assertEquals(listOfTasks.size(), 5);
		
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
