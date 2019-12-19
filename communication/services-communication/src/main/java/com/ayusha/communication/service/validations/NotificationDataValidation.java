package com.ayusha.communication.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.communication.data.models.NotificationDataModel;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author author1
 * Date : 01-Aug-2019
 * Ticket Model class
 * Defines the ticket service data validation methods
 *
 */
@Component
public class NotificationDataValidation {
	
	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(NotificationDataValidation.class);
	
	/**
	 * default constructor
	 */
	public NotificationDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(NotificationDataModel notificationDataModel) throws InvalidServiceRequestException{
		
		if(notificationDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(notificationDataModel.getNotificationType()==null || (notificationDataModel.getNotificationType().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
