package activities;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class activity7 {
	
    WebDriver driver;
    WebDriverWait wait;
    String expectedMessage = "Invalid credentials";
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		driver = new FirefoxDriver();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://training-support.net/webelements/login-form");
		
	}
	
	@Test(priority = 1)
	public void pageTitleTest() {
		
		Assert.assertEquals(driver.getTitle(), "Selenium: Login Form");
	}
	
	@DataProvider(name = "BadCredentials")
	public Object[][] creds(){
		Object[][] credentials = {
				{"admin","wrongPassword","Invalid credentials"},
				{"wrongUser","password","Invalid credentials"},
				{"wrongUser","wrongpassword","Invalid credentials"},
				{"   ", "   "," Invalid credentails"}
				
		};
		return credentials;
	}
	
	@Test(dataProvider = "BadCredentials", priority = 2)
	
	public void failedLoginTest(String username, String password, String message) {
		driver.navigate().refresh();
		
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
		
		//clear the inpt fields
		usernameField.clear();
		passwordField.clear();
		
		//Enter the values and click submit
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submitButton.click();
		
		
		
		
		String loginMessage = driver.findElement(By.id("subheading")).getText();
		assertEquals(loginMessage, expectedMessage);
		
	}
	
	@Test(priority = 3, enabled = false)
	public void successfulLoginTest() {
		
		
		
	}
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
