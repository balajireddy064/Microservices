package com.ayusha.job.inventory.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.job.inventory.data.models.PartIssuedDataModel;
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
public class PartsIssuedDataValidation {
	
	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(PartsIssuedDataValidation.class);
	
	/**
	 * default constructor
	 */
	public PartsIssuedDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(PartIssuedDataModel partIssuedDataModel) throws InvalidServiceRequestException{
		
		if(partIssuedDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(partIssuedDataModel.getJobId()==null || (partIssuedDataModel.getJobId().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}else if(partIssuedDataModel.getPartId()==null || (partIssuedDataModel.getPartId().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid issue request data");
		}
		return true;
	}

}
