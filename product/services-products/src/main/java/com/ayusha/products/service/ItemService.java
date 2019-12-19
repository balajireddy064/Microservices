package com.ayusha.products.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.products.data.models.ItemDataModel;
import com.ayusha.products.data.models.ItemsDataModel;
import com.ayusha.products.data.models.MakeDataModel;
import com.ayusha.products.data.models.MakeDataModels;
import com.ayusha.products.service.entity.ItemEntity;
import com.ayusha.products.service.entity.MakeEntity;
import com.ayusha.products.service.repository.IItemRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Product Service Methods
 */

@Service
public class ItemService implements IItemService{
	
	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(ItemService.class);
	
	/** iItemRepository **/
	@Autowired
	private IItemRepository iItemRepository;
	/**
	 * default constructor
	 */
	public ItemService() {
		
	}

	/**
	 * getItem
	 */
	public ItemDataModel getItem(String itemId) throws DataPersistenceOperationException,InvalidServiceRequestException{
		ItemDataModel itemDataModel = new ItemDataModel();
		ItemEntity itemEntity=  iItemRepository.findItemByItemId(itemId);
		
		itemDataModel.setId(Integer.parseInt(""+itemEntity.getId()));
		itemDataModel.setItemId(itemEntity.getItemId());
		itemDataModel.setRemarks(itemEntity.getRemarks());
		itemDataModel.setName(itemEntity.getName());
		itemDataModel.setProductId(itemEntity.getProductId());
		itemDataModel.setQuantity(itemEntity.getQuantity());
		itemDataModel.setProductId(itemEntity.getProductId());
		itemDataModel.setPrice(itemEntity.getPrice());
		itemDataModel.setTax(itemEntity.getTax());
		itemDataModel.setType(itemEntity.getType());
		return itemDataModel;
	}
	
	/**
	 * getItemByProductIdandType
	 */
	public ItemsDataModel getItemByProductIdandType(String productId,String type) throws DataPersistenceOperationException,InvalidServiceRequestException{
		ItemDataModel itemDataModel = new ItemDataModel();
		List<ItemEntity> lstItemEntity=  iItemRepository.findItemsByProductIdandType(productId, type);
		List<ItemDataModel> lstItemDataModel = new ArrayList();
		ItemEntity itemEntity=null;
		int size = 0;
		
		size = lstItemEntity.size();
		
		for(int index=0; index<size; index++) {
			itemDataModel = new ItemDataModel();
			itemEntity = lstItemEntity.get(index);
			
			itemDataModel.setId(Integer.parseInt(""+itemEntity.getId()));
			itemDataModel.setItemId(itemEntity.getItemId());
			itemDataModel.setRemarks(itemEntity.getRemarks());
			itemDataModel.setName(itemEntity.getName());
			itemDataModel.setProductId(itemEntity.getProductId());
			itemDataModel.setQuantity(itemEntity.getQuantity());
			itemDataModel.setProductId(itemEntity.getProductId());
			itemDataModel.setPrice(itemEntity.getPrice());
			itemDataModel.setTax(itemEntity.getTax());
			itemDataModel.setType(itemEntity.getType());
			lstItemDataModel.add(itemDataModel);
		}
		
		ItemsDataModel itemsDataModel = new ItemsDataModel();
		itemsDataModel.setProductId(productId);
		itemsDataModel.setLstItemDetails(lstItemDataModel);
		return itemsDataModel;
	}
	

	/**
	 * getItemsForProductId
	 */
	public ItemsDataModel getItemsForProductId(String productId) throws DataPersistenceOperationException,InvalidServiceRequestException{
		ItemDataModel itemDataModel = new ItemDataModel();
		List<ItemEntity> lstItemEntity=  iItemRepository.findItemsByProductId(productId);
		List<ItemDataModel> lstItemDataModel = new ArrayList();
		ItemEntity itemEntity=null;
		int size = 0;
		
		size = lstItemEntity.size();
		
		for(int index=0; index<size; index++) {
			itemDataModel = new ItemDataModel();
			itemEntity = lstItemEntity.get(index);
			
			itemDataModel.setId(Integer.parseInt(""+itemEntity.getId()));
			itemDataModel.setRemarks(itemEntity.getRemarks());
			itemDataModel.setName(itemEntity.getName());
			itemDataModel.setProductId(itemEntity.getProductId());
			itemDataModel.setQuantity(itemEntity.getQuantity());
			itemDataModel.setProductId(itemEntity.getProductId());
			itemDataModel.setPrice(itemEntity.getPrice());
			itemDataModel.setTax(itemEntity.getTax());
			itemDataModel.setItemId(itemEntity.getItemId());
			itemDataModel.setType(itemEntity.getType());
			lstItemDataModel.add(itemDataModel);
		}
		
		ItemsDataModel itemsDataModel = new ItemsDataModel();
		itemsDataModel.setProductId(productId);
		itemsDataModel.setLstItemDetails(lstItemDataModel);
		return itemsDataModel;
	}
	
	
	/**
	 * updateItem
	 */
	public ItemDataModel updateItem(ItemDataModel itemDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException{
		ItemEntity itemEntity = new ItemEntity();
		
		itemEntity.setId(Integer.parseInt(""+itemEntity.getId()));
		itemEntity.setRemarks(itemDataModel.getRemarks());
		itemEntity.setName(itemDataModel.getName());
		itemEntity.setProductId(itemDataModel.getProductId());
		itemEntity.setQuantity(itemDataModel.getQuantity());
		itemEntity.setProductId(itemDataModel.getProductId());
		itemEntity.setPrice(itemDataModel.getPrice());
		itemEntity.setTax(itemDataModel.getTax());
		itemEntity.setItemId(itemDataModel.getItemId());
		itemEntity.setType(itemDataModel.getType());
		iItemRepository.save(itemEntity);
		return itemDataModel;
	}
	/**
	 * add
	 */
	public ItemDataModel add(ItemDataModel itemDataModel) throws DataPersistenceOperationException,InvalidServiceRequestException{
			ItemEntity itemEntity = new ItemEntity();
		
		itemEntity.setId(Integer.parseInt(""+itemEntity.getId()));
		itemEntity.setRemarks(itemDataModel.getRemarks());
		itemEntity.setName(itemDataModel.getName());
		itemEntity.setProductId(itemDataModel.getProductId());
		itemEntity.setQuantity(itemDataModel.getQuantity());
		itemEntity.setProductId(itemDataModel.getProductId());
		itemEntity.setPrice(itemDataModel.getPrice());
		itemEntity.setItemId(itemDataModel.getItemId());
		itemEntity.setTax(itemDataModel.getTax());
		itemEntity.setType(itemDataModel.getType());
		iItemRepository.save(itemEntity);
		return itemDataModel;
	}
}
