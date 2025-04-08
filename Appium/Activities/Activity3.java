package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity2 {
	
AppiumDriver driver;
	
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
	
		//to open url
		driver.get("https://training-support.net");

}
	@Test
	public void webPage() {
		
		
		String pageHeading = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text='Training Support']")).getText();
		System.out.println("The heading of the page is : "+pageHeading);
		
		//about page heading
		//Click the button
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='About Us']")).click();
		
		String aboutPage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='About Us']")).getText();
		System.out.println("The head on above us is : "+aboutPage);
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		
	}
	
}