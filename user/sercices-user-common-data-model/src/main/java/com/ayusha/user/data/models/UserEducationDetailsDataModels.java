package com.ayusha.user.data.models;

import java.util.Date;

public class UserEducationDetailsDataModels {

	private int id;
	private String userId = "";
	private String qualification = "";
	private String passOut = "";

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
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPassOut() {
		return passOut;
	}

	public void setPassOut(String passOut) {
		this.passOut = passOut;
	}

	/**
	 * @return the passOut
	 */

}
