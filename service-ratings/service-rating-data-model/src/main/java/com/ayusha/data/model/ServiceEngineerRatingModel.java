package com.ayusha.data.model;

public class ServiceEngineerRatingModel {
	/** id **/
	private int id;
	/** jobId **/
	private String jobId;
	/** customerName **/
	private String customerName;
	/** userId **/
	private String userId;
	/** comments **/
	private String comments;
	/** rate **/
	private double rate;
	/** serviceEngineer **/
	private String techName;

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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserId() {
		return userId;
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
	 * @return the techName
	 */
	public String getTechName() {
		return techName;
	}

	/**
	 * @param techName the techName to set
	 */
	public void setTechName(String techName) {
		this.techName = techName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
