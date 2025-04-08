package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class activity5 {
	
	WebDriver driver;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() {
		
		driver = new FirefoxDriver();
		driver.get("https://training-support.net/webelements/target-practice");
		
	}
	
	@Test(groups = {"HeaderTests","ButtonTests"})
	public void pageTitleTest() {
		
		Assert.assertEquals(driver.getTitle(), "Selenium: Target Practice");
	}
	
	@Test (dependsOnMethods = {"pageTitleTest"} ,groups= {"HeaderTests"})
	public void headerTest1() {
		
		WebElement header3 = driver.findElement(By.xpath("//h3[contains(@class, 'orange')]"));
		Assert.assertEquals(header3.getText(), "Heading #3");
	}
	
	@Test (dependsOnMethods = {"pageTitleTest"} ,groups= {"HeaderTests"})
	public void headerTest2() {
		
		Color header5Color = Color.fromString(driver.findElement(By.cssSelector("h5.text-purple-600")).getCssValue("color"));
		Assert.assertEquals(header5Color.asHex(), "#9333ea");
	}
	
    @Test (dependsOnMethods = {"pageTitleTest"}, groups = {"ButtonTests"})
	
    public void ButtonTest1() {
	
        WebElement button1 = driver.findElement(By.cssSelector("button.olive"));
        Assert.assertEquals(button1.getText(), "Olive");
	
    }
	
    @Test (dependsOnMethods = {"pageTitleTest"}, groups = {"ButtonTests"})
    public void ButtonTest2() {
	
        WebElement button2 = driver.findElement(By.cssSelector("button.brown"));
        Assert.assertEquals(button2.getCssValue("color"), "rgb(255, 255, 255)");
	
    }
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	

}
