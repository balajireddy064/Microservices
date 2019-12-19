package com.ayusha.communication.services.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Ticket Model
 *
 */
@Entity
@Table(name = "notification_requests")
public class CommunicationEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** status **/
	@Column(name = "status")
	private String status;

	/** phone **/
	@Column(name = "phone")
	private String phone;

	/** jobCode **/
	@Column(name = "jobCode")
	private String jobCode;

	/** otp **/
	@Column(name = "otp")
	private String otp;

	/** email **/
	@Column(name = "email")
	private String email;

	/** otpExper **/
	@Column(name = "otpExpiry")
	private Date otpExpiry;

	/** customerName **/
	@Column(name = "customerName")
	private String customerName;

	/*
	 * last update date
	 */
	@Column(name = "last_update")
	private Date lastUpdate;

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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
	 * @return the otpExpiry
	 */
	public Date getOtpExpiry() {
		return otpExpiry;
	}

	/**
	 * @param otpExpiry the otpExpiry to set
	 */
	public void setOtpExpiry(Date otpExpiry) {
		this.otpExpiry = otpExpiry;
	}

	/**
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @return jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}

	/**
	 * 
	 * @param jobCode
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * 
	 * @return otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * 
	 * @param otp
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * 
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}