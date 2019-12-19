package com.ayusha.payments.data.models;

/**
 * 
 * @author author 10-Aug-2019
 *
 */
public class CashReceiptDiscountsDataModel {

	/** id **/
	private int id;
	
	/** couponCode **/
	private String couponCode;

	/** amount **/
	private double amount;

	/**
	 * default constructor
	 */
	public CashReceiptDiscountsDataModel() {

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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
