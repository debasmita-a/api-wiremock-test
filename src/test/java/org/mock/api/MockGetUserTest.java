package org.mock.api;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class MockGetUserTest {

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
	public void mockUserAPITest() {
		APIMocks.getDummyUser();
		RestAssured.given()
		      .when()
		      .get("/api/users")
		      .then().log().all()
		      .statusCode(200)
		      .body("name", equalTo("Debasmita"));
	}

}
