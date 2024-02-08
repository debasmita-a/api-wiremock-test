package org.mock.api;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.client.WireMock;

public class APIMocks {

	/**
	 * this method will create stub for dummy user
	 * 
	 */
	public static void getDummyUser() {
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("{\r\n" + 
								"  \"name\" : \"Debasmita\"\r\n" + 
								"}"))); 
				            
		         
	}
	
	public static void getDummyUserWithQueryParams() {
		stubFor(get(urlPathEqualTo("/api/users"))
				.withQueryParam("param", equalTo("value"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("{\r\n" + 
								"  \"message\" : \"User is searched.\"\r\n" + 
								"}")));
				     
	}
	
	public static void createDummyUser() {
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", WireMock.equalTo("application/json"))
				.withRequestBody(equalToJson("{\"name\" : \"Debasmita\"}"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", "application/json")
						.withBody("{\"message\" : \"User created.\"}"))
				);
		       
	}
	
	public static void deleteDummyUser() {
		stubFor(delete(urlEqualTo("/api/users"))
				.willReturn(aResponse()
						.withStatus(204)
						.withBody("{\"message\" : \"User deleted.\"}"))
				);
	}
}
