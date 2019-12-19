package com.ayusha.general.services.data.model;

import java.util.List;

/**
 * 
 * @author author 10-Aug-1029 State data
 *
 */
public class CountriesDataModel {

	/** id **/
	private int id;

	/** state list **/
	private List<CountryDataModel> lstCountryDataModel;

	private List<StatesDataModel> lstStatesDataModel;

	/**
	 * default constructor
	 */
	public CountriesDataModel() {

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
	 * @return the lstCountryDataModel
	 */
	public List<CountryDataModel> getLstCountryDataModel() {
		return lstCountryDataModel;
	}

	/**
	 * @param lstCountryDataModel the lstCountryDataModel to set
	 */
	public void setLstCountryDataModel(List<CountryDataModel> lstCountryDataModel) {
		this.lstCountryDataModel = lstCountryDataModel;
	}

	/**
	 * @return the lstStatesDataModel
	 */
	public List<StatesDataModel> getLstStatesDataModel() {
		return lstStatesDataModel;
	}

	/**
	 * @param lstStatesDataModel the lstStatesDataModel to set
	 */
	public void setLstStatesDataModel(List<StatesDataModel> lstStatesDataModel) {
		this.lstStatesDataModel = lstStatesDataModel;
	}
}
