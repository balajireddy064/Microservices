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
 * @author Finch Date : 01-Aug-2019 Ticket Model class Ticket Model
 *
 */
@Entity
@Table(name = "partsReturnedDemo")
public class PartReturnedEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** partid **/
	@Column(name = "partid")
	private String partid = "";

	/** type **/
	@Column(name = "type")
	private String type = "";

	/** jobId **/
	@Column(name = "jobId")
	private String jobId = "";

	/** remarks **/
	@Column(name = "remarks")
	private String remarks = "";

	@Lob
	/** media **/
	@Column(name = "media")
	private byte[] media;

	/** quantity **/
	@Column(name = "quantity")
	private int quantity;

	/** returnedOn **/
	@Column(name = "returnedOn")
	private String returnedOn;

	/** verified **/
	@Column(name = "verified")
	private char verified;

	/**
	 * default constructor
	 */
	public PartReturnedEntity() {

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
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the media
	 */
	public byte[] getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(byte[] media) {
		this.media = media;
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
	 * @return the returnedOn
	 */
	public String getReturnedOn() {
		return returnedOn;
	}

	/**
	 * @param returnedOn the returnedOn to set
	 */
	public void setReturnedOn(String returnedOn) {
		this.returnedOn = returnedOn;
	}

	/**
	 * @return the verified
	 */
	public char getVerified() {
		return verified;
	}

	/**
	 * @param verified the verified to set
	 */
	public void setVerified(char verified) {
		this.verified = verified;
	}

}