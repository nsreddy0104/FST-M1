package activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import io.restassured.response.Response;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Activity2 {

	@Test(priority = 1)
	public void addNewUserFromFile() throws IOException {

		FileInputStream inputJson = new FileInputStream("src/test/java/activities/userinfo.json");

		given()
			.baseUri("https://petstore.swagger.io/v2/user")
			.header("Content-Type", "application/json")
			.body(inputJson)
		.when()
			.post()
		.then()
			.statusCode(200)
			.body("message", equalTo("20251001"));
		inputJson.close();
	}

	@Test(priority = 2)

	public void getUserInfo() {

		File outputjson = new File("src/test/java/activities/userGetInfoResponse.json");

		Response response = given()
					.baseUri("https://petstore.swagger.io/v2/user")
					.header("Content-Type", "application/json")
					.pathParam("username", "justinc")
				.when()
					.get("/{username}");

		// get response body
		String resBody = response.getBody().asPrettyString();

		try {
			// create JSON file
			outputjson.createNewFile();
			// write response to extrenal file
			FileWriter writer = new FileWriter(outputjson.getPath());
			writer.write(resBody);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();

		}
		
		//Assertion
		response.then().body("id",equalTo(20251001));
		response.then().body("username",equalTo("justinc"));
		response.then().body("firstName",equalTo("Justin"));
		response.then().body("lastName",equalTo("Case"));
		response.then().body("email",equalTo("justincase@mail.com"));
		response.then().body("password",equalTo("password123"));
		response.then().body("phone",equalTo("9812763450"));

	}
	
	@Test(priority = 3)

	public void delUserInfo() {
		
		given()
			.baseUri("https://petstore.swagger.io/v2/user")
			.header("Content-Type", "application/json")
			.pathParam("username", "justinc")
		.when()
			.delete("/{username}")
		.then()
			.body("code",equalTo(200))
			.body("message", equalTo("justinc"));
		
	}
	
}
