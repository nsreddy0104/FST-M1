package projects;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity1 {

	AppiumDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options().setPlatformName("android")
				.setAutomationName("UiAutomator2").setAppPackage("com.app.todolist")
				.setAppActivity(".view.MainActivity").noReset();

		URL serverURL = new URI("http://127.0.0.1:4723").toURL();

		// Initialize driver
		driver = new AndroidDriver(serverURL, options);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@DataProvider(name = "tasks")
	public Object[][] tasks() {
		Object[][] tasksToAdd = 
			{ 
				{ "Complete Activity 1", "High" }, 
				{ "Complete Activity 2", "Medium" },
				{ "Complete Activity 3", "Low" } 
			};

		return tasksToAdd;
	}

	@Test(dataProvider = "tasks")
	public void addTasks(String description, String risk) {

		wait.until(ExpectedConditions.elementToBeClickable(
				AppiumBy.xpath("//android.widget.ImageButton[@resource-id=\"com.app.todolist:id/fab_new_task\"]")));

		driver.findElement(
				AppiumBy.xpath("//android.widget.ImageButton[@resource-id=\"com.app.todolist:id/fab_new_task\"]"))
				.click();

		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.app.todolist:id/et_new_task_name")));

		WebElement name = driver.findElement(AppiumBy.id("com.app.todolist:id/et_new_task_name"));
		name.sendKeys(description);

		// Set the priority
		driver.findElement(AppiumBy.id("com.app.todolist:id/tv_new_task_priority")).click();
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy
				.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='" + risk + "']")))
				.click();//
		
		WebElement save = driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok"));
		save.click();


	}
	
	@Test(dependsOnMethods = "addTasks")
    public void verifyTasksCount() {
        // Get task list and assert once at the end
        List<WebElement> tasks = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        AppiumBy.id("com.app.todolist:id/rl_exlv_task_group_root")));

        assertEquals(tasks.size(), 3, "Mismatch in the number of added tasks!");
    }

	@AfterClass
	public void closure() {
		driver.quit();
	}
}
