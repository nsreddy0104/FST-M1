package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class activity2 {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		//Driver initialiazation
		driver = new FirefoxDriver();
		
		//Open browser
		driver.get("https://training-support.net/webelements/target-practice/");
	}
	
	@Test
	public void titleTest() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Selenium: Target Practice");
	}
	
	@Test
	public void findBlackButton() {
		
		String colorTxt = driver.findElement(By.xpath("//button[contains(@class, 'purple-200')]")).getText();
		Assert.assertEquals(colorTxt, "Black");
	}
	
	@Test(enabled = false)
	public void skipped() {
		
	}
	

	@Test()
	public void skipUsingThrows() throws SkipException {
		
		String txt ="Skip";

	    if(txt.equals("Skip"))
	    {
		throw new SkipException("Skipping - This is not ready for testing ");
	    } 
	    else {
		//Execute test case when conditions are satisfied
	    }
		
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	

}
