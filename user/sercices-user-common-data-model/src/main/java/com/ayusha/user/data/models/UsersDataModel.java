package com.ayusha.user.data.models;

import java.util.List;

/**
 * 
 * @author Finch 
 * Date:01-Aug-2019 
 * User Data Model class
 *
 */
public class UsersDataModel {

	/** id **/
	private String id;

	/** lstUserDataModel **/
	private List<UserDataModel> lstUserDataModel;

	/**
	 * default constructor
	 */
	public UsersDataModel() {

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the lstUserDataModel
	 */
	public List<UserDataModel> getLstUserDataModel() {
		return lstUserDataModel;
	}

	/**
	 * @param lstUserDataModel the lstUserDataModel to set
	 */
	public void setLstUserDataModel(List<UserDataModel> lstUserDataModel) {
		this.lstUserDataModel = lstUserDataModel;
	}

}
