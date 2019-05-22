package com.cafe24.mysite.dto;

public class JSONResult {
	private String result;
	private String message;
	private Object data;
	
	public static JSONResult fail(String message) {
		return new JSONResult("success", message, null);
	}
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	
	private JSONResult(String result, String message, Object data ) {
		this.result = result;
		this.message = message;
		this.data= data;
	}
	
	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
	
	public Object getData() {
		return data;
	}
}
