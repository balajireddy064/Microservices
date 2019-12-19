package com.ayusha.products.service.validation;

import org.springframework.stereotype.Component;

import com.ayusha.products.data.models.SymptomDataModel;
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
public class SymptomDataValidation {
	
	
	/**
	 * default constructor
	 */
	public SymptomDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(SymptomDataModel symptomDataModel) throws InvalidServiceRequestException{
		
		if(symptomDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(symptomDataModel.getSymptom()==null || (symptomDataModel.getSymptom().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
