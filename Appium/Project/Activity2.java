package projects;

import static org.testng.Assert.assertEquals;
import static activities.ActionsBase.longPress;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity2 {
	AppiumDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options()
				.setPlatformName("android")
				.setAutomationName("UiAutomator2")
				.setAppPackage("com.app.todolist")
				.setAppActivity(".view.MainActivity")
				.noReset();
		
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		
		driver = new AndroidDriver(serverURL,options);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	
	
	@Test
	public void editTask() {
		
		WebElement firstTask = driver.findElement(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.app.todolist:id/rl_exlv_task_group_root\"])[1]"));
		/*
		//using JS executor for longpress
		((JavascriptExecutor)driver).executeScript("mobile:longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)firstTask).getId()
				,"duration",2000));
				*/
		//using point
		Dimension dims = driver.manage().window().getSize();
		Point start = new Point((int) (dims.getWidth() * .70), (int) (dims.getHeight() * .15));
		longPress(driver, start);
		
		//clicking to do
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Edit To-Do Task\"]")).click();
		//deadline ele
		driver.findElement(AppiumBy.id("com.app.todolist:id/tv_todo_list_deadline")).click();
		//selecting date
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("29 March 2025"))).click();
		//clicking ok
		driver.findElement(AppiumBy.id("com.app.todolist:id/bt_deadline_ok")).click();
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(AppiumBy.id("com.app.todolist:id/dialog_edit"), "Edit To-Do task"));
		//clicking final Ok
		driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
		//wait.until(ExpectedConditions.visibilityOf(firstTask));
		String deadLineDate = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Deadline: 29.03.2025\"]")).getText();
		assertEquals(deadLineDate, "Deadline: 29.03.2025");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
