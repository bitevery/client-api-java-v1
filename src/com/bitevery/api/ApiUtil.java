package com.bitevery.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiUtil {
	static String BASE_URL = "https://www.bitevery.com/api";
	static String SEPARATOR = "/";
	static String CONNECTOR = "&";
	
	private static String getParamString(List<String> params){
		String result = "";
		for(String item : params) {
			result += CONNECTOR;
			result += item;
		}
		
		return result;
	}
	
	public static String call_api(String resource, String api_code, String method, List<String> params, String data) {
		String request = BASE_URL + SEPARATOR + resource + SEPARATOR + api_code + getParamString(params);
		return sendRequest(request, method, data);
	}
	
	public static String call_get_api_code(String resource, String username, String password) {
		String usernameFull = "username=" + username;
		String passwordFull = "password=" + password;
		String request = BASE_URL + SEPARATOR + resource + CONNECTOR + usernameFull + CONNECTOR + passwordFull;
		return sendRequest(request, "GET", null);
	}
	
	private static String sendRequest(String url, String method, String data) {
	    try {
	    	URL obj = new URL(url);
	    	HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
	    	conn.setReadTimeout(5000);
	    	conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
	    	if(method == null) {
	    		conn.setRequestMethod("GET");
	    	}
	    	else {
	    		conn.setRequestMethod(method);
	    	}

	    	boolean redirect = false;
	    	// normally, 3xx is redirect
	    	int status = conn.getResponseCode();
	    	if (status != HttpURLConnection.HTTP_OK) {
	    		if (status == HttpURLConnection.HTTP_MOVED_TEMP
	    			|| status == HttpURLConnection.HTTP_MOVED_PERM
	    				|| status == HttpURLConnection.HTTP_SEE_OTHER)
	    		redirect = true;
	    	}

	    	if (redirect) {
	    		// get redirect url from "location" header field
	    		String newUrl = conn.getHeaderField("Location");
	    		return sendRequest(newUrl, method, data);
	    	}

	    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    	String inputLine;
	    	StringBuffer html = new StringBuffer();

	    	while ((inputLine = in.readLine()) != null) {
	    		html.append(inputLine);
	    	}
	    	in.close();

	    	return html.toString();

	        } catch (Exception e) {
	    	e.printStackTrace();
	    	return "Request Failed.";
	        }
	}
}
