package com.ayusha.products.service;

import java.util.List;

import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.products.data.models.ModelsDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Service Methods
 */

public interface IModelService {

	/** getModels **/
	public List<ModelDataModel> getModels(String makeId, String categoryId, String subCategoryId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** updateModel **/
	public ModelDataModel updateModel(ModelDataModel modelDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** add **/
	public ModelDataModel add(ModelDataModel ModelDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get By modelName **/
	public ModelDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

	/** get All **/
	public List<ModelDataModel> getAllModel() throws DataPersistenceOperationException, InvalidServiceRequestException;

}
