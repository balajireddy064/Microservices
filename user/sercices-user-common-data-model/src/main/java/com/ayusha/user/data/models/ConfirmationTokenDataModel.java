package com.ayusha.user.data.models;

import java.util.Date;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019 
 * User Data Model class
 *
 */
public class ConfirmationTokenDataModel {

	/** tokenid **/
	private long tokenid;
	
	/**confirmationToken**/
	private String confirmationToken;

	/**createdDate**/
	private Date createdDate;

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
