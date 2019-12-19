package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class CategoryDataModels {

//	/** id **/
//	private int id;
//	
//	/** description **/
//	private String categoryId;
	
	/** description **/
	private List<CategoryDataModel> lstCategoryDataModel;
	
	/**
	 * default constructor
	 */
	public CategoryDataModels() {
		
	}

//	/**
//	 * @return the id
//	 */
//	public int getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the categoryId
//	 */
//	public String getCategoryId() {
//		return categoryId;
//	}
//
//	/**
//	 * @param categoryId the categoryId to set
//	 */
//	public void setCategoryId(String categoryId) {
//		this.categoryId = categoryId;
//	}

	/**
	 * @return the lstCategoryDataModel
	 */
	public List<CategoryDataModel> getLstCategoryDataModel() {
		return lstCategoryDataModel;
	}

	/**
	 * @param lstCategoryDataModel the lstCategoryDataModel to set
	 */
	public void setLstCategoryDataModel(List<CategoryDataModel> lstCategoryDataModel) {
		this.lstCategoryDataModel = lstCategoryDataModel;
	}
}
