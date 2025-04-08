package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class activity6 {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://training-support.net/webelements/login-form/");
		
		
	}
	
	@Test
	@Parameters ({"UserName","Password"})
	public void loginTest(String UserName, String Password) {
		
		String actualMsg = "Welcome Back, Admin!";
		
        WebElement username = driver.findElement(By.id("username"));
    	
        WebElement password = driver.findElement(By.id("password"));
	
        username.sendKeys(UserName);
        password.sendKeys(Password);
        
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
	
        String expectedMsg = driver.findElement(By.cssSelector("h2.text-center")).getText();
	
        Assert.assertEquals(actualMsg, expectedMsg);
	
    }
	
	
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		
	}
	

}
