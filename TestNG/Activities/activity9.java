package activities;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class activity9 {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://training-support.net/webelements/alerts");
		
		
	}
	
	@BeforeMethod()
	public void defaultContent() {
		
		driver.switchTo().defaultContent();
		
	}
	
	
	@Test(priority = 1)
	public void simpleAlertTestCase() {
		
		driver.findElement(By.id("simple")).click();
		Reporter.log("Simple Alert opened |");
		Alert smplAlert = driver.switchTo().alert();
		Reporter.log(smplAlert.getText());
		
		smplAlert.accept();
		
		String msg = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(msg, "You just accepted a simple alert!");
	
    }
	@Test(priority = 2)
	public void confirmAlertTestCase() {
		
		driver.findElement(By.id("confirmation")).click();
		Reporter.log("Confirm Alert opened |");
		Alert cnfrmAlert = driver.switchTo().alert();
		Reporter.log(cnfrmAlert.getText());
		
		cnfrmAlert.accept();
		
		String msg = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(msg, "You just accepted a confirmation alert!");
		
	
    }
	
	@Test(priority = 3)
	public void promptAlertTestCase() {
		
		driver.findElement(By.id("prompt")).click();
		Reporter.log("Prompt Alert opened |");
		Alert promptAlert = driver.switchTo().alert();
		Reporter.log(promptAlert.getText());
		
		promptAlert.sendKeys("Madhu");
		promptAlert.accept();
		
		String msg = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(msg, "You typed \"Madhu\" into the prompt!");
		
	
    }
	
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
		
	}
	
	
	
}
