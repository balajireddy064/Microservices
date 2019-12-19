package com.ayusha.ticket.data.models;

import java.util.List;

public class TicketUserDataModel {

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
	private String addr = "";

	/** city **/
	private String city = "";

	/** state **/
	private String state = "";

	/** pinCode **/
	private String pinCode = "";

	/** country **/
	private String country = "";

	/** password **/
	private String password;

	/** locations **/
	private List<String> lstLocations;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getExpertiseLevel() {
		return expertiseLevel;
	}

	public void setExpertiseLevel(String expertiseLevel) {
		this.expertiseLevel = expertiseLevel;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getLstLocations() {
		return lstLocations;
	}

	public void setLstLocations(List<String> lstLocations) {
		this.lstLocations = lstLocations;
	}

}
