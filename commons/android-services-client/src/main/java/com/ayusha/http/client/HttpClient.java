package com.ayusha.http.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.ayusha.http.client.models.ServiceResponse;
import com.ayusha.http.constants.HTTPConstants;
import com.ayusha.services.common.exceptions.HttpServiceException;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;



/**
 * 
 * @author author
 * 07-Aug-2019
 * This class is an http client and connects to the services in the back-end
 */
public class HttpClient {
	
	/** Logger **/
	private static Logger Log = LogManager.getLogger(HttpClient.class);
	
	/** user id **/
	private String userId="";
	/** user id **/
	private String ticketId="";
	/** user id **/
	private String jobId="";
	
	/**
	 * default constructor
	 */
	public HttpClient() {
		
	}
	
	/**
	 * get
	 */
	public ServiceResponse executeGET(String url) throws ServicesDataException{ 
		HttpResponse httpResponse = null;
		BufferedReader bufferReader = null;
		StringBuffer result = null;
		int responseCode=-1;
		ServiceResponse serviceResponse = null;
		
		serviceResponse = new ServiceResponse();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		Log.info("Execurting the get resource "+url);
		try {
			// add request header
			httpGet.addHeader(HTTPConstants.HTTP_USER_AGENT_KEY, HTTPConstants.HTTP_USER_AGENT);
			preProcessHttpRequest(httpGet);
			httpResponse = client.execute(httpGet);
	
			Log.info("Response Codeeeeeeeeeeeeeeeeeeeeeeeeeewwwwwwwwwwwwww : " + httpResponse.getStatusLine().getStatusCode());
			responseCode =  httpResponse.getStatusLine().getStatusCode();
			
			serviceResponse.setResponseCode(""+responseCode);
	
			bufferReader = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
	
			result = new StringBuffer();
			String line = "";
			while ((line = bufferReader.readLine()) != null) {
				result.append(line);
			}
			serviceResponse.setResponseMessage(result.toString());
			serviceResponse.setResponse(result.toString());
			Log.info("Execurting the get resourcewwwwwwwwweeeeeeeeeeeeeeeeeeeeeeeeeeeeee <<response>>"+result);
		}catch(Exception e) {
			Log.exception(e);
			ServicesDataException servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		}
		
		return serviceResponse;
	}
	
	/**
	 * get
	 */
	public ServiceResponse executePOST(String url,String servicePayload) throws ServicesDataException{ 
		HttpResponse httpResponse = null;
		BufferedReader bufferReader = null;
		StringBuffer result = null;
		int responseCode=-1;
		ServiceResponse serviceResponse = null;
		
		serviceResponse = new ServiceResponse();
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		Log.info("Execurting the get resource "+url);
		try {
			// add request header
			httpPost.addHeader(HTTPConstants.HTTP_USER_AGENT_KEY, HTTPConstants.HTTP_USER_AGENT);
			httpPost.setEntity(new StringEntity(servicePayload));
			preProcessHttpRequest(httpPost);
			httpResponse = client.execute(httpPost);
			Log.info("Response Codeeeeeeeeeeeeeeeeeeeeeeeeee : " + httpResponse.getStatusLine().getStatusCode());
			responseCode =  httpResponse.getStatusLine().getStatusCode();
			serviceResponse.setResponseCode(""+responseCode);
	
			bufferReader = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
	
			result = new StringBuffer();
			String line = "";
			while ((line = bufferReader.readLine()) != null) {
				result.append(line);
			}
			serviceResponse.setResponseMessage(result.toString());
			Log.info("Execurtinggggggggggggggggggggggggggggggggggggg the get resource <<response>>"+result);
		}catch(Exception e) {
			Log.exception(e);
			ServicesDataException servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		}
		
		return serviceResponse;
	}

	/**
	 * pre-process request
	 */
	private void preProcessHttpRequest(HttpPost httpPost) throws HttpServiceException{
		
		httpPost.addHeader(HTTPConstants.USER_ID_KEY,userId);
		httpPost.addHeader(HTTPConstants.JOB_ID_KEY,jobId);
		httpPost.addHeader(HTTPConstants.TICKET_ID_KEY,ticketId);
	}
	
	/**
	 * pre-process request
	 */
	private void preProcessHttpRequest(HttpGet httpGet) throws HttpServiceException{
		httpGet.addHeader(HTTPConstants.USER_ID_KEY,userId);
		httpGet.addHeader(HTTPConstants.JOB_ID_KEY,jobId);
		httpGet.addHeader(HTTPConstants.TICKET_ID_KEY,ticketId);
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the ticketId
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
}
