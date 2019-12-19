package com.ayusha.services.common.requestpreprocessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ServiceExceptionHandler;

public class ServiceRequestHandler extends ServiceExceptionHandler{
	
	
	/**
	 * default constructor
	 */
	public ServiceRequestHandler() {
		
	}
	
	/**
	 * handle request pre-prossing
	 */
	public String preProcess(String requestModel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws InvalidServiceRequestException{
		
		/**if(httpServletRequest!=null && (httpServletRequest.getParameter("X-APPLICATION-ID")==null)){
			throw new InvalidServiceRequestException("No header data present for - X-APPLICATION-ID");
		}else if(httpServletRequest!=null && ((httpServletRequest.getParameter("X-APPLICATION-ID")!=null && httpServletRequest.getParameter("X-APPLICATION-ID").trim().length()<1))){
			throw new InvalidServiceRequestException("No header data present for - X-APPLICATION-ID");
		} */
		return requestModel;
	}
	
	/**
	 * request object
	 */
	public Object getRequestModelObject(String requestDataModel,Class requestDataModelClass) throws Exception{
		return JSONConverter.convertStringToPOJO(requestDataModel, requestDataModelClass);
		
	}
	/**
	 * handle request pre-prossing
	 */
	public String postProcess(String responseModel, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		return responseModel;
	}

}
