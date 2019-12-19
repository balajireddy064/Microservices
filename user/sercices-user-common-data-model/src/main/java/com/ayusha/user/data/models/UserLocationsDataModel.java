package com.ayusha.user.data.models;

import java.util.List;

/**
 * 
 * @author Finch Date:01-Aug-2019 User Data Model class
 *
 */
public class UserLocationsDataModel {

	/** userId **/
	private String userId;
	/** role **/
	private String role;

	/** lstLocations **/
	private List<String> lstLocations;

	private List<String> lstUsers;

	/**
	 * default constructor
	 */

	public UserLocationsDataModel() {

	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the lstLocations
	 */
	public List<String> getLstLocations() {
		return lstLocations;
	}

	/**
	 * @param lstLocations the lstLocations to set
	 */
	public void setLstLocations(List<String> lstLocations) {
		this.lstLocations = lstLocations;
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

}
