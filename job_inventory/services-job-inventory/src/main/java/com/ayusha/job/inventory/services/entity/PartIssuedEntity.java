package com.ayusha.job.inventory.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 
 * @author Finch
 * @Date : 01-Aug-2019 Ticket Model class Ticket Model
 *
 */
@Entity
@Table(name = "partsissued")
public class PartIssuedEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** partid **/
	@Column(name = "partId")
	private String partid = "";

	/** type **/
	@Column(name = "type")
	private String type = "";

	/** jobId **/
	@Column(name = "jobId")
	private String jobId = "";

	/** quantity **/
	@Column(name = "quantity")
	private int quantity;

	/** issuedOn **/
	@Column(name = "issuedOn")
	private String issuedOn;

	/**
	 * default constructor
	 */
	public PartIssuedEntity() {

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
	 * @return the partid
	 */
	public String getPartid() {
		return partid;
	}

	/**
	 * @param partid the partid to set
	 */
	public void setPartid(String partid) {
		this.partid = partid;
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
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
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
	 * @return the issuedOn
	 */
	public String getIssuedOn() {
		return issuedOn;
	}

	/**
	 * @param issuedOn the issuedOn to set
	 */
	public void setIssuedOn(String issuedOn) {
		this.issuedOn = issuedOn;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}