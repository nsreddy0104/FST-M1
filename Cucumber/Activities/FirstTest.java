package stepDefinitions;


import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest extends BaseClass{
	@Given("User is on TS Home Page")
	public void openPage() {
		
	    driver.get("https://training-support.net/");
	}
	
	@When("they click on the About Us link")
	public void clickLink() {
		driver.findElement(By.linkText("About Us")).click();
	}
	
	@Then("redirect to About page")
	public void redirectToAbout() {
		assertEquals(driver.getTitle(),"About Training Support");
	}
	
}