package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class activity9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
        
		Actions builder = new Actions(driver);

        // Open the browser
        driver.get("https://training-support.net/webelements/keyboard-events");

        // Verify page title
        System.out.println("Page title is: " + driver.getTitle());
		
		//Task 
        //On the page, type out a string from the Selenium script to show on the page
        //Print the message to the console.
		builder.sendKeys("This is coming from Selenium").sendKeys(Keys.RETURN).build().perform();
		
		String msg = driver.findElement(By.cssSelector("h1.mt-3")).getText();
		System.out.println(msg);
		
		// Close the browser
        driver.quit();

	}

}
