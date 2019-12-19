package com.ayusha.tickets.service;

import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.tickets.entity.ServiceTypeEntity;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Ticket Service Methods
 */
public interface IServiceTypeService {
	
	/**getServiceTypeByCode **/
	public ServiceTypeEntity getServiceTypeByCode(String code) throws DataPersistenceOperationException,InvalidServiceRequestException,ResourceNotFoundException,Exception;
		
}
