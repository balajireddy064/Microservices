package com.ayusha.products.service.validation;

import org.springframework.stereotype.Component;

import com.ayusha.products.data.models.ServiceTypeDataModel;
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
public class ServiceTypeDataValidation {
	
	
	/**
	 * default constructor
	 */
	public ServiceTypeDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(ServiceTypeDataModel serviceTypeDataModel) throws InvalidServiceRequestException{
		
		if(serviceTypeDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(serviceTypeDataModel.getType()==null || (serviceTypeDataModel.getType().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
