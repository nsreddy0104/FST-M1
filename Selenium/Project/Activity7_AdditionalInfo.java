package project_CRM;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity7_AdditionalInfo {
	
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass(alwaysRun = true)
	public void setUp() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// open the link
		driver.get("http://alchemy.hguy.co/crm");
	}

	@Test()
	public void getAddInfoFromPopup() throws InterruptedException {
		//Actions builder = new Actions(driver);
		
		WebElement userName = driver.findElement(By.id("user_name"));//
		WebElement password = driver.findElement(By.id("username_password"));
		WebElement login = driver.findElement(By.xpath("//input[@id='bigbutton']"));
		
		userName.sendKeys("admin");
		password.sendKeys("pa$$w0rd");
		login.click();
		
		//Navigate to Sales
		WebElement sales = driver.findElement(By.xpath("//a[@id='grouptab_0']"));		
		sales.click();
		
		//clicking leads
		wait.until(ExpectedConditions.elementToBeClickable(By.id("moduleTab_9_Leads"))).click();
		
		
		//clicking the info icon
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='adspan_77c0ebaa-448d-740d-a00f-605ae70266e9']"))).click();
		
		//fetching the phone number
		WebElement phoneNoEle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'phone')]")));
		String phoneNo = phoneNoEle.getText();
		System.out.println("Phone No is : " +phoneNo);

		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeure() {
		driver.close();
	}

}
