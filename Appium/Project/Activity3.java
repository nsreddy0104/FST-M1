package projects;

import static org.testng.Assert.assertEquals;
import static activities.ActionsBase.doSwipe;
import static activities.ActionsBase.longPress;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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

public class Activity3 {
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
	public void completeTasksSetProgress() {
		
		//firsttask
		driver.findElement(By.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])[1]")).click();
		//secondtask
		driver.findElement(By.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])[2]")).click();
		
		
		WebElement thirdTask = driver.findElement(By.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.app.todolist:id/rl_exlv_task_group_root\"])[3]"));
		
		((JavascriptExecutor)driver).executeScript("mobile:longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)thirdTask).getId()
				,"duration",2000));
		/*
		 	//using point longpress
		Dimension dims = driver.manage().window().getSize();
		Point start = new Point((int) (dims.getWidth() * .70), (int) (dims.getHeight() * .15));
		longPress(driver, start);
		 */
		
		//clicking to do
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Edit To-Do Task\"]")).click();
		
		//progress bar
		WebElement progressBar = driver.findElement(AppiumBy.className("android.widget.SeekBar"));
		wait.until(ExpectedConditions.elementToBeClickable(progressBar));
		
		//using point
		Dimension dims = driver.manage().window().getSize();
		Point start = new Point((int) (dims.getWidth() * .29), (int) (dims.getHeight() * .54));
		Point end = new Point((int) (dims.getWidth() * .60), (int) (dims.getHeight() * .54));
		doSwipe(driver, start, end, 2000);
		
		/*
		//using Javascript exceutor
		((JavascriptExecutor)driver).executeScript("mobile:swipeGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)progressBar).getId()
				,"direction","right",
				"percent", 0.50));
		*/
		//click OK after swipe
		driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
		
		//select more options
		driver.findElement(AppiumBy.accessibilityId("More options")).click();
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text=\"Completed tasks\"]"))).click();
		
		List<WebElement> completedTaks = driver.findElements(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.app.todolist:id/rl_exlv_task_group_root\"]"));
		assertEquals(completedTaks.size(), 2);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
