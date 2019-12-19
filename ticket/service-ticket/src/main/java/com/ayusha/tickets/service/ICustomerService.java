package com.ayusha.tickets.service;

import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.ticket.data.models.CustomerDataModel;
import com.ayusha.tickets.entity.CustomerEntity;

/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Ticket Model class
 * Defines the ticket service methods
 *
 */
public interface ICustomerService {
	
	/**getCustomerByEmailId **/
	public CustomerEntity getCustomerByEmailId(String emailId) throws DataPersistenceOperationException,InvalidServiceRequestException,ResourceNotFoundException,Exception;
	
	/** getCustomerByCustomerCode **/
	public CustomerDataModel getCustomerByCustomerCode(String emailId) throws DataPersistenceOperationException,InvalidServiceRequestException,ResourceNotFoundException,Exception;

	/**getCustomerByPhoneNumber**/
	public CustomerEntity getCustomerByPhoneNumber(String phoneNumber) throws DataPersistenceOperationException,InvalidServiceRequestException,ResourceNotFoundException,Exception;

	/** add**/
	public CustomerDataModel add(CustomerDataModel customerDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException,ResourceNotFoundException,Exception;

	/**update **/
	public CustomerDataModel update(CustomerDataModel customerDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException,ResourceNotFoundException,Exception;

}
