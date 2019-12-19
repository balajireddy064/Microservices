package com.ayusha.general.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.general.services.data.model.BusinessClientDataModel;
import com.ayusha.general.services.data.model.ClientDetailsDataModel;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service data validation methods
 *
 */
@Component
public class BusinessClientDataValidation {
	
	/**
	 * default constructor
	 */
	public BusinessClientDataValidation() {

	}

	/**
	 * Validation of all the client details mandatory fields
	 */
	public boolean validateClientDetails(BusinessClientDataModel businessClientDataModel)
			throws InvalidServiceRequestException {

		if (businessClientDataModel == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (businessClientDataModel.getClientName() == null || (businessClientDataModel.getClientName() != null
				&& businessClientDataModel.getClientName().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid client name");
		} else if (businessClientDataModel.getClientAddress() == null
				|| (businessClientDataModel.getClientAddress() != null
						&& businessClientDataModel.getClientAddress().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid client address");
		} else if (businessClientDataModel.getCountry() == null || (businessClientDataModel.getCountry() != null
				&& businessClientDataModel.getCountry().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid country");
		} else if (businessClientDataModel.getState() == null || (businessClientDataModel.getState() != null
				&& businessClientDataModel.getState().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid state");
		} else if (businessClientDataModel.getCity() == null || (businessClientDataModel.getCity() != null
				&& businessClientDataModel.getCity().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid city");
		} else if (businessClientDataModel.getPinCode() == null || (businessClientDataModel.getPinCode() != null
				&& businessClientDataModel.getPinCode().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid pincode");
		}

		return true;
	}

	/**
	 * Validation of all the client contact details mandatory fields
	 */
	public boolean validateContactDetails(ClientDetailsDataModel clientDetailsDataModel)
			throws InvalidServiceRequestException {

		if (clientDetailsDataModel == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (clientDetailsDataModel.getContactName() == null || (clientDetailsDataModel.getContactName() != null
				&& clientDetailsDataModel.getContactName().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid contact name");
		} else if (clientDetailsDataModel.getContactEmail() == null || (clientDetailsDataModel.getContactEmail() != null
				&& clientDetailsDataModel.getContactEmail().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid contact email");
		} else if (clientDetailsDataModel.getContactMobile() == null
				|| (clientDetailsDataModel.getContactMobile() != null
						&& clientDetailsDataModel.getContactMobile().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid contact mobile");
		} else if (clientDetailsDataModel.getContactDesignation() == null
				|| (clientDetailsDataModel.getContactDesignation() != null
						&& clientDetailsDataModel.getContactDesignation().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid contact designation");
		}

		return true;
	}

}
