package com.ayusha.user.data.models;

import java.util.List;

/**
 * 
 * @author Finch Date:01-Aug-2019 User Data Model class
 *
 */
public class LocationsUsersDataModel {

	/** locationId **/
	private String locationId;

	/** lstUsers **/
	private List<String> lstUsers;
	


	/** role **/
	private String role;
	
	
	

	/**
	 * default constructor
	 */
	public LocationsUsersDataModel() {

	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the lstUsers
	 */
	public List<String> getLstUsers() {
		return lstUsers;
	}

	/**
	 * @param lstUsers the lstUsers to set
	 */
	public void setLstUsers(List<String> lstUsers) {
		this.lstUsers = lstUsers;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
