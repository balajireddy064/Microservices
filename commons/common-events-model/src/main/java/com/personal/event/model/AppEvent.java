package com.personal.event.model;

import org.springframework.context.ApplicationEvent;

public class AppEvent extends ApplicationEvent implements IApplicationEvent{

	/** type **/
	private String type="";
	
	/** payload **/
	private String payload="";
	
	/**
	 * default constructor
	 * @param source
	 */
	 public AppEvent(Object source,String type,String payload) {
	      super(source);
	 }

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
}
