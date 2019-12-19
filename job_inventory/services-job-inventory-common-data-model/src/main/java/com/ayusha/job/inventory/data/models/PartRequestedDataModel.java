package com.ayusha.job.inventory.data.models;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Job Notes
 */
public class PartRequestedDataModel {

	/** id **/
	private int id;

	/** jobid **/
	private String jobId;

	/** status **/
	private String status;

	/** availableQuantity **/
	private String availableQuantity;

	/** partid **/
	private String partid;

	/** requestedQuantity **/
	private int requestedQuantity;

	/** type **/
	private String type;
	/** requestedOn **/
	private String requestedOn;

	/** media **/
	private byte[] media;

	public String getPartid() {
		return partid;
	}

	public void setPartid(String partid) {
		this.partid = partid;
	}

	/**
	 * default constructor
	 */
	public PartRequestedDataModel() {

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the availableQuantity
	 */
	public String getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	/**
	 * @return the requestedQuantity
	 */
	public int getRequestedQuantity() {
		return requestedQuantity;
	}

	/**
	 * @param requestedQuantity the requestedQuantity to set
	 */
	public void setRequestedQuantity(int requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the requestedOn
	 */
	public String getRequestedOn() {
		return requestedOn;
	}

	/**
	 * @param requestedOn the requestedOn to set
	 */
	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
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
