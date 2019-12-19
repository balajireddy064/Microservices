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
public class ProductSymptomsDataModel {
	
	/**  id **/
	private int id;
	
	/** productId **/
	private String productId;
	
	/** lstServiceTypes**/
	private List<SymptomDataModel> lstServiceTypes = new ArrayList();
	
	/**
	 * default constructor
	 */
	public ProductSymptomsDataModel() {
		
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
	public List<SymptomDataModel> getLstServiceTypes() {
		return lstServiceTypes;
	}

	/**
	 * @param lstServiceTypes the lstServiceTypes to set
	 */
	public void setLstServiceTypes(List<SymptomDataModel> lstServiceTypes) {
		this.lstServiceTypes = lstServiceTypes;
	}
}
