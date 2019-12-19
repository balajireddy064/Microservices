package com.ayusha.payments.data.models;

public class LineItemsCombinedDataModel {
	
	/** id **/
	private long id;

	/** itemId **/
	private String itemId = "";

	/** amount **/
	private double amount;

	/** tax **/
	private double tax;

	/** quantity **/
	private int quantity;

	/** type **/
	private String type = "";

	/** jobCode **/
	private String jobCode = "";

	/** loggedOn **/
	private String loggedOn = "";

	/** paymentItemDataModel **/
	private PaymentItemDataModel paymentItemDataModel;

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

	/**
	 * @return the paymentItemDataModel
	 */
	public PaymentItemDataModel getPaymentItemDataModel() {
		return paymentItemDataModel;
	}

	/**
	 * @param paymentItemDataModel the paymentItemDataModel to set
	 */
	public void setPaymentItemDataModel(PaymentItemDataModel paymentItemDataModel) {
		this.paymentItemDataModel = paymentItemDataModel;
	}

	/** Parameterised constructor **/
	public LineItemsCombinedDataModel(long id, String itemId, double amount, double tax, int quantity, String type,
			String jobCode, String loggedOn, PaymentItemDataModel paymentItemDataModel) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.amount = amount;
		this.tax = tax;
		this.quantity = quantity;
		this.type = type;
		this.jobCode = jobCode;
		this.loggedOn = loggedOn;
		this.paymentItemDataModel = paymentItemDataModel;
	}

	/** default constructor **/
	public LineItemsCombinedDataModel() {

	}

}
