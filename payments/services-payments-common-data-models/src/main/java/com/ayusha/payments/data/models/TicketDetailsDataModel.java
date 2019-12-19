package com.ayusha.payments.data.models;

public class TicketDetailsDataModel {
	/** id **/
	private long id;

	/** issue **/
	private String issue = "";
	/** description **/

	private String description = "";
	/** statusid **/

	private String statusid = "";
	/** productid **/

	private String productid = "";
	/** loggedon **/

	private String loggedon = "";
	/** customerid **/

	private String customerid = "";
	/** loggedby **/

	private String loggedby = "";
	/** lastupdatedon **/

	private String lastupdatedon = "";
	/** serialnumber **/

	private String serialnumber = "";
	/** servicetype **/

	private String servicetype = "";
	/** ticketCode **/

	private String ticketCode = "";
	/** customerDataModels **/

	public CustomerDataModel customerDataModels;

	public CustomerDataModel getCustomerDataModels() {
		return customerDataModels;
	}

	public void setCustomerDataModels(CustomerDataModel customerDataModels) {
		this.customerDataModels = customerDataModels;
	}

	public TicketDetailsDataModel(long id, String issue, String description, String statusid, String productid,
			String loggedon, String customerid, String loggedby, String lastupdatedon, String serialnumber,
			String servicetype, String ticketCode, CustomerDataModel customerDataModels) {
		super();
		this.id = id;
		this.issue = issue;
		this.description = description;
		this.statusid = statusid;
		this.productid = productid;
		this.loggedon = loggedon;
		this.customerid = customerid;
		this.loggedby = loggedby;
		this.lastupdatedon = lastupdatedon;
		this.serialnumber = serialnumber;
		this.servicetype = servicetype;
		this.ticketCode = ticketCode;
		this.customerDataModels = customerDataModels;
	}

	public TicketDetailsDataModel(CustomerDataModel customerDataModels) {
		super();
		this.customerDataModels = customerDataModels;
	}

	public TicketDetailsDataModel() {

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
	 * @return the issue
	 */
	public String getIssue() {
		return issue;
	}

	/**
	 * @param issue the issue to set
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the statusid
	 */
	public String getStatusid() {
		return statusid;
	}

	/**
	 * @param statusid the statusid to set
	 */
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	/**
	 * @return the productid
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * @param productid the productid to set
	 */
	public void setProductid(String productid) {
		this.productid = productid;
	}

	/**
	 * @return the loggedon
	 */
	public String getLoggedon() {
		return loggedon;
	}

	/**
	 * @param loggedon the loggedon to set
	 */
	public void setLoggedon(String loggedon) {
		this.loggedon = loggedon;
	}

	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	/**
	 * @return the loggedby
	 */
	public String getLoggedby() {
		return loggedby;
	}

	/**
	 * @param loggedby the loggedby to set
	 */
	public void setLoggedby(String loggedby) {
		this.loggedby = loggedby;
	}

	/**
	 * @return the lastupdatedon
	 */
	public String getLastupdatedon() {
		return lastupdatedon;
	}

	/**
	 * @param lastupdatedon the lastupdatedon to set
	 */
	public void setLastupdatedon(String lastupdatedon) {
		this.lastupdatedon = lastupdatedon;
	}

	/**
	 * @return the serialnumber
	 */
	public String getSerialnumber() {
		return serialnumber;
	}

	/**
	 * @param serialnumber the serialnumber to set
	 */
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	/**
	 * @return the servicetype
	 */
	public String getServicetype() {
		return servicetype;
	}

	/**
	 * @param servicetype the servicetype to set
	 */
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	/**
	 * @return the ticketCode
	 */
	public String getTicketCode() {
		return ticketCode;
	}

	/**
	 * @param ticketCode the ticketCode to set
	 */
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

}
