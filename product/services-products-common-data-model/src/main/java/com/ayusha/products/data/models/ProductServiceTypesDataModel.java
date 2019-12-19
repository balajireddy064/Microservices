package com.ayusha.products.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class ProductServiceTypesDataModel {
	
	/**  id **/
	private int id;
	
	/**productId **/
	private String productId;
	
	/** lstServiceTypes**/
	private List<ServiceTypeDataModel> lstServiceTypes = new ArrayList();
	
	/**
	 * default constructor
	 */
	public ProductServiceTypesDataModel() {
		
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
	 * @return the lstServiceTypes
	 */
	public List<ServiceTypeDataModel> getLstServiceTypes() {
		return lstServiceTypes;
	}

	/**
	 * @param lstServiceTypes the lstServiceTypes to set
	 */
	public void setLstServiceTypes(List<ServiceTypeDataModel> lstServiceTypes) {
		this.lstServiceTypes = lstServiceTypes;
	}
}
