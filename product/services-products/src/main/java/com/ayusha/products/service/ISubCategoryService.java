package com.ayusha.products.service;

import java.util.List;

import com.ayusha.products.data.models.SubCategoryDataModel;
import com.ayusha.products.data.models.SubCategorysDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Product Service Methods
 */
public interface ISubCategoryService {

	/** getSubCategory **/
	public List<SubCategoryDataModel> getSubCategory(String makeId, String categoryId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** updateSubCategory **/
	public SubCategoryDataModel updateSubCategory(SubCategoryDataModel subCategoryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** add **/
	public SubCategoryDataModel add(SubCategoryDataModel subCategoryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	public SubCategoryDataModel getByName(String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	public List<SubCategoryDataModel> getAllSubCategory() throws DataPersistenceOperationException, InvalidServiceRequestException;;

}
