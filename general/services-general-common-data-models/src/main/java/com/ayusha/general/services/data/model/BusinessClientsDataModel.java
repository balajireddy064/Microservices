package com.ayusha.general.services.data.model;

import java.util.List;

public class BusinessClientsDataModel {

	private List<BusinessClientDataModel> lstClientsDataModel;

	private List<ClientDetailsDataModel> lstClientDetailsDataModels;

	

	public BusinessClientsDataModel() {

	}

	/**
	 * @return the lstClientsDataModel
	 */
	public List<BusinessClientDataModel> getLstClientsDataModel() {
		return lstClientsDataModel;
	}

	/**
	 * @param lstClientsDataModel the lstClientsDataModel to set
	 */
	public void setLstClientsDataModel(List<BusinessClientDataModel> lstClientsDataModel) {
		this.lstClientsDataModel = lstClientsDataModel;
	}
	/**
	 * @return the lstClientDetailsDataModels
	 */
	public List<ClientDetailsDataModel> getLstClientDetailsDataModels() {
		return lstClientDetailsDataModels;
	}

	/**
	 * @param lstClientDetailsDataModels the lstClientDetailsDataModels to set
	 */
	public void setLstClientDetailsDataModels(List<ClientDetailsDataModel> lstClientDetailsDataModels) {
		this.lstClientDetailsDataModels = lstClientDetailsDataModels;
	}
}
