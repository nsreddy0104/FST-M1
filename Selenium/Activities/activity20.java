package activities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class activity20 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		
        // Open the browser
        driver.get("https://training-support.net/webelements/alerts");

        // Verify page title
        System.out.println("Page title is: " + driver.getTitle());
        
        driver.findElement(By.id("prompt")).click();
        
        //switch focus to alert
        Alert promptAlert = driver.switchTo().alert();
        
        System.out.println("The message is :" + promptAlert.getText());
        promptAlert.sendKeys("Awesome!");
        
        promptAlert.accept();
        
       
        System.out.println("Message : "+ driver.findElement(By.id("result")).getText());
        //closing
        driver.quit();

	}

}

