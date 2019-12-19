package com.ayusha.products.service.validation;

import org.springframework.stereotype.Component;

import com.ayusha.products.data.models.SubCategoryDataModel;
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
public class SubCategoryDataValidation {
	
	
	/**
	 * default constructor
	 */
	public SubCategoryDataValidation() {
		
	}
	
	/**
	 * validate
	 */
	public boolean validate(SubCategoryDataModel subCategoryDataModel) throws InvalidServiceRequestException{
		
		if(subCategoryDataModel==null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		}else if(subCategoryDataModel.getName()==null || (subCategoryDataModel.getName().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}else if(subCategoryDataModel.getCategoryId()==null || (subCategoryDataModel.getCategoryId().trim().length()<1)) {
			throw new InvalidServiceRequestException("Invalid job notes request data");
		}
		return true;
	}

}
