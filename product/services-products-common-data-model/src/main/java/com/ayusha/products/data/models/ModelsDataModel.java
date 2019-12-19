package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class ModelsDataModel {


	/** lstModelDataModels **/
	private List<ModelDataModel> lstModelDataModels;
	
	/**makeId  **/
	private String makeId;
	
	/**
	 * default constructor
	 */
	public ModelsDataModel() {
		
	}

	/**
	 * @return the lstModelDataModels
	 */
	public List<ModelDataModel> getLstModelDataModels() {
		return lstModelDataModels;
	}

	/**
	 * @param lstModelDataModels the lstModelDataModels to set
	 */
	public void setLstModelDataModels(List<ModelDataModel> lstModelDataModels) {
		this.lstModelDataModels = lstModelDataModels;
	}

	/**
	 * @return the makeId
	 */
	public String getMakeId() {
		return makeId;
	}

	/**
	 * @param makeId the makeId to set
	 */
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

}
