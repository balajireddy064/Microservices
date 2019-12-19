package com.ayusha.ticket.data.models;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Ticket Data Model class
 *
 */
public class ServiceTypeDataModel {
	
	/** id **/
	private int id;
	
	/**type**/
	private String type;
	
	/** code **/
	private String code;
	
	/**
	 * default constructor
	 */
	public ServiceTypeDataModel() {
		
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

}
