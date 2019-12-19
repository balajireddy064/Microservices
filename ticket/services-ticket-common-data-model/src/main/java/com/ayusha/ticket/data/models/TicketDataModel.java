package com.ayusha.ticket.data.models;

import java.util.Date;

import com.ayusha.products.data.models.ProductDataModel;

/**
 * 
 * @author Swathi 07-Aug-2091 Holds ticket details
 *
 */
public class TicketDataModel {

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

	/** loggedon **/
	private String loggedon = "";
	/** tikectid **/

	/** lastupdatedon **/
	private String lastupdatedon = "";

	/** ticketId **/
	private String ticketId = "";

	private String productId = "";

	private String city = "";

	private String userId;

	private String customerId;

//	/** product data **/
	private ProductDataModel productModel = null;

	/** customer **/
	private CustomerDataModel customerDataModel = null;

	/**
	 * /** default constructor
	 */
	public TicketDataModel() {

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
	 * @return the visitDate
	 */

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return the dealerName
	 */
	public String getDealerName() {
		return dealerName;
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param dealerName the dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	 * @return the loggedon
	 */
	public String getLoggedon() {
		return loggedon;
	}

	/**
	 * @param loggedon the loggedon to set
	 */
	public void setLoggedon(String loggedon) {
		this.loggedon = loggedon;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productModel
	 */
	public ProductDataModel getProductModel() {
		return productModel;
	}

	/**
	 * @param productModel the productModel to set
	 */
	public void setProductModel(ProductDataModel productModel) {
		this.productModel = productModel;
	}

	/**
	 * @return the customerDataModel
	 */
	public CustomerDataModel getCustomerDataModel() {
		return customerDataModel;
	}

	/**
	 * @param customerDataModel the customerDataModel to set
	 */
	public void setCustomerDataModel(CustomerDataModel customerDataModel) {
		this.customerDataModel = customerDataModel;
	}

}
