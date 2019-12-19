package com.ayusha.communication.data.models;

import java.util.HashMap;

/**
 * 
 * @author Author Date:01-Aug-2019 Communication Data Model class
 *
 */
public class NotificationMessageDataModel {

	/** HashMap message properties **/
	private HashMap hashMapMessageProperties;

	/** notificationType **/
	private String notificationType;

	/** eventType **/
	private String eventType = "";

	/**
	 * default constructor
	 */
	public NotificationMessageDataModel() {

	}

	/**
	 * @return the hashMapMessageProperties
	 */
	public HashMap getHashMapMessageProperties() {
		return hashMapMessageProperties;
	}

	/**
	 * @param hashMapMessageProperties the hashMapMessageProperties to set
	 */
	public void setHashMapMessageProperties(HashMap hashMapMessageProperties) {
		this.hashMapMessageProperties = hashMapMessageProperties;
	}

	/**
	 * @return the notificationId
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
