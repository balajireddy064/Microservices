package com.ayusha.ticket.data.models;

import java.util.List;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public class BulkTicketsUpload {
	
	/** list of tickets **/
	private List<TicketDataModel> lstTicketDataModel = null;
	
	/** id **/
	private String is="1";
	
	/**
	 * default constructor
	 */
	public BulkTicketsUpload() {
		
	}

	/**
	 * @return the lstTicketDataModel
	 */
	public List<TicketDataModel> getLstTicketDataModel() {
		return lstTicketDataModel;
	}

	/**
	 * @param lstTicketDataModel the lstTicketDataModel to set
	 */
	public void setLstTicketDataModel(List<TicketDataModel> lstTicketDataModel) {
		this.lstTicketDataModel = lstTicketDataModel;
	}

	/**
	 * @return the is
	 */
	public String getIs() {
		return is;
	}

	/**
	 * @param is the is to set
	 */
	public void setIs(String is) {
		this.is = is;
	}
}
