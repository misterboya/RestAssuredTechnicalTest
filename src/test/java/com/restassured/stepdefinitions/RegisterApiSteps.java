package com.restassured.stepdefinitions;

import com.restassured.utils.Helper;
import io.cucumber.java.en.Given;
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
 * You might notice some information messages on the console, this is intentional.
 * I find it very useful when coding, this will redundant during the code optimisation.
 */
public class RegisterApiSteps extends Helper {

	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;

	@Given("register with payload from external json")
	public void register_payload_is_available() throws IOException {

		String registerBodyExt = Helper.generateStringFromResource(System.getProperty("user.dir")+"/src/test/resources/json/register.json");

		System.out.println("Data extracted from external json file: " + registerBodyExt);

		requestSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in")
				.setContentType(ContentType.JSON)
				.build();
		requestSpecification = given().spec(requestSpecification).body(registerBodyExt);

	}

	@When("user calls POST_REGISTER should return status 200")
	public void user_calls_POST_REGISTER_should_return_status_200() {

		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		response = requestSpecification.when().post("/api/register");

		System.out.println("Hey this is the actual status code: > " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
