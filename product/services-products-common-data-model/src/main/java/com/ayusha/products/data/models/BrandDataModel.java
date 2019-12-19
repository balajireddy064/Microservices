package com.ayusha.products.data.models;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class BrandDataModel {

	/** id **/
	private int id;
	
	/** type**/
	private String type;
	
	/** description **/
	private String description;
	
	/**makeId  **/
	private String makeId;
	/**
	 * default constructor
	 */
	public BrandDataModel() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
