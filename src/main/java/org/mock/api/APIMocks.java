package org.mock.api;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

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
		stubFor(get(urlEqualTo("/api/users"))
				.withQueryParam("param", equalTo("value"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/xml")
						.withBody("{\r\n" + 
								"  \"name\" : \"Debasmita\"\r\n" + 
								"}")));
				     
	}
}
