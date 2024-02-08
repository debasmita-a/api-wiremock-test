package org.mock.api;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MockDeleteUserTest {
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
	public void deleteDummyUserTest() {
		APIMocks.deleteDummyUser();
		
		Response response = RestAssured.given().log().all()
				                .when()
				                .delete("/api/users");
		response.then().log().all()
		        .assertThat().statusCode(204);
		        
		System.out.println(response.getBody().asString());
	}
}
