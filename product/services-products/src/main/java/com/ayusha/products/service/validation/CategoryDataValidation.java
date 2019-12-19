package com.ayusha.products.service.validation;

import org.springframework.stereotype.Component;

import com.ayusha.products.data.models.CategoryDataModel;
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
public class CategoryDataValidation {
	
	
	/**
	 * default constructor
	 */
	public CategoryDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(CategoryDataModel categoryDataModel) throws InvalidServiceRequestException{
		
		if(categoryDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(categoryDataModel.getName()==null || (categoryDataModel.getName().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
