package com.ayusha.job.data.models;

import java.util.List;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Job Data Model class
 */
public class JobsDataModel {

	/** id **/
	private int id;
	
	/** jobs **/
	private List<JobDataModel> lstJobModel;
	/**
	 * default constructor
	 */
	public JobsDataModel() {
		
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
	 * @return the lstJobModel
	 */
	public List<JobDataModel> getLstJobModel() {
		return lstJobModel;
	}
	/**
	 * @param lstJobModel the lstJobModel to set
	 */
	public void setLstJobModel(List<JobDataModel> lstJobModel) {
		this.lstJobModel = lstJobModel;
	}
}
