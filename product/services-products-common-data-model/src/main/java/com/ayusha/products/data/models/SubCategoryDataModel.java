package com.ayusha.products.data.models;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class SubCategoryDataModel {

	/** id **/
	private int id;
	
	/** name**/
	private String name;
	
	/** makeId **/
	private String makeId;
	
	/**categoryId  **/
	private String categoryId;
	
	/**subCategoryId  **/
	private String subCategoryId;
	
	/**
	 * default constructor
	 */
	public SubCategoryDataModel() {
		
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
	 * @return the subCategoryId
	 */
	public String getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId the subCategoryId to set
	 */
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the makeId
	 */
	public String getMakeId() {
		return makeId;
	}

	/**
	 * @param makeId the makeId to set
	 */
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	
	
}
