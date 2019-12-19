package com.ayusha.general.services.data.model;

import java.util.List;

/**
 * 
 * @author author 10-Aug-1029 State data
 *
 */
public class StateDataModel {

	/** id **/
	private int id;

	/** state **/
	private String stateCode;

	/** name **/
	private String name;

	/** name **/
	private String countryCode;

	/** state list **/
	private List<CityDataModel> lstCityDataModel;

	/**
	 * default constructor
	 */
	public StateDataModel() {

	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lstCityDataModel
	 */
	public List<CityDataModel> getLstCityDataModel() {
		return lstCityDataModel;
	}

	/**
	 * @param lstCityDataModel the lstCityDataModel to set
	 */
	public void setLstCityDataModel(List<CityDataModel> lstCityDataModel) {
		this.lstCityDataModel = lstCityDataModel;
	}

}
