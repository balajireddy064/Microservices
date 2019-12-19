package com.ayusha.communication.core;

import java.util.HashMap;

/**
 * 
 * @author Author
 * Date:01-Aug-2019
 * Communication Service Methods
 *
 */
public class EmailTicketLoggedFormatter implements IMessageFormatter{
	
	/** message formatter **/
	private String messageExpression = null;
	
	/**
	 * default constructor
	 */
	public EmailTicketLoggedFormatter() {
		
	}

	/**
	 * @return the messageExpression
	 */
	public String getMessageExpression() {
		return messageExpression;
	}

	/**
	 * @param messageExpression the messageExpression to set
	 */
	public void setMessageExpression(String messageExpression) {
		this.messageExpression = messageExpression;
	}

	/**
	 * format
	 */
	public String format(HashMap messageProperties) throws NotificationOperationException {
		// TODO Auto-generated method stub
		String formattedMessage = ""+messageExpression;
		if(messageProperties.containsKey("$serviceRequest")) {
			formattedMessage = formattedMessage.replaceAll("#serviceRequest", ""+messageProperties.get("$serviceRequest"));
		}
		if(messageProperties.containsKey("$serviceEngineer")) {
			formattedMessage = formattedMessage.replaceAll("#serviceEngineer", ""+messageProperties.get("$serviceEngineer"));
		}
		if(messageProperties.containsKey("$OTP")) {
			formattedMessage = formattedMessage.replaceAll("#OTP", ""+messageProperties.get("$OTP"));
		}
		if(messageProperties.containsKey("$Customer")) {
			formattedMessage = formattedMessage.replaceAll("#Customer", ""+messageProperties.get("$Customer"));
		}
		messageProperties.put("MESSAGE",""+formattedMessage);
		return "SUCCESS";
	}
}
