package com.ayusha.tickets.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Swathi Date : 01-Aug-2019 Ticket Model class Ticket Model
 *
 */
@Entity
@Table(name = "ticket")
public class TicketEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** callType **/
	@Column(name = "call_type")
	private String callType = "";

	/** brand **/
	@Column(name = "brand")
	private String brand = "";

	/** category **/
	@Column(name = "category")
	private String category = "";

	/** subCategory **/
	@Column(name = "sub_category")
	private String subCategory = "";

	/** model **/
	@Column(name = "model")
	private String model = "";

	/** serialNumber **/
	@Column(name = "serial_number")
	private String serialNumber = "";

	/** warranty **/
	@Column(name = "warranty")
	private String warranty = "";

	/** visitTime **/
	@Column(name = "time_of_visit")
	private String visitTime = "";

	/** visitDate **/

//	private String visitDate = "";

	/** dealerName **/
	@Column(name = "dealer_name")
	private String dealerName = "";

	/** description **/
	@Column(name = "description")
	private String description = "";

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//	@DateTimeFormat(iso = ISO.DATE_TIME)
//	@DateTimeFormat(style = "M-")
	@Column(name = "visitDate")
	public Date visitDate;

	/** status **/
	@Column(name = "status")
	private String status = "";

	/** loggedon **/
	@Column(name = "loggedOn")
	private String loggedon = "";
	/** tikectid **/

	/** lastupdatedon **/
	@Column(name = "last_updated_on")
	private String lastupdatedon = "";

	/** ticketId **/
	@Column(name = "ticket_id")
	private String ticketId = "";

	/** customerid **/
	@Column(name = "customer_id")
	private String customerId = "";

	/** customerid **/
	@Column(name = "product_id")
	private String productId = "";

	@Column(name = "user_id")
	private String userId;

//	@Column(name = "city")
//	private String city;

	/**
	 * // * @return the city //
	 */
//	public String getCity() {
//		return city;
//	}
//
//	/**
//	 * @param city the city to set
//	 */
//	public void setCity(String city) {
//		this.city = city;
//	}

	/**
	 * default constructor
	 */
	public TicketEntity() {

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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}