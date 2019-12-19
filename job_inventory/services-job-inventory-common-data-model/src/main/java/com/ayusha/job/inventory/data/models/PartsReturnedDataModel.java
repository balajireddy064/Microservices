package com.ayusha.job.inventory.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author author1
 * Date : 01-Aug-2019
 * Job Notes
 */
public class PartsReturnedDataModel {
	
	/** id **/
	private int id;
	
	/** job id **/
	private String jobId;
	
	/** lst of parts returned **/
	private List<PartReturnedDataModel> lstPartsReturnedDataModel = new ArrayList();
	/**
	 * default constructor
	 */
	public PartsReturnedDataModel() {
		
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
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	/**
	 * @return the lstPartsReturnedDataModel
	 */
	public List<PartReturnedDataModel> getLstPartsReturnedDataModel() {
		return lstPartsReturnedDataModel;
	}
	/**
	 * @param lstPartsReturnedDataModel the lstPartsReturnedDataModel to set
	 */
	public void setLstPartsReturnedDataModel(List<PartReturnedDataModel> lstPartsReturnedDataModel) {
		this.lstPartsReturnedDataModel = lstPartsReturnedDataModel;
	}
}
