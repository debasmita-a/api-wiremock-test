package org.mock.api;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class MockGetUserWithParamTest {

	@BeforeTest
	public void setup() {
		WireMockSetup.createWireMockServer();
		RestAssured.baseURI = "http://localhost:8089";
		RestAssured.port = 8089;
	}
	
	@AfterTest
	public void teardown() {
		WireMockSetup.stopWireMockServer();
	}
	
	@Test
	public void mockUserWithParamsAPITest() {
		APIMocks.getDummyUserWithQueryParams();
		RestAssured.given().log().all()
		.param("param", "value")
	      .when().log().all()
	      .get("/api/users")
	      .then().log().all()
	      .statusCode(200)
	      .body("message", equalTo("User is searched."));
		
	}
}
