package com.restassured.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class LoginBody {

	private String email;
	private String password;
}
