package com.ayusha.products.service.validation;

import org.springframework.stereotype.Component;

import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;

/**
 * 
 * @author author1
 * Date : 01-Aug-2019
 * Ticket Model class
 * Defines the ticket service data validation methods
 *
 */
@Component
public class ModelDataValidation {
	
	
	/**
	 * default constructor
	 */
	public ModelDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(ModelDataModel modelDataModel) throws InvalidServiceRequestException{
		
		if(modelDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(modelDataModel.getName()==null || (modelDataModel.getName().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
