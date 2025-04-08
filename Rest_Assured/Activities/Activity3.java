package activities;


import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import java.util.HashMap;

import java.util.Map;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;

public class Activity3 {
	//Declare the Request specification
	RequestSpecification requestSpec;
	//Declare the Response specification
	ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://petstore.swagger.io/v2/pet")
				.addHeader("Content-Type","application/json")//.setContentType("ContentType.JSON")
				.build();
	
	
		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)	
				.expectContentType(ContentType.JSON)
				.expectResponseTime(lessThanOrEqualTo(5000L))//5000l bcz its accepts only long
				.build();
	}
	
	@DataProvider(name = "petInfo")
	public Object[][] petInfoProvider(){
		Object[][] testData = new Object[][] {
			{ 77232, "Riley", "alive" }, 
			{ 77233, "Hansel", "alive" } 
		};
		return testData;
	}
	
	@Test(priority=1, dataProvider = "petInfo")
	public void addPets(int petId, String petName, String petStatus) {
		
		Map<String, Object> reqBody = new HashMap<>();
		
		reqBody.put("id", petId);
		reqBody.put("name", petName);
		reqBody.put("status", petStatus);
		
		given()
		.spec(requestSpec)
		.body(reqBody)
		.log().all()
		.when()
			.post()
		.then()
			.spec(responseSpec)
			.body("name", equalTo(petName));
			
			
	}
	
	@Test(priority=2, dataProvider = "petInfo")
	public void getPets(int petId, String petName, String petStatus) {
		
		given()
			.spec(requestSpec)
			.pathParam("petId", petId)
			.log().all()
		.when()
			.get("/{petId}")
		.then()
			.spec(responseSpec)
			.body("status", equalTo("alive"));
			
	}
	
	@Test(priority=3, dataProvider = "petInfo")
	public void deletePets(int petId, String petName, String petStatus) {
		
		given()
			.spec(requestSpec)
			.pathParam("petId", petId)
			.log().all()
		.when()
			.delete("/{petId}")
		.then()
			.spec(responseSpec)
			.body("message", equalTo(""+petId));//here message expects id as string, hence explicitly converting it to a string
			
	}
	
	
	
	
}
