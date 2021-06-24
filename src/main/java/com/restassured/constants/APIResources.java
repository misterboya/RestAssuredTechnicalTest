package com.restassured.constants;

public enum APIResources {

	POST_LOGIN("/api/login"),
	POST_REGISTER("/api/register"),
	GET_LIST_RESOURCE("/api/unknown");

	private String resource;

	APIResources (String resource) {
		this.resource = resource;
	}

	public String getResource() {

		return resource;
	}
}
