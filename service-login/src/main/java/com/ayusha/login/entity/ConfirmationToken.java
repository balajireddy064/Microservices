package com.ayusha.login.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	/** tokenid **/
	private long tokenid;
	/** confirmationToken **/
	@Column(name = "confirmation_token")
	private String confirmationToken;
	/** createdDate **/
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@OneToOne(targetEntity = UserLogin.class, fetch = FetchType.EAGER)
	// @JoinColumn(nullable = false, name = "user_id")
	private UserLogin user;

	public ConfirmationToken() {
	}

	/** generater tokens **/
	public ConfirmationToken(UserLogin user) {
		this.user = user;
		createdDate = new Date();
		confirmationToken = UUID.randomUUID().toString();
	}

	/**
	 * @return the tokenid
	 */
	public long getTokenid() {
		return tokenid;
	}

	/**
	 * @param tokenid the tokenid to set
	 */
	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	/**
	 * @return the confirmationToken
	 */
	public String getConfirmationToken() {
		return confirmationToken;
	}

	/**
	 * @param confirmationToken the confirmationToken to set
	 */
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the user
	 */
	public UserLogin getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserLogin user) {
		this.user = user;
	}

}
