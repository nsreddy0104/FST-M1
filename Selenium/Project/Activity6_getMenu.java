package project_CRM;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity6_getMenu {
	
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void setUp() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// open the link
		driver.get("http://alchemy.hguy.co/crm");
	}

	@Test()
	public void getMenu() {
		
		WebElement userName = driver.findElement(By.id("user_name"));//
		WebElement password = driver.findElement(By.id("username_password"));
		WebElement login = driver.findElement(By.xpath("//input[@id='bigbutton']"));
		
		userName.sendKeys("admin");
		password.sendKeys("pa$$w0rd");
		login.click();
		
		String msg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='grouptab_3']"))).getText();
		System.out.println("Element is clickable and pls check");
		assertEquals(msg, "ACTIVITIES");		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeure() {
		driver.close();
	}

}
