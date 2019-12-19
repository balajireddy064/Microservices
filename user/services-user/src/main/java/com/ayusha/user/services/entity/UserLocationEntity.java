package com.ayusha.user.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Finch Date : 01-Aug-2019 User Service Methods
 *
 */
@Entity
@Table(name = "user_location")
public class UserLocationEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	/** id **/
	@Column(name = "id")
	private long id;

	/** userId **/
	@Column(name = "user_id")
	private String userId = "";

	/** locationid **/
	@Column(name = "location_id")
	private String locationid = "";

	/** effectiveDate **/
	@Column(name = "effective_date")
	private String effectiveDate = "";

	/** serving **/
	@Column(name = "serving")
	private String serving = "";
	@Column(name = "role")
	private String role = "";

	/**
	 * default constructor
	 */
	public UserLocationEntity() {

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
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationid;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the serving
	 */
	public String getServing() {
		return serving;
	}

	/**
	 * @param serving the serving to set
	 */
	public void setServing(String serving) {
		this.serving = serving;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the locationid
	 */
	public String getLocationid() {
		return locationid;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
