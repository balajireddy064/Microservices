package com.ayusha.http.client;

import com.ayusha.http.client.models.ServiceResponse;
import com.ayusha.http.constants.HTTPConstants;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author author
 *
 *         07-Aug-2019 Services Client
 */
public class ServicesClient {

	/** singletton reference **/
	private final static ServicesClient servicesClient = new ServicesClient();

	/** base url **/
	public String BASE_URL = "http://134.209.147.111:8092/";

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(ServicesClient.class);

	/** urls **/
	public final static String TICKET_BASE_URL = "http://134.209.147.111:8091/";
	/** urls **/
	public final static String USER_BASE_URL = "http://134.209.147.111:8095/";
	/** urls **/
	public final static String JOB_BASE_URL = "http://134.209.147.111:8092/";
	/** urls **/
	public final static String PRODUCT_BASE_URL = "http://134.209.147.111:8093/";
	/** urls **/
	public final static String COMMUNICATION_BASE_URL = "http://134.209.147.111:8094/";
	/** urls **/
	public final static String PAYMENTS_BASE_URL = "http://134.209.147.111:8096/";
	/** urls **/
	public final static String JOB_INVENTORY_BASE_URL = "http://134.209.147.111:8097/";
	/** urls **/
	public final static String GENERAL_SERVICES_BASE_URL = "http://134.209.147.111:8098/";
	/** urls **/
	public final static String REPAIR_SERVICES_BASE_URL = "http://134.209.147.111:8099/";

	/**
	 * default constructor
	 */
	public ServicesClient() {

	}

	/**
	 * returns singleton object reference
	 */
	public void setBaseURL(String baseURL) {
		this.BASE_URL = baseURL;
	}

	/**
	 * execute get method
	 */
	public ServiceResponse execute(String serviceName, String payLoad, String method) throws ServicesDataException {
		HttpClient httpClient = new HttpClient();
		System.out.println("servicename:" + serviceName);
		System.out.println("payload" + payLoad);
		System.out.println("method" + method);
		ServiceResponse serviceResponse = null;
		if (method == null) {
			ServicesDataException servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		} else if (method.equalsIgnoreCase(HTTPConstants.GET)) {
			serviceResponse = httpClient.executeGET(BASE_URL + serviceName);
			System.out.println(" SERVICE RESSSSSSSSSSSSSSSSSSSSSAAAA" + serviceResponse.getResponseCode());
			System.out.println(" CHECK THIS    " + serviceResponse.getResponseCode());
			if (serviceResponse == null) {

			} else if (serviceResponse != null
					&& (!serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_OK)
							|| !serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_SUCCESS))) {
				System.out.println(" CHECK THIS    " + serviceResponse);
				System.out.println(" SERVICE AAAAAAAAAAAAAAAAAAAAAAAAAAAA " + serviceResponse.getResponseCode());

				ServicesDataException servicesDataException = new ServicesDataException();
				servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
				servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
				throw servicesDataException;
			}

			return serviceResponse;
		} else if (method.equalsIgnoreCase(HTTPConstants.POST)) {
			LOG.info(" Post Service " + (BASE_URL + serviceName) + ":::::" + payLoad);
			serviceResponse = httpClient.executePOST(BASE_URL + serviceName, payLoad);
			System.out.println(
					" SERVICE RESSSSSSSSAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB " + serviceResponse.getResponseCode());
			if (serviceResponse == null) {

			} else if (serviceResponse != null
					&& (!serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_SUCCESS))) {
				ServicesDataException servicesDataException = new ServicesDataException();
				servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
				servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
				throw servicesDataException;
			}
			return serviceResponse;
		} else {
			ServicesDataException servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		}
	}

}
//public ServiceResponse execute(String serviceName, String payLoad, String method) throws ServicesDataException {
//	HttpClient httpClient = new HttpClient();
//	System.out.println("servicename:" + serviceName);
//	System.out.println("payload" + payLoad);
//	System.out.println("method" + method);
//	ServiceResponse serviceResponse = null;
//	if (method == null) {
//		ServicesDataException servicesDataException = new ServicesDataException();
//		servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
//		servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
//		throw servicesDataException;
//	} else if (method.equalsIgnoreCase(HTTPConstants.GET)) {
//		serviceResponse = httpClient.executeGET(BASE_URL + serviceName);
//		System.out.println(" SERVICE RESSSSSSSSSSSSSSSSSSSSS" + serviceResponse.getResponseCode());
//		System.out.println(" CHECK THIS    " + serviceResponse.getResponseCode());
//		if (serviceResponse == null) {
//
//		} else if (serviceResponse != null
//				&& (!serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_OK)
//						|| !serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_SUCCESS))) {
//			System.out.println(" CHECK THIS    " + serviceResponse);
//			System.out.println(" SERVICE AAAAAAAAAAAAAAAAAAAAAAAAAAAA " + serviceResponse.getResponseCode());
//
//			ServicesDataException servicesDataException = new ServicesDataException();
//			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
//			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
//			throw servicesDataException;
//		}
//
//		return serviceResponse;
//	} else if (method.equalsIgnoreCase(HTTPConstants.POST)) {
//		LOG.info(" Post Service " + (BASE_URL + serviceName) + ":::::" + payLoad);
//		serviceResponse = httpClient.executePOST(BASE_URL + serviceName, payLoad);
//		System.out.println(" SERVICE RESSSSSSSSSSSSSSSSSSSSSSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB "
//				+ serviceResponse.getResponseCode());
//		if (serviceResponse == null) {
//
//		} else if (serviceResponse != null
//				&& (!serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_SUCCESS))) {
//			ServicesDataException servicesDataException = new ServicesDataException();
//			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
//			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
//			throw servicesDataException;
//		}
//		return serviceResponse;
//	} else {
//		ServicesDataException servicesDataException = new ServicesDataException();
//		servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
//		servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
//		throw servicesDataException;
//	}
//}
