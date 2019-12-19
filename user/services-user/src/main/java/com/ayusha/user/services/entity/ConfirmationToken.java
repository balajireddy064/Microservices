package com.ayusha.user.services.entity;

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

/**
 * 
 * @author Finch Date:01-Aug-2019 User Service Methods
 *
 */
@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	/** tokenid **/
	@Column(name = "token_id")
	private long tokenid;

	/** confirmationToken **/
	@Column(name = "confirmation_token")
	private String confirmationToken;
	@Column(name = "email")
	private String email;
	/** createdDate **/
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/** user **/
	@OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private UserEntity user;

	/**
	 * default constructor
	 */
	public ConfirmationToken() {
	}

	/**
	 * 
	 * @param user
	 */
	public ConfirmationToken(UserEntity user) {
		this.user = user;
		createdDate = new Date();
		confirmationToken = UUID.randomUUID().toString();
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
	 * 
	 * @return confirmationToken
	 */
	public String getConfirmationToken() {
		return confirmationToken;
	}

	/**
	 * 
	 * @param confirmationToken
	 */
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	/**
	 * 
	 * @return createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 
	 * @return user
	 */
	public UserEntity getUserEntity() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUserEntity(UserEntity user) {
		this.user = user;
	}

	/**
	 * 
	 * @return tokenid
	 */
	public long getTokenid() {
		return tokenid;
	}

	/**
	 * 
	 * @param tokenid
	 */
	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}
}
