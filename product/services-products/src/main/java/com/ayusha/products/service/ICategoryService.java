package com.ayusha.products.service;

import java.util.List;

import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Product Service Methods
 */

public interface ICategoryService {
	
	/**getCategory  **/
	public CategoryDataModel getCategory(String id) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** getAllCategory **/
	public List<CategoryDataModel> getAllCategory() throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/**updateCategory  **/
	public CategoryDataModel updateCategory(CategoryDataModel categoryDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** add **/
	public CategoryDataModel add(CategoryDataModel categoryDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	public CategoryDataModel getByName(String name)throws DataPersistenceOperationException,InvalidServiceRequestException;	


}
