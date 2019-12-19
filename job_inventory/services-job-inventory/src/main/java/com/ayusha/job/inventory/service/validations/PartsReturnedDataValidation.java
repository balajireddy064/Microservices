package com.ayusha.job.inventory.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.job.inventory.data.models.PartReturnedDataModel;
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
public class PartsReturnedDataValidation {
	
	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(PartsReturnedDataValidation.class);
	
	/**
	 * default constructor
	 */
	public PartsReturnedDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(PartReturnedDataModel partReturnedDataModel) throws InvalidServiceRequestException{
		
		if(partReturnedDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(partReturnedDataModel.getJobId()==null || (partReturnedDataModel.getJobId().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}else if(partReturnedDataModel.getPartId()==null || (partReturnedDataModel.getPartId().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid issue request data");
		}
		return true;
	}

}
