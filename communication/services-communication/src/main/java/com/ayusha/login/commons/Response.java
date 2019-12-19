package com.ayusha.login.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class Response {

	private String status;
	private String message;
	private String statusCode;
	
	private Object data;
	private Object errors=400;
	private boolean isApiTimeout;
	
	
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

	public boolean getIsApiTimeout() {
		return isApiTimeout;
	}

	public void setIsApiTimeout(boolean isApiTimeout) {
		this.isApiTimeout = isApiTimeout;
	}

}
