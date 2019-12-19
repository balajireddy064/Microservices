package com.ayusha.http.client.models;

/**
 * 
 * @author authror
 * 07-Aug-2019
 * Service response from hhtp client
 *
 */
public class ServiceResponse {
	
	/** response code **/
	private String responseCode;
	
	/** message **/
	private String responseMessage;
	
	/** service response object **/
	private Object response;
	
	/**
	 * default constructor
	 */
	public ServiceResponse() {
		
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the response
	 */
	public Object getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Object response) {
		this.response = response;
	}
}
