package com.ayusha.job.specification;

import java.util.List;

import com.ayusha.user.data.models.UserDataModel;

public class ListOfObjectBody {

	private List<UserDataModel> lstUserDataModel;

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
