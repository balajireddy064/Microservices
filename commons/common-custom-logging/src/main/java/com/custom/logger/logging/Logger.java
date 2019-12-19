package com.custom.logger.logging;

/**
* This is the actual logger, which writes the logs to the configured medium.
*
* @author  Nagendra Kumar
* @version 1.0
* @since   2019-05-03 
*/
public class Logger {
	
	/** class to which the logger is attached **/
	private Class loggerClass = null;
	
	/** seperator **/
	private final String SEPERATOR = "  ";
	
	/** Format **/
	private final String LOG_FORMAT= "$TIMESTAMP"+SEPERATOR+"$CLASSNAME"+SEPERATOR+"$ACTION"+SEPERATOR+"$MESSAGETYPE"+SEPERATOR+"#MESSAGE";
	
	/** message **/
	private String message = "";
	
	/** print stream */

	/**
	 * default constructor
	 */
	public Logger(Class loggerClass) {
		this.loggerClass = loggerClass;
		message = LOG_FORMAT;
		message = message.replaceAll("$CLASSNAME", loggerClass.getName());
	}
	
	/**
	 * log info
	 */
	public void info(String message) {
		message = message.replaceAll("$TIMESTAMP", ""+System.currentTimeMillis());
		message = message.replaceAll("$ACTION", " ");
		message = message.replaceAll("$MESSAGETYPE", "INFO");
		message = message.replaceAll("$MESSAGE", message);
		System.out.println(message);
	}
	
	/**
	 * log info
	 */
	public void debug(String message) {
		
		message = message.replaceAll("$TIMESTAMP", ""+System.currentTimeMillis());
		message = message.replaceAll("$ACTION", " ");
		message = message.replaceAll("$MESSAGETYPE", "DEBUG");
		message = message.replaceAll("$MESSAGE", message);
		System.out.println(message);
		
	}
	
	/**
	 * log info
	 */
	public void exception(Exception exception) {
		message = message.replace("$TIMESTAMP", ""+System.currentTimeMillis());
		message = message.replace("$ACTION", " ");
		message = message.replace("$MESSAGETYPE", "EXCEPTION");
		message = message.replace("$MESSAGE", exception.getMessage());
		System.out.println(message);
	}

}
