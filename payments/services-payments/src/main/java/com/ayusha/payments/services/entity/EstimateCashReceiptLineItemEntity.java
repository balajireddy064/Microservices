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
@Table(name = "lineItems")
public class EstimateCashReceiptLineItemEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** itemId **/
	@Column(name = "item_id")
	private String itemId;

	/** type **/
	@Column(name = "type")
	private String type = "";

	/** amount **/
	@Column(name = "amount")
	private double amount;

	/** tax **/
	@Column(name = "tax")
	private double tax;

	/** quantity **/
	@Column(name = "quantity")
	private int quantity;

	/** jobCode **/
	@Column(name = "job_code")
	private String jobCode = "";

	/** loggedOn **/
	@Column(name = "logged_on")
	private String loggedOn = "";

	/** estimate id **/
	@Column(name = "estimate_id")
	private String estimateId = "";

	/**
	 * default constructor
	 */
	public EstimateCashReceiptLineItemEntity() {

	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the loggedOn
	 */
	public String getLoggedOn() {
		return loggedOn;
	}

	/**
	 * @param loggedOn the loggedOn to set
	 */
	public void setLoggedOn(String loggedOn) {
		this.loggedOn = loggedOn;
	}

	public String getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}

}