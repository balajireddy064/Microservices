package com.ayusha.communication.core;

import java.util.HashMap;

/**
 * 
 * @author Author 
 * Date:01-Aug-2019 
 * Communication Service Methods
 *
 */
public interface INotificationChannel {

	/**
	 * send Notification
	 */
	public String sendNotification(HashMap messageProps) throws NotificationOperationException;
}
