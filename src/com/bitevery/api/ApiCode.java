package com.bitevery.api;

public class ApiCode {
	static String API_CODE_ENDPOINT = "api_code";
	public static String getApiCode(String username, String password) {
		return ApiUtil.call_get_api_code(API_CODE_ENDPOINT, username, password);
	}
}
