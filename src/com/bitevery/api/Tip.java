package com.bitevery.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tip {
	private static String ENDPOINT = "tip";
	private static String SEPARATOR = "/";
	private static String ACTION_TIPLINK = "tip_link";
	private String FORMAT = "URL";
	private String LOCALE = "en-us";
	
	public void setFormat(String format) {
		FORMAT = format;
	}
	
	public String getFormat() {
		return FORMAT;
	}
	
	public void setLocale(String locale) {
		LOCALE = locale;
	}
	
	public String getLocale() {
		return LOCALE;
	}
	
	private static String getResourceString(String action) {
		return ENDPOINT + SEPARATOR + action;
	}
	
	private static List<String> paramToList(Map<String, String> params) {
		List<String> result = new ArrayList<String>();
		
		for(String key : params.keySet()) {
			String keyValue = key + "=" + params.get(key);
			result.add(keyValue);
		}
		return result;
	}
	
	public String getTipLink(String api_code, Map<String, String> distribution) {
		String resource = getResourceString(ACTION_TIPLINK);
		Map<String, String> params = new HashMap<String, String>();
		params.put("format",getFormat());
		params.put("locale", getLocale());
		
		if(distribution == null || distribution.isEmpty()){
			return getTipLinkSingleReceiver(resource, api_code, params);
		}
		else {
			return getTipLinkMultipleReceivers(resource, api_code, params, distribution);
		}
	}
	
	private static String getTipLinkSingleReceiver(String resource, String api_code, Map<String, String> params){
		return ApiUtil.call_api(resource, api_code, "GET", paramToList(params), null);
	}
	
	private static String getTipLinkMultipleReceivers(String resource, String api_code, Map<String, String> params, Map<String, String> distribution){
		params.putAll(distribution);
		return ApiUtil.call_api(resource, api_code, "GET", paramToList(params), null);
	}
}
