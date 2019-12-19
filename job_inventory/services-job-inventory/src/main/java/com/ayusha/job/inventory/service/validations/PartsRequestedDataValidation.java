package com.ayusha.job.inventory.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.job.inventory.data.models.PartRequestedDataModel;
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
public class PartsRequestedDataValidation {
	
	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(PartsRequestedDataValidation.class);
	
	/**
	 * default constructor
	 */
	public PartsRequestedDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(PartRequestedDataModel partRequestedDataModel) throws InvalidServiceRequestException{
		
		if(partRequestedDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(partRequestedDataModel.getJobId()==null || (partRequestedDataModel.getJobId().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}else if(partRequestedDataModel.getPartid()==null || (partRequestedDataModel.getPartid().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid issue request data");
		}
		return true;
	}

}
