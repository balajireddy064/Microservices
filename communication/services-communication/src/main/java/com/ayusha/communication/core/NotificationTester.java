package com.ayusha.communication.core;

import java.util.HashMap;

/**
 * 
 * @author Author Date:01-Aug-2019 Communication Service Methods
 *
 */
public class NotificationTester {

	/**
	 * default constructor
	 */
	public NotificationTester() {

	}

	/**
	 * test sms
	 */
	public void testSMS() {
		HashMap hashMapMessageProps = null;
		NotificationSender notificationSender = null;

		try {
			hashMapMessageProps = new HashMap();
			hashMapMessageProps.put("TO", "+918904173832");
			hashMapMessageProps.put("$serviceRequest", "GREETINGS FROM AUSHA");
			hashMapMessageProps.put("$serviceEngineer", "John Abrham");
			notificationSender = new NotificationSender();
			notificationSender.notify(hashMapMessageProps, "sms", "smsTicketLoggedFormatter");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test sms
	 */
	public void testEmail() {
		HashMap hashMapMessageProps = null;
		NotificationSender notificationSender = null;

		try {
			hashMapMessageProps = new HashMap();
			hashMapMessageProps.put("TO", "dvsnkumar72@gmail.com");
			hashMapMessageProps.put("$serviceRequest", "GREETINGS FROM AUSHA");
			hashMapMessageProps.put("$serviceEngineer", "John Abrham");
			hashMapMessageProps.put("$Customer", "John Abrham");
			hashMapMessageProps.put("SUBJECT", "testing");
			notificationSender = new NotificationSender();
			notificationSender.notify(hashMapMessageProps, "email", "emailTicketLoggedFormatter_ticketcreated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * main method
	 */
	public static void main(String[] args) {
		NotificationTester notificationTester = new NotificationTester();
		notificationTester.testSMS();
	}
}
