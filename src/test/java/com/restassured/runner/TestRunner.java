package com.restassured.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features"} ,
		glue = {"com.restassured.stepdefinitions"},
		plugin = {"pretty"
		})

public class TestRunner extends AbstractTestNGCucumberTests {

}