package activities;



import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Activity1 {
	
	@Test(priority = 1)
	public void postPet() {
		//creating a request body
		Map<String, Object> reqBody = new HashMap<>();
		
		reqBody.put("id", 202501);
		reqBody.put("name", "Nikki");
		reqBody.put("status", "alive");
		
		//Send request,get response,assert response
		given()
			.baseUri("https://petstore.swagger.io/v2/pet")
			.header("Content-Type","application/json")
			.body(reqBody)
		.when()
			.post()
		.then()
			.statusCode(200)
			.body("name", equalTo("Nikki"))
			.body("status", equalTo("alive"));
	
	}
	
	@Test(priority = 2)
	public void getPet() {
		
		given()
			.baseUri("https://petstore.swagger.io/v2/pet")
			.header("Content-Type","application/json")
			.pathParam("petId", 202501)
			.log().all()
		.when()
			.get("/{petId}")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", equalTo("Nikki"))
			.body("status", equalTo("alive"));
			
	}
	
	@Test(priority = 3)
	public void deletePet() {
		
		given()
		.baseUri("https://petstore.swagger.io/v2/pet")
		.header("Content-Type","application/json")
		.pathParam("petId", 202501)
		.log().all()
	.when()
		.delete("/{petId}")
	.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo(202501));
		
		
	}
	

}
