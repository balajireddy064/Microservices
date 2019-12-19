package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class ItemsDataModel {
	
	/** id **/
	private int id;
	
	/**productId  **/
	private String productId;
	
	/**lstItemDetails  **/
	private List<ItemDataModel> lstItemDetails = null;
	/**
	 * default constructor
	 */
	public ItemsDataModel() {
		
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the lstItemDetails
	 */
	public List<ItemDataModel> getLstItemDetails() {
		return lstItemDetails;
	}
	/**
	 * @param lstItemDetails the lstItemDetails to set
	 */
	public void setLstItemDetails(List<ItemDataModel> lstItemDetails) {
		this.lstItemDetails = lstItemDetails;
	}
}
