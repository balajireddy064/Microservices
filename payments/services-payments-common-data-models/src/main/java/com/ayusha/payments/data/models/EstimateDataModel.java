package com.ayusha.payments.data.models;

/**
 * 
 * @author author 10-Aug-2019
 *
 */
public class EstimateDataModel {

	/** id **/
	private long id;

	/** estimateid **/
	private String estimateId = "";

	/** jobCode **/
	private String jobCode = "";

	/** generatedOn **/
	private String generatedOn = "";

	/** approvalStatus **/
	private String approvalStatus = "";

	/** requestedBy **/
	private String requestedBy = "";

	/** grandTotal **/
	private double grandTotal;

	/** constructor **/
	public EstimateDataModel() {

	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public String getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}

	/**
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}

	/**
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * @return the generatedOn
	 */
	public String getGeneratedOn() {
		return generatedOn;
	}

	/**
	 * @param generatedOn the generatedOn to set
	 */
	public void setGeneratedOn(String generatedOn) {
		this.generatedOn = generatedOn;
	}

	/**
	 * @return the approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @return the grandTotal
	 */
	public double getGrandTotal() {
		return grandTotal;
	}

	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

}
