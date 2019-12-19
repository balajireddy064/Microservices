package com.ayusha.job.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Finch Date:01-Aug-2019 Job Service Methods
 *
 */
@Entity
@Table(name = "job")
public class JobEntity {
	private static final long serialVersionUID = 1L;
	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	/** id **/
	@Column(name = "id")
	private long id;

	/** jobId **/
	@Column(name = "job_id")
	private String jobId = "";

	/** statusId **/
	@Column(name = "status")
	private String status = "";

	/** startDate **/
	@Column(name = "start_date")
	private String startDate = "";

	/** actualStartDate **/
	@Column(name = "actual_start_date")
	private String actualStartDate = "";

	/** actualEndDate **/
	@Column(name = "actual_finish_date")
	private String actualEndDate = "";

	/** lastUpdatedOn **/
	@Column(name = "last_updated_on")
	private String lastUpdatedOn = "";

	/** notes **/
	@Column(name = "notes")
	private String notes = "";

	/** userId **/
	@Column(name = "user_id")
	private String userId = "";

	/** reason **/
	@Column(name = "reason")
	private String reason;

	/** customerId **/
	@Column(name = "customerId")
	private String customerId;

	@Column(name = "TicketId")
	private String ticketId;
	@Column(name = "customerName")
	private String customerName;

	/**
	 * default constructor
	 */
	public JobEntity() {

	}

	/**
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * 
	 * @param jobId
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 
	 * @return statusId
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param statusId
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 
	 * @return actualStartDate
	 */
	public String getActualStartDate() {
		return actualStartDate;
	}

	/**
	 * 
	 * @param actualStartDate
	 */
	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	/**
	 * 
	 * @return actualEndDate
	 */
	public String getActualEndDate() {
		return actualEndDate;
	}

	/**
	 * 
	 * @param actualEndDate
	 */
	public void setActualEndDate(String actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	/**
	 * 
	 * @return lastUpdatedOn
	 */
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	/**
	 * 
	 * @param lastUpdatedOn
	 */
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	/**
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return reason
	 */

	public String getReason() {
		return reason;
	}

	/**
	 * 
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 
	 * @return customerId
	 */

	public String getCustomerId() {
		return customerId;
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
	 * 
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the customerName
	 */

}