package com.ayusha.products.service;


import com.ayusha.products.data.models.ServiceTypeDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Product Service Methods
 */

public interface IServiceTypeService {
	
	/**getServiceType  **/
	public ServiceTypeDataModel getServiceType(String id) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** updateServiceType **/
	public ServiceTypeDataModel updateServiceType(ServiceTypeDataModel serviceTypeDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** add **/
	public ServiceTypeDataModel add(ServiceTypeDataModel serviceTypeDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;


}
