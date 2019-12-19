package com.ayusha.general.services.data.model;

public class ClientDetailsDataModel {

	private int id;

	private String clientId = "";

	/** name **/
	private String contactName = "";

	/** emailAddress **/
	private String contactEmail = "";

	/** landline **/
	private String contactLandline = "";

	/** mobileNumber **/
	private String contactMobile = "";

	/** designation **/
	private String contactDesignation = "";

	/** default constructor **/
	public ClientDetailsDataModel() {

	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the contactLandline
	 */
	public String getContactLandline() {
		return contactLandline;
	}

	/**
	 * @param contactLandline the contactLandline to set
	 */
	public void setContactLandline(String contactLandline) {
		this.contactLandline = contactLandline;
	}

	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * @return the contactDesignation
	 */
	public String getContactDesignation() {
		return contactDesignation;
	}

	/**
	 * @param contactDesignation the contactDesignation to set
	 */
	public void setContactDesignation(String contactDesignation) {
		this.contactDesignation = contactDesignation;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
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

}
