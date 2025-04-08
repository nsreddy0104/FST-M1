package project_CRM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity9_LeadsTable {
	
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
	public void getOddNumRows() throws InterruptedException {
		// Actions builder = new Actions(driver);

		WebElement userName = driver.findElement(By.id("user_name"));//
		WebElement password = driver.findElement(By.id("username_password"));
		WebElement login = driver.findElement(By.xpath("//input[@id='bigbutton']"));

		userName.sendKeys("admin");
		password.sendKeys("pa$$w0rd");
		login.click();

		// Navigate to Sales
		WebElement sales = driver.findElement(By.xpath("//a[@id='grouptab_0']"));
		sales.click();

		// clicking Leads
		wait.until(ExpectedConditions.elementToBeClickable(By.id("moduleTab_9_Leads"))).click();

		// Table element
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[contains(@class,'list view table')]")));

		List <WebElement> names= driver.findElements(By.xpath("//table[contains(@class,'list view tab')]/tbody/tr/td[contains(@type, 'name')]"));
		List <WebElement> users= driver.findElements(By.xpath("//table[contains(@class,'list view tab')]/tbody/tr/td[contains(@field,'assigned_user_name')]"));
	
		for (int i = 0; i<10; i++) {
			
			System.out.printf((i+1) + " : The name is :" +names.get(i).getText()+ " & The user is :" +users.get(i).getText());
			System.out.println();
		}
	
	}

	@AfterClass(alwaysRun = true)
	public void closeure() {
		driver.close();
	}

	

}
