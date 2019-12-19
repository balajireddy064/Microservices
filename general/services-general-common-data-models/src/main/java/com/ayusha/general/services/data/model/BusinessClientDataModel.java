package com.ayusha.general.services.data.model;

import java.util.List;

public class BusinessClientDataModel {

	/** id **/
	private int id;
	/** client id **/
	private String clientId = "";

	/** clientName **/
	private String clientName = "";

	/** clientAddress **/
	private String clientAddress = "";

	/** country **/
	private String country = "";

	/** state **/
	private String state = "";

	/** city **/
	private String city = "";

	/** pinCode **/
	private String pinCode = "";

	/** lstdetails **/
	private List<ClientDetailsDataModel> clientContactDetails;

//	List<BusinessClientDataModel> businessClientDataModels;

//	ClientDetailsDataModel lstClientDetailsDataModel;

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
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the clientAddress
	 */
	public String getClientAddress() {
		return clientAddress;
	}

	/**
	 * @param clientAddress the clientAddress to set
	 */
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the clientContactDetails
	 */
	public List<ClientDetailsDataModel> getClientContactDetails() {
		return clientContactDetails;
	}

	/**
	 * @param clientContactDetails the clientContactDetails to set
	 */
	public void setClientContactDetails(List<ClientDetailsDataModel> clientContactDetails) {
		this.clientContactDetails = clientContactDetails;
	}

	/**
	 * @return the clientContactDetails
	 */

	/**
	 * @return the clientContactDetails
	 */

//	/**
//	 * @return the clientContactDetails
//	 */
//	public List<ClientDetailsDataModel> getClientContactDetails() {
//		return ClientContactDetails;
//	}
//
//	/**
//	 * @param clientContactDetails the clientContactDetails to set
//	 */
//	public void setClientContactDetails(List<ClientDetailsDataModel> clientContactDetails) {
//		ClientContactDetails = clientContactDetails;

}
