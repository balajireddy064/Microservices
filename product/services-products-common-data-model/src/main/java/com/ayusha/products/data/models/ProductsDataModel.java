package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class ProductsDataModel {
	
	/** id **/
	private int id;
	
	/**productId  **/
	private String productId="";
	
	/** lstProductDataModel **/
	private List<ProductDataModel> lstProductDataModel = null;
	
	/**
	 * default constructor
	 */
	public ProductsDataModel() {
		
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
	 * @return the lstProductDataModel
	 */
	public List<ProductDataModel> getLstProductDataModel() {
		return lstProductDataModel;
	}

	/**
	 * @param lstProductDataModel the lstProductDataModel to set
	 */
	public void setLstProductDataModel(List<ProductDataModel> lstProductDataModel) {
		this.lstProductDataModel = lstProductDataModel;
	}
}
