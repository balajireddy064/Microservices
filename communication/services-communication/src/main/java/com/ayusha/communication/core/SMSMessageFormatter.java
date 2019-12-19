package com.ayusha.communication.core;

import java.util.HashMap;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Author 
 * Date:01-Aug-2019 
 * Communication Service Methods
 *
 */
@Component
public class SMSMessageFormatter implements IMessageFormatter {

	/**
	 * default constructor
	 */
	public SMSMessageFormatter() {

	}

	/**
	 * formats message
	 */
	public String format(HashMap hashMapMessageProps) throws NotificationOperationException {
		return "";
	}
}
