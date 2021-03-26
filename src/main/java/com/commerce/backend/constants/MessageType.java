package com.commerce.backend.constants;

public enum MessageType {
	Success("SUCCESS"),
	Fail("Fail");
	private String message;
	
	public String getMessage() {
		return this.message;
	}
	 MessageType(String message) {
		this.message = message;
	}
}
