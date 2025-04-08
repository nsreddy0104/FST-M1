package activities;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class activity6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Find the checkbox input element. 
		 * Check if it is visible on the page. 
		 * Click the "Remove Checkbox" button. 
		 * Check if it is visible again and print the
		 * result.
		 */

		WebDriver driver = new FirefoxDriver();
		
		
        // Open the browser
        driver.get("https://training-support.net/webelements/dynamic-controls");

        // Verify page title
        System.out.println("Page title is: " + driver.getTitle());
        
        WebElement checkbox = driver.findElement(By.id("checkbox"));
        //check if check box is selecetd
        System.out.println("Checkbox is selected: " + checkbox.isSelected());
        // click the checkbox 
        checkbox.click();
        //Re-check if check box is selecetd
        System.out.println("Recheck if Checkbox is selected: " + checkbox.isSelected());
        

        // Close the browser
        driver.quit();
        
	}

}
