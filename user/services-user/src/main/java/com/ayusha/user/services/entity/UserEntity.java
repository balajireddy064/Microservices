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
@Table(name = "users")
public class UserEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	/** id **/
	@Column(name = "id")
	private long id;

	/** userId **/
	@Column(name = "user_id")
	private String userId = "";

	/** firstName **/
	@Column(name = "f_name")
	private String firstName = "";

	/** middleName **/
	@Column(name = "m_name")
	private String middleName = "";

	/** lastName **/
	@Column(name = "l_name")
	private String lastName = "";

	/** aboutMe **/
	@Column(name = "about_me")
	private String aboutMe;

	/** dateOfBirth **/
	@Column(name = "dob")
	private String dateOfBirth;

	/** dateOfJoining **/
	@Column(name = "doj")
	private String dateOfJoining;

	/** email **/
	@Column(name = "email")
	private String email = "";

	/** phoneNumber **/
	@Column(name = "ph_no")
	private String phoneNumber = "";

	/** skills **/
	@Column(name = "skills")
	private String skills = "";

	/** skills **/
	@Column(name = "expertise_level")
	private String expertiseLevel;

	/** role **/
	@Column(name = "role")
	private String role;

	/** salary **/
	@Column(name = "salary")
	private String salary;

	/** age **/
	@Column(name = "age")
	private String age;

	/** gender **/
	@Column(name = "gender")
	private String gender = "";

	/** createdOn **/
	@Column(name = "created_on")
	private String createdOn = "";

	/** addr **/
	@Column(name = "address")
	private String address = "";

	/** city **/
	@Column(name = "city")
	private String city = "";

	/** state **/
	@Column(name = "state")
	private String state = "";

	/** pinCode **/
	@Column(name = "pin")
	private String pinCode = "";

	/** password **/
	@Column(name = "password")
	private String password;

	@Column(name = "image")
	private byte[] imageUpload;

	/**
	 * default constructor
	 */
	public UserEntity() {

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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the aboutMe
	 */
	public String getAboutMe() {
		return aboutMe;
	}

	/**
	 * @param aboutMe the aboutMe to set
	 */
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the dateOfJoining
	 */
	public String getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}

	/**
	 * @return the expertiseLevel
	 */
	public String getExpertiseLevel() {
		return expertiseLevel;
	}

	/**
	 * @param expertiseLevel the expertiseLevel to set
	 */
	public void setExpertiseLevel(String expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
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
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	 

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the imageUpload
	 */
	public byte[] getImageUpload() {
		return imageUpload;
	}

	/**
	 * 
	 * @param imageUpload the imageUpload to set
	 * 
	 */
	public void setImageUpload(byte[] imageUpload) {
		this.imageUpload = imageUpload;
	}

}