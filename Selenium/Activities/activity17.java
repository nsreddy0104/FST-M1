package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class activity17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver();
		
        // Open the browser
        driver.get("https://training-support.net/webelements/selects");

        // Verify page title
        System.out.println("Page title is: " + driver.getTitle());
        WebElement options = driver.findElement(By.cssSelector("select.h-80"));
        Select mulSel = new Select(options);
        
        mulSel.selectByVisibleText("HTML");
        mulSel.selectByIndex(4);
        mulSel.selectByIndex(5);
        mulSel.selectByIndex(6);
        mulSel.selectByValue("nodejs");
        mulSel.deselectByIndex(5);
        
        driver.quit();

	}

}
