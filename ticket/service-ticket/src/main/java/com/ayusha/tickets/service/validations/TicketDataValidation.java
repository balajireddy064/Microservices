package com.ayusha.tickets.service.validations;

import org.springframework.stereotype.Component;

import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.tickets.entity.TicketEntity;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service data validation methods
 *
 */
@Component
public class TicketDataValidation {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(TicketDataValidation.class);

	/**
	 * default constructor
	 */
	public TicketDataValidation() {

	}

	/**
	 * validate
	 */
	public boolean validate(TicketEntity ticket) throws InvalidServiceRequestException {

		if (ticket == null) {
			throw new InvalidServiceRequestException("Invalid service request data");
		} else if (ticket.getCustomerId() == null || (ticket.getCustomerId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid customer request data");
		} else if (ticket.getLoggedon() == null || (ticket.getLoggedon().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid loggedby request data");
		} else if (ticket.getProductId() == null || (ticket.getProductId().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid productid request data");
		}

		return true;
	}

	public boolean validation(TicketDataModel ticketDataModel) throws InvalidServiceRequestException {

		if (ticketDataModel.getCallType() == null || ticketDataModel.getCallType().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid Call Type");
		} else if (ticketDataModel.getCallType() == null || ticketDataModel.getBrand().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid Brand ");
		} else if (ticketDataModel.getCategory() == null
				|| (ticketDataModel.getCategory() != null && ticketDataModel.getCategory().trim().length() < 1)) {
			throw new InvalidServiceRequestException("Invalid  Category " + ticketDataModel.getCategory());
		} else if (ticketDataModel.getSubCategory().trim().length() < 1 || ticketDataModel.getSubCategory() == null) {
			throw new InvalidServiceRequestException("Invalid  SubCategory");
		} else if (ticketDataModel.getModel().trim().length() < 1 || ticketDataModel.getModel() == null) {
			throw new InvalidServiceRequestException("Invalid model");
		} else if (ticketDataModel.getDescription() == null || ticketDataModel.getDescription().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid Description");
		} else if (ticketDataModel.getWarranty() == null || ticketDataModel.getWarranty().trim().length() < 1) {
			throw new InvalidServiceRequestException(" Invalid warranty");
		} else if (ticketDataModel.getSerialNumber() == null || ticketDataModel.getSerialNumber().trim().length() < 1) {
			throw new InvalidServiceRequestException(" Invalid Serial Number  ");
		} else if (ticketDataModel.getCustomerDataModel().getCustomerName() == null
				|| ticketDataModel.getCustomerDataModel().getCustomerName().trim().length() < 1) {
			throw new InvalidServiceRequestException(" Invalid CustomerName");
		} else if (ticketDataModel.getCustomerDataModel().getAddress1() == null
				|| ticketDataModel.getCustomerDataModel().getAddress1().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid Customer address ");
		} else if (ticketDataModel.getCustomerDataModel().getAddress2() == null
				|| ticketDataModel.getCustomerDataModel().getAddress2().trim().length() < 1) {
			throw new InvalidServiceRequestException(" Invalid Customer address");
		} else if (ticketDataModel.getCustomerDataModel().getStreet() == null
				|| ticketDataModel.getCustomerDataModel().getStreet().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid  street");
		} else if (ticketDataModel.getCustomerDataModel().getState() == null
				|| ticketDataModel.getCustomerDataModel().getState().trim().length() < 1
				) {
			throw new InvalidServiceRequestException("Invalid state");
		} else if ( ticketDataModel.getCustomerDataModel().getCity() == null
				|| ticketDataModel.getCustomerDataModel().getCity().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid city");
		} else if (ticketDataModel.getCustomerDataModel().getEmail() == null
				|| ticketDataModel.getCustomerDataModel().getEmail().trim().length() < 1) {
			throw new InvalidServiceRequestException("Invalid state");
		} else if (ticketDataModel.getCustomerDataModel().getPinCode() == null
				|| ticketDataModel.getCustomerDataModel().getPinCode().trim().length() < 1
				) {
			throw new InvalidServiceRequestException(" Invalid pinCode ");
		} else if ( ticketDataModel.getCustomerDataModel() == null
				|| ticketDataModel.getCustomerDataModel().getContactNumber().trim().length() < 1
				) {
			throw new InvalidServiceRequestException("Invalid Contactnumber");
		}
		return true;
	}
}
