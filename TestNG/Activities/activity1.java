package activities;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class activity1 {
	//Declare initialization
	WebDriver driver;
	
	@BeforeClass //setup function
	
	public void setUp() {
		//Driver initialiazation
		driver = new FirefoxDriver();
		
		//Open browser
		driver.get("https://training-support.net/");
	}
	
	@Test(priority = 1)
	public void pageTitleTest() {
		
		assertEquals(driver.getTitle(),"Training Support");
	}
	
	@Test(priority = 2)
	public void aboutLinkTest() {
		
		driver.findElement(By.linkText("About Us")).click();
		
		assertEquals(driver.getTitle(),"About Training Support");
		
	}
	
	@AfterClass //Teardown function
	public void tearDown() {
		//Close browser
		driver.quit();
	}

}
