package com.ayusha.user.data.models;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Finch Date:01-Aug-2019 User Data Model class
 *
 */
public class UserDataModel {
	/** id **/
	private long id;

	/** userId **/
	private String userId = "";

	/** loginId **/
	private String loginId = "";

	/** firstName **/
	private String firstName = "";

	/** middleName **/
	private String middleName = "";

	/** lastName **/
	private String lastName = "";

	/** aboutMe **/
	private String aboutMe;

	/** dateOfBirth **/
	private String dateOfBirth;

	/** dateOfJoining **/
	private String dateOfJoining;

	/** email **/
	private String email = "";

	/** phoneNumber **/
	private String phoneNumber = "";

	/** skills **/
	private String skills = "";

	/** skills **/
	private String expertiseLevel;

	/** role **/
	private String role;

	/** salary **/
	private String salary;

	/** age **/
	private String age;

	/** gender **/
	private String gender = "";

	/** createdOn **/
	private String createdOn = "";

	/** addr **/
	private String address = "";

	/** city **/
	private String city = "";

	/** state **/
	private String state = "";

	/** pinCode **/
	private String pinCode = "";

	/** password **/
	private String password;

	private byte[] imageUpload;

	/** locations **/
	private List<String> lstLocations;

	private List<UserEducationDetailsDataModels> userEducationDetailsDataModels;

	public List<UserEducationDetailsDataModels> getUserEducationDetailsDataModels() {
		return userEducationDetailsDataModels;
	}

	public void setUserEducationDetailsDataModels(List<UserEducationDetailsDataModels> userEducationDetailsDataModels) {
		this.userEducationDetailsDataModels = userEducationDetailsDataModels;
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
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	 * @return the imageUpload
	 */
	public byte[] getImageUpload() {
		return imageUpload;
	}

	/**
	 * @param imageUpload the imageUpload to set
	 */
	public void setImageUpload(byte[] imageUpload) {
		this.imageUpload = imageUpload;
	}

	/**
	 * @return the lstLocations
	 */
	public List<String> getLstLocations() {
		return lstLocations;
	}

	/**
	 * @param lstLocations the lstLocations to set
	 */
	public void setLstLocations(List<String> lstLocations) {
		this.lstLocations = lstLocations;
	}

	/**
	 * @return the userEducationDetailsDataModels
	 */
//	public List<UserEducationDetailsDataModels> getUserEducationDetailsDataModels() {
//		return userEducationDetailsDataModels;
//	}
//
//	/**
//	 * @param userEducationDetailsDataModels the userEducationDetailsDataModels to
//	 *                                       set
//	 */
//	public void setUserEducationDetailsDataModels(List<UserEducationDetailsDataModels> userEducationDetailsDataModels) {
//		this.userEducationDetailsDataModels = userEducationDetailsDataModels;
//	}

}
