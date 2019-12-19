package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class SubCategorysDataModel {

	
	/** categoryId **/
	private String categoryId;
	
	/** lstSubCategoryDataModel **/
	private List<SubCategoryDataModel> lstSubCategoryDataModel;
	
	/**
	 * default constructor
	 */
	public SubCategorysDataModel() {
		
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the lstSubCategoryDataModel
	 */
	public List<SubCategoryDataModel> getLstSubCategoryDataModel() {
		return lstSubCategoryDataModel;
	}

	/**
	 * @param lstSubCategoryDataModel the lstSubCategoryDataModel to set
	 */
	public void setLstSubCategoryDataModel(List<SubCategoryDataModel> lstSubCategoryDataModel) {
		this.lstSubCategoryDataModel = lstSubCategoryDataModel;
	}
}
