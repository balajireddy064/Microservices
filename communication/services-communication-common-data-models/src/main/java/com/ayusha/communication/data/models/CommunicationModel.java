package com.ayusha.communication.data.models;

import java.util.Date;
/**
 * 
 * @author Author
 * Date:01-Aug-2019
 * Communication Data Model class
 *
 */
public class CommunicationModel {
	
	
	/** id **/
	private long id;

	/** status **/
	private String status;

	/** status **/
	private String phone;

	/** jobCode **/
	private String jobCode;

	/** otp **/
	private String otp;

	/** email **/
	private String email;

	/** otpExper **/
	private Date otpExpiry;

	/** customerName **/
	private String customerName;
	
	/*
	 * last update date
	 */
	private String lastUpdate;

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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the otp
	 */
	public String getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		this.otp = otp;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the otpExper
	 */
	 
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
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
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	


}
