package com.ayusha.services.common.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ayusha.services.common.constants.GeneralConstants;
import com.ayusha.services.common.error.models.ServiceError;

public class ServiceRequestProcessor {


	
	/**
	 * default constructor
	 */
	public ServiceRequestProcessor() {
		
	}
	
	/**
	 * process request
	 */
	public String preProcess(String requestBody, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		return requestBody;
	}
	
	/**
	 * process request
	 */
	public String postProcess(String responseBody, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		return responseBody;
	}
	
	/**
	 * general error handling
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ServiceError handleGeneralException(Exception exception) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(""+HttpStatus.INTERNAL_SERVER_ERROR.value());
		serviceError.setErrorDescription(exception.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return serviceError;
	}
	
	/**
	 * general error handling
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidServiceRequestException.class)
	public ServiceError handleBadRequestException(InvalidServiceRequestException invalidServiceRequestException) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(""+HttpStatus.BAD_REQUEST.value());
		serviceError.setErrorDescription(invalidServiceRequestException.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return serviceError;
	}
	
	/**
	 * general error handling
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ServiceError handleBadRequestException(ResourceNotFoundException resourceNotFoundException) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(""+HttpStatus.NOT_FOUND.value());
		serviceError.setErrorDescription(resourceNotFoundException.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return serviceError;
	}
	
	/**
	 * general error handling
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(NoContentException.class)
	public ServiceError handleBadRequestException(NoContentException noContentException) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(""+HttpStatus.NO_CONTENT.value());
		serviceError.setErrorDescription(noContentException.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return serviceError;
	}
	
	/**
	 * general error handling
	 */
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(AcceptedException.class)
	@ResponseBody
	public ServiceError handleBadRequestException(AcceptedException noContentException) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(""+HttpStatus.ACCEPTED.value());
		serviceError.setErrorDescription(noContentException.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return serviceError;
	}
	
	/**
	 * general error handling
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(NotContentException.class)
	public ServiceError handleBadRequestException(NotContentException noContentException) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode("");
		serviceError.setErrorDescription(noContentException.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return serviceError;
	}
}
