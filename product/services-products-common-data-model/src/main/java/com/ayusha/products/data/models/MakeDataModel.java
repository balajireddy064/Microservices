package com.ayusha.products.data.models;

/**
 * 
 * @author Finch Date:01-Aug-2019 Product and supported service types
 *
 */
public class MakeDataModel {

	/** id **/
	private int id;

	/** name **/
	private String name;

	/** description **/
	private String description;

	/** makeId **/
	private String makeId;

	/**
	 * default constructor
	 */
	public MakeDataModel() {

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
