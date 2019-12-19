package com.ayusha.ticket.services.constants;

/**
 * 
 * @author Finch Date:01-Aug-2019 Ticket Service Constants
 *
 */
public class TicketServiceConstants {

	/** get customer details service name **/
	public final static String GET_CUSTOMER_DETAILS_SERVICE_NAME = "customer/get?id=$1";

	/** get ticket details service name **/
	public final static String GET_TICKET_DETAILS_SERVICE_NAME = "tickets/ticket/get?id=$1";

	/** update ticket details service name **/
	public final static String UPDATE_TICKET_DETAILS_SERVICE_NAME = "ticket/updatepart";

	/** add ticket details service name **/
	public final static String ADD_TICKET_DETAILS_SERVICE_NAME = "tickets/ticket/add";

	/** assign ticket details service name **/
	public final static String ASSIGN_TICKET_DETAILS_SERVICE_NAME = "jobs/job/assign";

	public final static String UPDATE_ASSIGN = "jobs/job/assignwithWeb";

	/** update customer details service name **/
	public final static String UPDATE_CUSTOMER_DETAILS_SERVICE_NAME = "customer/update";

	/** add customer details service name **/
	public final static String ADD_CUSTOMER_DETAILS_SERVICE_NAME = "customer/add";

	/** find product details service name **/
	public final static String FIND_PRODUCT_DETAILS_SERVICE_NAME = "products/product/find/productfor1?categoryid=$1&subcategoryid=$2&makeid=$3&modelid=$4";
	
	public final static String GET_USER_PINCODE="http://localhost:8095/users/user/locations?locationid=";

}
