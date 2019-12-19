package com.ayusha.communication.core;

import java.util.HashMap;

/**
 * 
 * @author Author
 * Date:01-Aug-2019
 * Communication Services Methods
 *
 */
public interface IMessageFormatter {

	
	/** formate message **/
	public String format(HashMap messageProperties) throws NotificationOperationException;
}
