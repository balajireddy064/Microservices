package com.ayusha.payments.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Ticket Model
 *
 */
@Entity
@Table(name = "cash_receipt")
public class CashReceiptEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** jobCode **/
	@Column(name = "job_code")
	private String jobCode = "";

	/** generatedOn **/
	@Column(name = "generated_on")
	private String generatedOn = "";

	/** couponCode **/
	@Column(name = "coupon_code")
	private String couponCode = "";

	/** subTotal **/
	@Column(name = "subTotal")
	private double subTotal;

	/** grandTotal **/
	@Column(name = "grandTotal")
	private double grandTotal;

	/** paidStatus **/
	@Column(name = "paid_status")
	private String paidStatus = "";

	/** requestedBy **/
	@Column(name = "requested_by")
	private String requestedBy = "";

	/**
	 * default constructor
	 */
	public CashReceiptEntity() {

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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}