package project_CRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2_HeaderImage {

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
	public void getHeaderImageUrl() {
		
		String src = driver.findElement(By.xpath("//img[@alt='SuiteCRM']")).getAttribute("src");
		System.out.println("The url of the header image is : " + src);
		
	}

	@AfterClass(alwaysRun = true)
	public void closeure() {
		driver.close();
	}

}
