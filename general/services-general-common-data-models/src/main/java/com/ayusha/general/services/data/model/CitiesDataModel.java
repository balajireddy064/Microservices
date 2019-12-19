package com.ayusha.general.services.data.model;

import java.util.List;

/**
 * 
 * @author author
 * 10-Aug-1029
 * State data
 *
 */
public class CitiesDataModel {
	
	/** id **/
	private int id;
	
	
	
	/** state list **/
	private List<CityDataModel> lstCitiesDataModel;
	
	/**
	 * default constructor
	 */
	public CitiesDataModel() {
		
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
	 * @return the lstCitiesDataModel
	 */
	public List<CityDataModel> getLstCitiesDataModel() {
		return lstCitiesDataModel;
	}

	/**
	 * @param lstCitiesDataModel the lstCitiesDataModel to set
	 */
	public void setLstCitiesDataModel(List<CityDataModel> lstCitiesDataModel) {
		this.lstCitiesDataModel = lstCitiesDataModel;
	}
}
