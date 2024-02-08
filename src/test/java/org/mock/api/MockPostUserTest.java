package org.mock.api;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MockPostUserTest {

	@BeforeTest
	public void setup() {
		WireMockSetup.createWireMockServer();
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8089;
	}
	
	@AfterTest
	public void teardown() {
		WireMockSetup.stopWireMockServer();
	}
	
	@Test
	public void createDummyUserTest() {
		APIMocks.createDummyUser();
		
		Response response = RestAssured.given().log().all()
		            .contentType("application/json")
		            .body("{\r\n" + 
							"  \"name\" : \"Debasmita\"\r\n" + 
							"}")
		            .when().log().all()
		            .post("api/users");
		response.then().assertThat().statusCode(201).log().all()
		                     .and()
		                     .contentType("application/json")
		                     .body("message", equalTo("User created."));
		System.out.println(response.getBody().asString());
	}
}
