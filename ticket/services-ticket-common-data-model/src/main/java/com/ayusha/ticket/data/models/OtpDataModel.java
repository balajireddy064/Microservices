package com.ayusha.ticket.data.models;

import java.util.Date;

public class OtpDataModel {

	private String status;
	private String phone;
	private String jobCode;
	private String email;
	private String customerName;
	private Date otpExper;
	private String lastUpdate;

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getOtpExper() {
		return otpExper;
	}

	public void setOtpExper(Date otpExper) {
		this.otpExper = otpExper;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}