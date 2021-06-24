package com.restassured.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ListResourceApiSteps {


	@When("user calls GET end point status code is 200")
	public void list_resource_end_point_is_available () {

		RestAssured.given().log().all().baseUri("https://reqres.in")
				.when()
				.get("/api/unknown")
				.then().log().all()
				.assertThat()
				.statusCode(200);
	}

	@Then("response body should contain id")
	public void response_body_should_contain_id () {

		RestAssured.given().log().all().baseUri("https://reqres.in")
				.when()
				.get("/api/unknown")
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.body("data[2].id" , is(notNullValue()));
	}


}

