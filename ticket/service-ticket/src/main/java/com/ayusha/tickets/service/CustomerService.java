package com.ayusha.tickets.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.ticket.data.models.CustomerDataModel;
import com.ayusha.tickets.entity.CustomerEntity;
import com.ayusha.tickets.entity.TicketEntity;
import com.ayusha.tickets.repository.ICustomerRepository;
import com.ayusha.tickets.service.validations.CustomerDataValidation;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Service Methods
 * 
 *         1. Recording (single): a. Persisting in DB b. on Success - sending an
 *         email/sms to customer c. assigning service invoking
 * 
 *         2. Update: a. On change of status - sending an email/sms notification
 * 
 *         3. Batch Recording: a. Persisting in DB b. on Success - sending an
 *         email/sms to customer -seggregating and sending an single email c.
 *         assigning service invoking - Individually
 * 
 *         4. Search: a. search based on date, user, customer,logged date,
 *         issue,servicetype,serialnumber
 * 
 *         5. Sorting: a. soring based on logged date,status,servicetype (ASC |
 *         DSC)
 */
@Service
public class CustomerService implements ICustomerService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(CustomerService.class);

	/** iCustomerRepository **/
	@Autowired
	private ICustomerRepository iCustomerRepository;

	/** customerDataValidation **/
	private CustomerDataValidation customerDataValidation;

	/**
	 * default constructor
	 */
	public CustomerService() {
		LOG.info("Ticket Service Constructor");
		customerDataValidation = new CustomerDataValidation();
	}

	/**
	 * add
	 */
	public CustomerDataModel add(CustomerDataModel customerDataModel)
			throws ResourceNotFoundException, DataPersistenceOperationException, InvalidServiceRequestException {
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/YY-HH:mm:ss");
		Date date = new Date();

		LOG.info("In adding a new Customer -Start");
		/**
		 * 1. Validate the data, throw exception if validation fails. 2. Save the ticket
		 * data into DB 3. Send a event to the event handler
		 */
		CustomerEntity customerEntity = new CustomerEntity();
		// customerEntity.setCreatedOn(dateFormat1.format(date));
		populateCustomerEntity(customerEntity, customerDataModel);
		iCustomerRepository.save(customerEntity);
		LOG.info("In adding a new Customer -End");
		return customerDataModel;
	}

	/**
	 * update
	 */
	public CustomerDataModel update(CustomerDataModel customerDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("In adding a new Ticket Update -Start");

		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/YY-HH:mm:ss");
		Date date = new Date();

		CustomerEntity customerEntity = new CustomerEntity();
		populateCustomerEntity(customerEntity, customerDataModel);
		// customerEntity.setCreatedOn(dateFormat1.format(date));
		iCustomerRepository.save(customerEntity);
		LOG.info("In adding a new Ticket Update -End");
		return customerDataModel;
	}

	/**
	 * @method populateCustomerEntity
	 * @param customerEntity
	 * @param customerDataModel
	 */
	private void populateCustomerEntity(CustomerEntity customerEntity, CustomerDataModel customerDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {

		customerEntity.setCustomerId(customerDataModel.getCustomerId());
		customerEntity.setCustomerName(customerDataModel.getCustomerName());
		customerEntity.setAddress1(customerDataModel.getAddress1());
		customerEntity.setAddress2(customerDataModel.getAddress2());
		customerEntity.setStreet(customerDataModel.getStreet());
		customerEntity.setState(customerDataModel.getState());
		customerEntity.setCity(customerDataModel.getCity());
		customerEntity.setPinCode(customerDataModel.getPinCode());
		customerEntity.setEmail(customerDataModel.getEmail());
		customerEntity.setContactNumber(customerDataModel.getContactNumber());
	}

	/**
	 * getCustomerByEmailId
	 */
	public CustomerEntity getCustomerByEmailId(String emailId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		CustomerEntity customer = iCustomerRepository.findCustomerByEmailId(emailId);

		return customer;
	}

	/**
	 * getCustomerByPhoneNumber
	 */
	public CustomerEntity getCustomerByPhoneNumber(String mobileNumber) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		CustomerEntity customer = iCustomerRepository.findCustomerByMobileNumber(mobileNumber);

		return customer;
	}

	/**
	 * getCustomerByCustomerCode
	 */

	/** find ticket by id **/
	public CustomerDataModel getCustomerByCustomerCode(String customerId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		CustomerEntity customerEntity = iCustomerRepository.findCustomerByCustomerCode(customerId);
		CustomerDataModel customerDataModel = new CustomerDataModel();

		if (customerEntity == null) {
			LOG.info("No Data Found for the ticket id " + customerId);
			throw new ResourceNotFoundException("No Data Found for the ticket id " + customerId);
		} else {

			customerDataModel.setCustomerId(customerEntity.getCustomerId());
			customerDataModel.setCustomerName(customerEntity.getCustomerName());
			customerDataModel.setAddress1(customerEntity.getAddress1());
			customerDataModel.setAddress2(customerEntity.getAddress2());
			customerDataModel.setStreet(customerEntity.getStreet());
			customerDataModel.setState(customerEntity.getState());
			customerDataModel.setCity(customerEntity.getCity());
			customerDataModel.setPinCode(customerEntity.getPinCode());
			customerDataModel.setEmail(customerEntity.getEmail());
			customerDataModel.setContactNumber(customerEntity.getContactNumber());
			customerDataModel.setAlternateContact(customerEntity.getAlternateContact());
		}
		return customerDataModel;
	}

}
