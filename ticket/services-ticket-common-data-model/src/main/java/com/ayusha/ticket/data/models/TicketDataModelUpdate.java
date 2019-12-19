package com.ayusha.ticket.data.models;

/**
 * 
 * @author Finch Date:01-Aug-2019 Ticket Data Model class
 *
 */

public class TicketDataModelUpdate {

	/** id **/
	private long id;

	/** issue **/
	private String issue = "";

	/** description **/
	private String description = "";

	/** statusid **/
	private String status = "";

	/** productid **/
	private String productid = "";

	/** loggedon **/
	private String loggedon = "";

	/** customerid **/
	private String customerid = "";

	/** lastupdatedon **/
	private String lastupdatedon = "";

	/** serialnumber **/
	private String serialnumber = "";

	/** servicetype **/
	private String servicetype = "";

	/** ticketCode **/
	private String ticketCode = "";

	/** userId **/
	private String UserId;

	/**
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return issue
	 */
	public String getIssue() {
		return issue;
	}

	/**
	 * 
	 * @param issue
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return statusid
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param statusid
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return productid
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * 
	 * @param productid
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * 
	 * @return loggedon
	 */
	public String getLoggedon() {
		return loggedon;
	}

	/**
	 * 
	 * @param loggedon
	 */
	public void setLoggedon(String loggedon) {
		this.loggedon = loggedon;
	}

	/**
	 * 
	 * @return customerid
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * 
	 * @param customerid
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return UserId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		UserId = userId;
	}

	/**
	 * 
	 * @return lastupdatedon
	 */
	public String getLastupdatedon() {
		return lastupdatedon;
	}

	/**
	 * 
	 * @param lastupdatedon
	 */
	public void setLastupdatedon(String lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	/**
	 * 
	 * @return serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}

	/**
	 * 
	 * @param serialnumber
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	/**
	 * 
	 * @return servicetype
	 */
	public String getServicetype() {
		return servicetype;
	}

	/**
	 * 
	 * @param servicetype
	 */
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	/**
	 * 
	 * @return ticketCode
	 */
	public String getTicketCode() {
		return ticketCode;
	}

	/**
	 * 
	 * @param ticketCode
	 */
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

}
