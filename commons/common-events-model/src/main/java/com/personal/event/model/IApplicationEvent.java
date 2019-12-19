package com.personal.event.model;

/**
* Application event interface
*
* @author  Nagendra Kumar
* @version 1.0
* @since   2019-05-03 
*/
public interface IApplicationEvent {
	
	/** event **/
	public void setType(String eventType);
	
	/** event payload **/
	public String getType();
	
	/** event **/
	public void setPayload(String payload);
	
	/** event payload **/
	public String getPayload();

}
