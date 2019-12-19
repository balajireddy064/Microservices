package com.ayusha.user.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class Response {

	private String status;
	private String message;
	private Object data;
	private Object errors;
	private boolean isApiTimeout;
	private String timestamp;

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

	public void setApiTimeout(boolean isApiTimeout) {
		this.isApiTimeout = isApiTimeout;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
