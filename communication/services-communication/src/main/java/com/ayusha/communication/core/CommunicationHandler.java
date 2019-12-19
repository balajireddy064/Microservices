package com.ayusha.communication.core;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Author
 * Date:01-Aug-2019
 * Communication Service Methods
 *
 */
@Component	
public class CommunicationHandler {
	
	/** iNotificationHandler **/
	private INotificationHandler iNotificationHandler = null;
	
	/**notificationHandlerRegistry**/
	private HashMap notificationHandlerRegistry;
	
	/**
	 * default constructor
	 */
	public CommunicationHandler() {
		
		
	}
	
	/**
	 * init method
	 */
	public void init() {
		
	}
	
	/**
	 * sendMessage
	 * @param messageProperties
	 * @param notificationType
	 * @param eventType
	 * @throws NotificationOperationException
	 */
	public void sendMessage(HashMap messageProperties, String notificationType,String eventType) throws NotificationOperationException{
		System.out.println(" MESSAGE PROPERTIES TO AYUSHA "+notificationHandlerRegistry);
		if(notificationHandlerRegistry.containsKey(notificationType)) {
			System.out.println(" MESSAGE PROPERTIES TO AYUSHA "+messageProperties);
			System.out.println(" THE MESSAGE IS "+eventType+"::::: "+notificationHandlerRegistry);
			iNotificationHandler = (INotificationHandler)notificationHandlerRegistry.get(notificationType);
			System.out.println(" THE MESSAGE IS=========> <<iNotificationHandler>>"+iNotificationHandler);
			iNotificationHandler.sendNotification(messageProperties, eventType);
		}
	}

	/**
	 * @return the notificationHandlerRegistry
	 */
	public HashMap getNotificationHandlerRegistry() {
		return notificationHandlerRegistry;
	}

	/**
	 * @param notificationHandlerRegistry the notificationHandlerRegistry to set
	 */
	public void setNotificationHandlerRegistry(HashMap notificationHandlerRegistry) {
		this.notificationHandlerRegistry = notificationHandlerRegistry;
	}
}
