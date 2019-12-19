package com.ayusha.products.service.validation;

import org.springframework.stereotype.Component;

import com.ayusha.products.data.models.MakeDataModel;
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
public class MakeDataValidation {
	
	
	/**
	 * default constructor
	 */
	public MakeDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(MakeDataModel makeDataModel) throws InvalidServiceRequestException{
		
		if(makeDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(makeDataModel.getName()==null || (makeDataModel.getName().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
