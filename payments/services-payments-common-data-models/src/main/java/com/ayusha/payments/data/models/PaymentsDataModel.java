package com.ayusha.payments.data.models;

/**
 * 
 * @author author 10-Aug-2019
 *
 */
public class PaymentsDataModel {

	/** id **/
	private int id;

	/** cash receipt id **/
	private String cashReceiptId;

	/** mode **/
	private String mode;

	/** paidOn **/
	private String paidOn;

	/** status **/
	private String status;

	/**
	 * default constructor
	 */
	public PaymentsDataModel() {

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
	 * @return the cashReceiptId
	 */
	public String getCashReceiptId() {
		return cashReceiptId;
	}

	/**
	 * @param cashReceiptId the cashReceiptId to set
	 */
	public void setCashReceiptId(String cashReceiptId) {
		this.cashReceiptId = cashReceiptId;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the paidOn
	 */
	public String getPaidOn() {
		return paidOn;
	}

	/**
	 * @param paidOn the paidOn to set
	 */
	public void setPaidOn(String paidOn) {
		this.paidOn = paidOn;
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
}
