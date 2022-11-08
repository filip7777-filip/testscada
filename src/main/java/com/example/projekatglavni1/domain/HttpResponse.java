package com.example.projekatglavni1.domain;

import org.springframework.http.HttpStatus;

public class HttpResponse {
	private int hashCode;
	private HttpStatus httpStatus;
	private String reason;
	private String message;
	
	public HttpResponse() {
		// TODO Auto-generated constructor stub
	}

	public HttpResponse(int hashCode, HttpStatus httpStatus, String reason, String message) {
		super();
		this.hashCode = hashCode;
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.message = message;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setHashCode(int hashCode) {
		this.hashCode = hashCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
