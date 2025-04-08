package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class LoginSteps extends BaseClass {
	@Given("the user is on the login page")
	public void openPage() {
		driver.get("https://training-support.net/webelements/login-form");
		assertEquals("Selenium: Login Form", driver.getTitle());
	}

	@When("the user enters username and password")
	public void enterCredentials() {
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("password");
	}

	@When("the user enters {string} and {string}")
	public void enterCredentialsWithParams(String username, String password) {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@And("clicks the submit button")
	public void clickSubmit() {
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
	}

	@Then("get the confirmation message and verify it")
	public void confirmMessage() {
		String message = driver.findElement(By.tagName("h2")).getText();
		assertEquals("Welcome Back, Admin!", message);
	}

	@Then("get the confirmation text and verify message as {string}")
	public void confirmMessageWithParams(String expectedMessage) {
		String message = "NOT FOUND";
		if (expectedMessage.contains("Invalid")) {
			message = wait.until(visibilityOfElementLocated(By.id("subheading"))).getText();
			assertEquals(expectedMessage, message);
		} else {
			message = wait.until(visibilityOfElementLocated(By.tagName("h2"))).getText();
			assertEquals(expectedMessage, message);
		}
	}

}