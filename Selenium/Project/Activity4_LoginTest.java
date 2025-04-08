package project_CRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4_LoginTest {
	
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void setUp() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// open the link
		driver.get("http://alchemy.hguy.co/crm");
	}

	@Test
	public void loginTest() {
		
		WebElement userName = driver.findElement(By.id("user_name"));//
		WebElement password = driver.findElement(By.id("username_password"));
		WebElement login = driver.findElement(By.xpath("//input[@id='bigbutton']"));
		
		userName.sendKeys("admin");
		password.sendKeys("pa$$w0rd");
		login.click();
		
		System.out.println("The current url is : " +driver.getCurrentUrl());
		
	}

	@AfterClass(alwaysRun = true)
	public void closeure() {
		driver.close();
	}

}
