package com.ayusha.tickets.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Swathi Date : 01-Aug-2019 Ticket Model class Ticket Model
 *
 */
@Entity
@Table(name = "customer")
public class CustomerEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private long id;

	/** customerid **/
	@Column(name = "customerId")
	private String customerId = "";

	/** customerName **/
	@Column(name = "customer_name")
	private String customerName = "";

	/** address1 **/
	@Column(name = "address_1")
	private String address1 = "";

	/** address2 **/
	@Column(name = "address_2")
	private String address2 = "";

	/** street **/
	@Column(name = "street")
	private String street = "";

	/** state **/
	@Column(name = "state")
	private String state = "";

	/** city **/
	@Column(name = "city")
	private String city = "";

	/** pinCode **/
	@Column(name = "pin_code")
	private String pinCode = "";

	/** email **/
	@Column(name = "email")
	private String email = "";

	/** contactNumber **/
	@Column(name = "contact_number")
	private String contactNumber = "";

	/** alternateContact **/
	@Column(name = "alternate_contact")
	private String alternateContact = "";

	/**
	 * default constructor
	 */
	public CustomerEntity() {

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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the alternateContact
	 */
	public String getAlternateContact() {
		return alternateContact;
	}

	/**
	 * @param alternateContact the alternateContact to set
	 */
	public void setAlternateContact(String alternateContact) {
		this.alternateContact = alternateContact;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}