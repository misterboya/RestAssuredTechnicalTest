package com.restassured.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * This class is basically heart of the entire framework.
 * Currently it's behaviour is not correct, this will be looked into during code optimisation.
 */

public class Helper {

	public static RequestSpecification requestSpecification;

	public RequestSpecification getRequestSpecification() throws IOException {

		if (requestSpecification == null){
				PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			return requestSpecification;
		}

		return requestSpecification;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config/global.properties");
		properties.load(fileInputStream);

		return properties.getProperty(key);
	}

	public String getJsonPath(Response response, String key) {

		String storeResponse = response.asString();
		JsonPath jsonPath = new JsonPath(storeResponse);
		return jsonPath.get(key).toString();
	}

	public static String generateStringFromResource(String path) throws IOException, IOException {

		return new String( Files.readAllBytes(Paths.get(path)));

	}
}
