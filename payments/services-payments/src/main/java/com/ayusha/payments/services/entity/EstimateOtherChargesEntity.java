package com.ayusha.payments.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author author1
 * Date : 01-Aug-2019
 * Ticket Model class
 * Ticket Model
 *
 */
@Entity
@Table(name="estimation_other_charges")
public class EstimateOtherChargesEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	@Column(name="id")
    private long id; 
	

	/** customerid **/
	@Column(name="estimated_id")
	private String estimateId="";
	
	
	
	/** customerid **/
	@Column(name="type")
	private String type="";
	
	/** customerid **/
	@Column(name="amount")
	private double amount;
	
	
	/**
	 * default constructor
	 */
	public EstimateOtherChargesEntity() {
		
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
	 * @return the estimateId
	 */
	public String getEstimateId() {
		return estimateId;
	}


	/**
	 * @param estimateId the estimateId to set
	 */
	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}