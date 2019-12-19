package com.ayusha.repair.services.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author author
 * 08-Aug-2019
 * Symptoms details
 *
 */
public class SymptomDataModel {
	
	/** id **/
	private int id;
	
	/** jobid **/
	private String jobId="";
	
	/** symptoms **/
	private String symptomId="";
	
	/** symptoms **/
	private byte[] media;
	
	/**
	 * default constructor
	 */
	public SymptomDataModel() {
		
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
	 * @return the symptomId
	 */
	public String getSymptomId() {
		return symptomId;
	}

	/**
	 * @param symptomId the symptomId to set
	 */
	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
	}

	/**
	 * @return the media
	 */
	public byte[] getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(byte[] media) {
		this.media = media;
	}
}
