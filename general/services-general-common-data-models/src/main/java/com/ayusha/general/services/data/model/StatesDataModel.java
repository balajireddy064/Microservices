package com.ayusha.general.services.data.model;

import java.util.List;

/**
 * 
 * @author author 10-Aug-1029 State data
 *
 */
public class StatesDataModel {

	/** id **/
	private int id;

	/** state list **/
	private List<StateDataModel> lstStatesDataModel;

	/**
	 * default constructor
	 */
	public StatesDataModel() {

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
	 * @return the lstStatesDataModel
	 */
	public List<StateDataModel> getLstStatesDataModel() {
		return lstStatesDataModel;
	}

	/**
	 * @param lstStatesDataModel the lstStatesDataModel to set
	 */
	public void setLstStatesDataModel(List<StateDataModel> lstStatesDataModel) {
		this.lstStatesDataModel = lstStatesDataModel;
	}

}
