package com.ayusha.payments.data.models;

/**
 * 
 * @author author 10-Aug-2019
 *
 */
public class CashReceiptDataModel {

	/** id **/
	private long id;

	/** jobCode **/
	private String jobCode = "";

	/** generatedOn **/
	private String generatedOn = "";
	
	/** couponCode **/
	private String couponCode = "";
	
	/** subTotal **/
	private double subTotal;
	
	/** grandTotal **/
	private double grandTotal;
	
	/** paidStatus **/
	private String paidStatus = "";
	
	/** requestedBy **/
	private String requestedBy = "";

	/**
	 * default constructor
	 */
	public CashReceiptDataModel() {

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
	 * @return the couponCode
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * @param couponCode the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
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

	/**
	 * @return the paidStatus
	 */
	public String getPaidStatus() {
		return paidStatus;
	}

	/**
	 * @param paidStatus the paidStatus to set
	 */
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
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

}
