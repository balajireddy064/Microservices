package com.commons.message.handler;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
* This is the event publisher.
*
* @author  Nagendra Kumar
* @version 1.0
* @since   2019-05-03 
*/
public class AppEventPublisher implements ApplicationEventPublisherAware {
	
	/** application event publisher **/
	   private ApplicationEventPublisher applicationEventPublisher;

	 /**
	  * publishes the event
	 */
	public void publish(org.springframework.context.ApplicationEvent appEvent) {
	   System.out.println(" I AM HERE");
	   applicationEventPublisher.publishEvent(appEvent);
	}
	/**
	 * application publisher
	 */
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		 this.applicationEventPublisher = applicationEventPublisher;
	}
}
