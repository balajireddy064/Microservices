package com.ayusha.ticket.data.models;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Ticket Data Model class
 *
 */
public class TicketUpdateDataModel {
	
	/**tktId  **/
	private String tktId;
	
	/** issue **/
	private String issue;
	
	/** description **/
	private String description;
	
	/**seviceTypeId  **/
	private String seviceTypeId;
	
	/**statusId**/
	private String statusId;
	
	/**serialNumber  **/
	private String serialNumber;
	
	
	/**SERVICETYPE **/
	private String type="SERVICETYPE"; 
	

	/**
	 * default constructor
	 */
	public TicketUpdateDataModel() {
		
	}


	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}


	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}


	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}


	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	/**
	 * @return the tktId
	 */
	public String getTktId() {
		return tktId;
	}


	/**
	 * @param tktId the tktId to set
	 */
	public void setTktId(String tktId) {
		this.tktId = tktId;
	}


	/**
	 * @return the issue
	 */
	public String getIssue() {
		return issue;
	}


	/**
	 * @param issue the issue to set
	 */
	public void setIssue(String issue) {
		this.issue = issue;
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


	/**
	 * @return the seviceTypeId
	 */
	public String getSeviceTypeId() {
		return seviceTypeId;
	}


	/**
	 * @param seviceTypeId the seviceTypeId to set
	 */
	public void setSeviceTypeId(String seviceTypeId) {
		this.seviceTypeId = seviceTypeId;
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
