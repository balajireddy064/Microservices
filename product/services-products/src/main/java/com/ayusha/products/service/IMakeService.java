package com.ayusha.products.service;

import java.util.List;

import com.ayusha.products.data.models.MakeDataModel;
import com.ayusha.products.data.models.MakeDataModels;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Service Methods
 */
public interface IMakeService {

	/** getMake **/
	public MakeDataModel getMake(String id) throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** getAllMake **/
	public List<MakeDataModel> getAllMake() throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** updateMake **/
	public MakeDataModel updateMake(MakeDataModel makeDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** add **/
	public MakeDataModel add(MakeDataModel makeDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get By make Name **/
	public MakeDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

}
