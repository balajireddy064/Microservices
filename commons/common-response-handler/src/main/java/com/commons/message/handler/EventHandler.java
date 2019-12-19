package com.commons.message.handler;

import com.personal.event.model.AppEvent;

public class EventHandler {
	
	/** event publisher **/
	private AppEventPublisher appEventPublisher = null;
	
	/**
	 * default constructor
	 */
	public EventHandler() {
		
	}
	
	/**
	 * publish event
	 */
	public void pusblishEvent(AppEvent appEvent) {
		appEventPublisher.publish(appEvent);
	}

	/**
	 * @return the appEventPublisher
	 */
	public AppEventPublisher getAppEventPublisher() {
		return appEventPublisher;
	}

	/**
	 * @param appEventPublisher the appEventPublisher to set
	 */
	public void setAppEventPublisher(AppEventPublisher appEventPublisher) {
		this.appEventPublisher = appEventPublisher;
	}
}
