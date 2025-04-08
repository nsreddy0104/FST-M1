package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;

import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity5 {

	AppiumDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		
		UiAutomator2Options options = new UiAutomator2Options()
				.setAutomationName("UiAutomator2")
				.setPlatformName("android")
		        .setAppPackage("com.google.android.apps.messaging")
		        .setAppActivity(".ui.ConversationListActivity")
				.noReset();
		
		URL serverURL = new URI("http://127.0.0.1:4723").toURL();
		
		//Initialize driver
		driver = new AndroidDriver(serverURL, options);
		
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	@Test
	public void sendMessage() {
		
        driver.findElement(AppiumBy.id("com.google.android.apps.messaging:id/start_chat_fab")).click();
    	       
	
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text=\"Type names, phone numbers, or emails\"]")));
	   // Find the element to add recipient
	
        driver.findElement(AppiumBy.className("android.widget.TextView")).sendKeys("18282832912");
	
        // Press ENTER
	
        ((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
	     // Wait for textbox to appear
	
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("compose_message_text")));
	
        // Enter text to send
	
        driver.findElement(AppiumBy.id("compose_message_text")).sendKeys("Hello from Appium");
	
        // Press Send
	
        driver.findElement(AppiumBy.accessibilityId("Send SMS")).click();
        // Assertion
	
        String messageTextSent = driver.findElement(AppiumBy.id("message_text")).getText();
	
        Assert.assertEquals(messageTextSent, "Hello from Appium");
		
	}
	
	@AfterClass
	public void closure() {
		
	}
}
