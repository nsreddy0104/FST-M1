package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class activity7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver();
		
		
        // Open the browser
        driver.get("https://training-support.net/webelements/dynamic-controls");

        // Verify page title
        System.out.println("Page title is: " + driver.getTitle());
        
        /*
         * 
    Find the text field.
    Check if the text field is enabled and print it.
    Click the "Enable Input" button to enable the input field.
    Check if the text field is enabled again and print it.

         */
        
        WebElement txtfield = driver.findElement(By.id("textInput"));
        //to check the enablment
        System.out.println("If text field is enabled : " + txtfield.isEnabled());
        
        //Click the "Enable Input" button to enable the input field.
        driver.findElement(By.id("textInputButton")).click();
        
        System.out.println("Reverfy is text field is enabled : " + txtfield.isEnabled());
        
        driver.close();
	}

}
