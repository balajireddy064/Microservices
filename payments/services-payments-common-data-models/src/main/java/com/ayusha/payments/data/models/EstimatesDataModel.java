package com.ayusha.payments.data.models;

import java.util.ArrayList;
import java.util.List;

public class EstimatesDataModel {

	/** id **/
	private int id;

	/** EstimateDataModel **/
	private List<EstimateDataModel> lstEstimates;

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
	 * @return the lstEstimates
	 */
	public List<EstimateDataModel> getLstEstimates() {
		return lstEstimates;
	}

	/**
	 * @param lstEstimates the lstEstimates to set
	 */
	public void setLstEstimates(List<EstimateDataModel> lstEstimates) {
		this.lstEstimates = lstEstimates;
	}

	public EstimatesDataModel() {
		
		// TODO Auto-generated constructor stub
	}

	
}
