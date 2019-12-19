package com.custom.logger.manager;

import com.custom.logger.logging.Logger;

/**
* This is acustom log manager, which centralizes all logging to a common class.
* This class writes to the required medium like file, console etc..
* Here the actual java logger will be configured like log4J etc..
*
* @author  Nagendra Kumar
* @version 1.0
* @since   2019-05-03 
*/
public class LogManager {
	
	/** static method 
	 * 
	 */
	public static Logger getLogger(Class oClass) {
		return new Logger(oClass);
	}

}
