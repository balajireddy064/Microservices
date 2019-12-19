package com.ayusha.services.common.requestpreprocessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ayusha.services.common.exceptions.InvalidServiceRequestException;

public class ServiceRequestPreProcessor {
	
	/**
	 * default constructor
	 */
	public ServiceRequestPreProcessor() {
		
	}
	
	/**
	 * process request
	 */
	public void preProcess(String requestModel, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws InvalidServiceRequestException {
		
		if(httpServletRequest!=null && (httpServletRequest.getParameter("X-APPLICATION-ID")==null)){
			throw new InvalidServiceRequestException("No header data present for - X-APPLICATION-ID");
		}else if(httpServletRequest!=null && ((httpServletRequest.getParameter("X-APPLICATION-ID")!=null && httpServletRequest.getParameter("X-APPLICATION-ID").trim().length()<1))){
			throw new InvalidServiceRequestException("No header data present for - X-APPLICATION-ID");
		}
	}

}
