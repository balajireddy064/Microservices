package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date: 01-Aug-2019
 * Product and supported service types
 *
 */
public class SymptomsDataModel {
		
	/** description **/
	private String productId="";
	
	/** symptoms **/
	private List<SymptomDataModel> lstSymptomDataModel;
	
	/**
	 * default constructor
	 */
	public SymptomsDataModel() {
		
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
	 * @return the lstSymptomDataModel
	 */
	public List<SymptomDataModel> getLstSymptomDataModel() {
		return lstSymptomDataModel;
	}

	/**
	 * @param lstSymptomDataModel the lstSymptomDataModel to set
	 */
	public void setLstSymptomDataModel(List<SymptomDataModel> lstSymptomDataModel) {
		this.lstSymptomDataModel = lstSymptomDataModel;
	}
}
