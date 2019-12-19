package com.ayusha.communication.core;

import java.util.HashMap;

/**
 * 
 * @author Author
 * Date:01-Aug-2019
 * Communication Service Methods
 *
 */
public interface INotificationHandler {

	
	/** send notification **/
	public String sendNotification(HashMap messageProperties, String eventType) throws NotificationOperationException;
}
