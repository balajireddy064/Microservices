package com.ayusha.products.service;

import java.util.List;

import com.ayusha.products.data.models.ItemDataModel;
import com.ayusha.products.data.models.ItemsDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Product Service Methods
 */
public interface IItemService {
	
	/** getItem **/
	public ItemDataModel getItem(String itemId) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/**getItemByProductIdandType  **/
	public ItemsDataModel getItemByProductIdandType(String productId,String type) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** getItemsForProductId **/
	public ItemsDataModel getItemsForProductId(String productId) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/**updateItem  **/
	public ItemDataModel updateItem(ItemDataModel itemDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;
	
	/** add **/
	public ItemDataModel add(ItemDataModel itemDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException;


}
