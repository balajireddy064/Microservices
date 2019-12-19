package com.ayusha.payments.data.models;

public class FinalReport {
	/** date **/
	private String date;
	/** jobCode **/
	private String jobCode;
	/** customerName **/
	private String customerName;
	/** statusId **/
	private String statusId;
	/** EstimateAmount **/
	private Double EstimateAmount;
	/** InvoiceAmount **/
	private Double InvoiceAmount;
	/** ServiceEngineer **/
	private String ServiceEngineer;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the estimateAmount
	 */
	public Double getEstimateAmount() {
		return EstimateAmount;
	}

	/**
	 * @param estimateAmount the estimateAmount to set
	 */
	public void setEstimateAmount(Double estimateAmount) {
		EstimateAmount = estimateAmount;
	}

	/**
	 * @return the invoiceAmount
	 */
	public Double getInvoiceAmount() {
		return InvoiceAmount;
	}

	/**
	 * @param invoiceAmount the invoiceAmount to set
	 */
	public void setInvoiceAmount(Double invoiceAmount) {
		InvoiceAmount = invoiceAmount;
	}

	/**
	 * @return the serviceEngineer
	 */
	public String getServiceEngineer() {
		return ServiceEngineer;
	}

	/**
	 * @param serviceEngineer the serviceEngineer to set
	 */
	public void setServiceEngineer(String serviceEngineer) {
		ServiceEngineer = serviceEngineer;
	}

}
