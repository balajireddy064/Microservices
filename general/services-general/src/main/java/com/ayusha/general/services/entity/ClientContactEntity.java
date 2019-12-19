package com.ayusha.general.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientContacts")
public class ClientContactEntity {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int id;

	/** clientName **/
	@Column(name = "clientId")
	private String clientId = "";

	/** clientName **/

	/** emailAddress **/
	@Column(name = "contact_email")
	private String contactEmail = "";

	/** landline **/
	@Column(name = "contact_landline")
	private String contactLandline = "";

	/** mobileNumber **/
	@Column(name = "contact_mobile")
	private String contactMobile = "";

	/** designation **/
	@Column(name = "contact_designation")
	private String contactDesignation = "";

	@Column(name = "contactName")
	private String contactName;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "clientId", insertable="false" update="false")
//	

//	@JoinColumn(name = "clientId")
//	private BusinessClientEntity businessClientEntity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactLandline() {
		return contactLandline;
	}

	public void setContactLandline(String contactLandline) {
		this.contactLandline = contactLandline;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getContactDesignation() {
		return contactDesignation;
	}

	/**
	 * @return the contectName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contectName the contectName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public void setContactDesignation(String contactDesignation) {
		this.contactDesignation = contactDesignation;

	}

//	public BusinessClientEntity getBusinessClientEntity() {
//		return businessClientEntity;
//	}
//
//	public void setBusinessClientEntity(BusinessClientEntity businessClientEntity) {
//		this.businessClientEntity = businessClientEntity;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
