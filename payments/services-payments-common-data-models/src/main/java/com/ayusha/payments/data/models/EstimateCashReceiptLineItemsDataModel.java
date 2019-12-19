package com.ayusha.payments.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author author 10-Aug-2019
 *
 */
public class EstimateCashReceiptLineItemsDataModel {

	/** jobCode **/
	private String jobCode="";
	
	/** loggedOn **/
	private String loggedOn = "";
	
	/** estimate Id **/
	private String estimateId= "";
	
	/** LineItems Datamodel **/
	List<EstimateCashReceiptLineItemDataModel> lineItems;

	
	/**Default constructor **/
	public EstimateCashReceiptLineItemsDataModel() {
		
	}

	/**
	 * @return the lineItems
	 */
	public List<EstimateCashReceiptLineItemDataModel> getLineItems() {
		return lineItems;
	}

	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<EstimateCashReceiptLineItemDataModel> lineItems) {
		this.lineItems = lineItems;
	}
	

	/**
	 * @return the jobCode
	 */
	public String getJobCode() {
		return jobCode;
	}


	/**
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	

	/**
	 * @return the loggedOn
	 */
	public String getLoggedOn() {
		return loggedOn;
	}

	/**
	 * @param loggedOn the loggedOn to set
	 */
	public void setLoggedOn(String loggedOn) {
		this.loggedOn = loggedOn;
	}

	public String getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}

}
