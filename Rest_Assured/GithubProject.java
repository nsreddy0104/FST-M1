package activities;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.authentication.OAuth2Scheme;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GithubProject {
	
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	String sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQDJ0X+ogX+ALZrRV31TBPHm+9+hpfvQdL1FgeLWveCOMajCxF3W9NEs88WOcp9z+px1OrAhO1pjxTlOAu8Mt/ZqgFlgZPjuyQ3md/vrJ9QfGnRYUjbwev4M2GnTVeb7iElWcPdhSF6oZRuhV/pjQT6qzdgChAgQ5XJ9MS1Y5sBHew==";
	//String accessToken = "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";
	int sshKeyId;
	@BeforeClass
	public void setup() {
		
		//Request
		reqSpec = new RequestSpecBuilder()
				.setBaseUri("https://api.github.com/user/keys")
				.addHeader("Authorization", "token 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")
				.addHeader("Content-Type","application/json")
				
				.build();
		
		//response
		resSpec = new ResponseSpecBuilder()
					.expectResponseTime(lessThanOrEqualTo(4000L))
					.expectBody("key",equalTo(sshKey))
					.expectBody("title",equalTo("TestAPIKey"))
					.build();
	
	}

	@Test(priority = 1)
	public void postRequest() {
		Map<String, String> reqBody = new HashMap<>();

        reqBody.put("title", "TestAPIKey");

        reqBody.put("key", sshKey);

        // Generate response

        Response response = given().spec(reqSpec).body(reqBody).when().post();
        // Extract the id and save it
        sshKeyId = response.then().extract().path("id");
        
        // Assertions
        response.then().statusCode(201).spec(resSpec);

    }

	
	@Test(priority = 2)
	public void getKey() {
		
		given()
			.spec(reqSpec)
			.pathParam("keyId", sshKeyId)
		.when()
			.get("/{keyId}")
		.then()
			.statusCode(200).spec(resSpec);
	}
	
	@Test(priority = 3)
	public void delKey() {
		
		given()
		.spec(reqSpec)
		.pathParam("keyId", sshKeyId)
	.when()
		.delete("/{keyId}")
	.then()
		.statusCode(204).
		time(lessThanOrEqualTo(3000L));
		
	}
}
