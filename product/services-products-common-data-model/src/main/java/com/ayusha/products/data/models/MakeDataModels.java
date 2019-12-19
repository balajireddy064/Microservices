package com.ayusha.products.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Product and supported service types
 *
 */
public class MakeDataModels {

//	/** id **/
//	private int id;
//	
//	/** description **/
//	private String makeId;
	
	/** description **/
	private List<MakeDataModel> lstMakeDataModel;
	
	/**
	 * default constructor
	 */
	public MakeDataModels() {
		
	}

//	/**
//	 * @return the id
//	 */
//	public int getId() {
//		return id;
//	}
//
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	/**
//	 * @return the makeId
//	 */
//	public String getMakeId() {
//		return makeId;
//	}
//
//	/**
//	 * @param makeId the makeId to set
//	 */
//	public void setMakeId(String makeId) {
//		this.makeId = makeId;
//	}

	/**
	 * @return the lstMakeDataModel
	 */
	public List<MakeDataModel> getLstMakeDataModel() {
		return lstMakeDataModel;
	}

	/**
	 * @param lstMakeDataModel the lstMakeDataModel to set
	 */
	public void setLstMakeDataModel(List<MakeDataModel> lstMakeDataModel) {
		this.lstMakeDataModel = lstMakeDataModel;
	}
}
