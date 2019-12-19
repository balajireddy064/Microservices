package com.commons.message.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.personal.event.model.AppEvent;

public class MessageHandler {
	
	/** event handler **/
	private EventHandler eventHandler = null;
	
	/**
	 * default constructor
	 */
	public MessageHandler() {
		
	}
	
	/**
	 * init
	 */
	public void init() {
		ApplicationContext applicationContext = null;
		
		//LOG.info("building chatbot..........");
		try {
			applicationContext = new ClassPathXmlApplicationContext("event-handlers-config.xml");
			eventHandler  =  (EventHandler)applicationContext.getBean(EventHandler.class);
			System.out.println(" About to publish the event..........OKKKKKK................ THIS "+eventHandler);
		}catch(Exception exception) {
			exception.printStackTrace();
			//LOG.exception(exception);
			//throw new ChatbotOperationsException("Error while building chatbot");
		}
	}
	
	/**
	 * handle message
	 */
	public void handleMessage(String message) {
		System.out.println(" About to publish the event........................... THIS "+eventHandler);
		AppEvent appEvent = new AppEvent(eventHandler.getAppEventPublisher(),"Test","Test");
		
		eventHandler.pusblishEvent(appEvent);
	}

}
