package com.ayusha.ticket.data.models;

import java.util.Date;

/**
 * 
 * @author Swathi 08-Aug-2019 Ticket Model class
 *
 */
public class TicketAndCustomerDataModel {

	private long id;

	/** callType **/
	private String callType = "";

	/** brand **/
	private String brand = "";

	/** category **/
	private String category = "";

	/** subCategory **/
	private String subCategory = "";

	/** model **/
	private String model = "";

	/** serialNumber **/
	private String serialNumber = "";

	/** warranty **/
	private String warranty = "";

	/** visitTime **/
	private String visitTime = "";

	/** visitDate **/
	private Date visitDate;

	/** dealerName **/
	private String dealerName = "";

	/** description **/
	private String description = "";

	/** status **/
	private String status = "";

	/** lastupdatedon **/
	private String lastupdatedon = "";

	/** ticketId **/
	private String ticketId = "";

	private String customerId = "";

	public CustomerDataModel customerDataModel;

	public TicketProductDataModel productDataModel;

	/**
	 * 
	 * @return customerDataModels
	 */
	public CustomerDataModel getCustomerDataModel() {
		return customerDataModel;
	}

	/**
	 * 
	 * @param customerDataModels
	 */
	public void setCustomerDataModel(CustomerDataModel customerDataModel) {
		this.customerDataModel = customerDataModel;
	}

	/**
	 * TicketAndCustomerDataModel
	 * 
	 * @param id
	 * @param issue
	 * @param description
	 * @param statusid
	 * @param productid
	 * @param loggedon
	 * @param customerid
	 * @param loggedby
	 * @param lastupdatedon
	 * @param serialnumber
	 * @param servicetype
	 * @param ticketCode
	 * @param customerDataModels
	 */

	public TicketAndCustomerDataModel(long id, String callType, String brand, String category, String subCategory,
			String model, String serialNumber, String warranty, String visitTime, Date visitDate, String dealerName,
			String description, String status,String lastupdatedon, String ticketId,
			String customerId, CustomerDataModel customerDataModel) {
		super();
		this.id = id;
		this.callType = callType;
		this.brand = brand;
		this.category = category;
		this.subCategory = subCategory;
		this.model = model;
		this.serialNumber = serialNumber;
		this.warranty = warranty;
		this.visitTime = visitTime;
		this.visitDate = visitDate;
		this.dealerName = dealerName;
		this.description = description;
		this.status = status;
		this.lastupdatedon = lastupdatedon;
		this.ticketId = ticketId;
		this.customerId = customerId;
		this.customerDataModel = customerDataModel;
	}

	public TicketAndCustomerDataModel(long id, String callType, String brand, String category, String subCategory,
			String model, String serialNumber, String warranty, String visitTime, Date visitDate, String dealerName,
			String description, String status, String lastupdatedon, String ticketId,
			String customerId, CustomerDataModel customerDataModel, TicketProductDataModel productDataModel) {
		super();
		this.id = id;
		this.callType = callType;
		this.brand = brand;
		this.category = category;
		this.subCategory = subCategory;
		this.model = model;
		this.serialNumber = serialNumber;
		this.warranty = warranty;
		this.visitTime = visitTime;
		this.visitDate = visitDate;
		this.dealerName = dealerName;
		this.description = description;
		this.status = status;
		this.lastupdatedon = lastupdatedon;
		this.ticketId = ticketId;
		this.customerId = customerId;
		this.customerDataModel = customerDataModel;
		this.productDataModel = productDataModel;
	}

	/**
	 * default constructor
	 */
	public TicketAndCustomerDataModel() {

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
	 * @return the callType
	 */
	public String getCallType() {
		return callType;
	}

	/**
	 * @param callType the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the warranty
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * @param warranty the warranty to set
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	/**
	 * @return the visitTime
	 */
	public String getVisitTime() {
		return visitTime;
	}

	/**
	 * @param visitTime the visitTime to set
	 */
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	/**
	 * @return the dealerName
	 */
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * @param dealerName the dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the lastupdatedon
	 */
	public String getLastupdatedon() {
		return lastupdatedon;
	}

	/**
	 * @param lastupdatedon the lastupdatedon to set
	 */
	public void setLastupdatedon(String lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	/**
	 * @return the ticketId
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the productDataModel
	 */
	public TicketProductDataModel getProductDataModel() {
		return productDataModel;
	}

	/**
	 * @param productDataModel the productDataModel to set
	 */
	public void setProductDataModel(TicketProductDataModel productDataModel) {
		this.productDataModel = productDataModel;
	}

}