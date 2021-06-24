package com.restassured.stepdefinitions;

import com.restassured.utils.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

/**
 Currently pojo class is not able to bind, hence retuning jackson bind exception
 it needs some investigation. Bit weird though. So, the below code at the moment is not legal.

 TO DO --

 public void login_payload_is_available(String email, String pwd)
 LoginBody loginBody = new LoginBody();
 loginBody.email(email);
 loginBody.password(pwd);

 requestSpecification = given().spec(getRequestSpecification())
 .body(loginBody);
 System.out.println("This is the magic of pojo: " + requestSpecification);

 */
public class LoginApiSteps extends Helper {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;

	@Given("login payload with external json file")
	public void login_payload_is_available() throws IOException {

		String loginBodyExt = Helper.generateStringFromResource(System.getProperty("user.dir")+"/src/test/resources/json/login.json");

		System.out.println("Data extracted from external json file: " + loginBodyExt);

		requestSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.setContentType(ContentType.JSON)
				.build();
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();

		requestSpecification = given().spec(requestSpecification).body(loginBodyExt);



	}

	/**
	 * This step definition must be more generic in nature.
	 * Currently there is a bug if we have common scenario step for more than one feature.
	 * Request specification object some how is staled hence throwing null-pointer exception.
	 *
	 * TO DO -- optimise below step so that it will act as generic step for required scenario.
	 *
	 *		APIResources apiResources = APIResources.valueOf(resource);
	 * 		System.out.println("This is the resource: " + apiResources.getResource());
	 * 	if (method.equalsIgnoreCase("POST"))
	 * 			response = requestSpecification.when().post(apiResources.getResource());
	 * 		else if (method.equalsIgnoreCase("GET"))
	 * 			response = requestSpecification.when().get(apiResources.getResource());
	 */

	@When("user calls POST_LOGIN with POST http request")
	public void user_calls_with_http_request() {

		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		response = requestSpecification.when().post("/api/login");

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {

		System.out.println("Hey this is the actual status code: > " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}


}
