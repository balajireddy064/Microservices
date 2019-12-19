package com.ayusha.payments.services.constants;

/**
 * 
 * @author author
 * 10-Aug-2019
 *
 */
public class PaymentsServicesConstants {

	/** Job Notes Get Service Name **/
	public final static String GET_STATES_SERVICE_NAME="general/states/find?statecode=$1";
	
	/** Job Notes Get Service Name **/
	public final static String GET_STATES_COUNTRY_SERVICE_NAME="general/states/findAll?countrycode=$1";
	/** Job Notes Get Service Name **/
	public final static String UPDATE_STATES_SERVICE_NAME="general/states/update/";
	
	/** Job Notes Get Service Name **/
	public final static String ADD_STATES_SERVICE_NAME="general/states/add";
	
	
	/** Job Notes Get Service Name **/
	public final static String GET_COUNTRY_SERVICE_NAME="general/country/find?countrycode=$1";
	/** Job Notes Get Service Name **/
	public final static String UPDATE_COUNTRY_SERVICE_NAME="general/country/update/";
	
	/** Job Notes Get Service Name **/
	public final static String ADD_COUNTRY_SERVICE_NAME="general/country/add";
	
	
	/** Job Notes Get Service Name **/
	public final static String GET_ESTIMATE_FOR_ESTIMATEID_SERVICE_NAME="payments/estimate/find?estimateId=$1";
	
	/** Job Notes Get Service Name **/
	public final static String GET_ESTIMATE_FOR_JOBCODE_SERVICE_NAME="payments/estimate/findjobcode?jobcode=$1";
	
	
	/** Job Notes Get Service Name **/
	public final static String UPDATE_ESTIMATE_SERVICE_NAME="payments/estimate/update/";
	
	/** Job Notes Get Service Name **/
	public final static String ADD_ESTIMATESERVICE_NAME="payments/estimate/add";
}
