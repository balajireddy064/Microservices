package com.ayusha.services.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ayusha.services.common.constants.GeneralConstants;
import com.ayusha.services.common.error.models.ServiceError;

public class ServiceExceptionHandler {
	
	
	
	/**
	 * default constructor
	 */
	public ServiceExceptionHandler() {
		
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
		serviceError.setErrorCode(""+HttpStatus.INTERNAL_SERVER_ERROR.value());
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
	public ResponseEntity<ServiceError> handleBadRequestException(NoContentException noContentException) {
		ServiceError serviceError = new ServiceError();
		serviceError.setErrorCode(""+HttpStatus.NO_CONTENT.value());
		serviceError.setErrorDescription(noContentException.getMessage());
		serviceError.setErrorType(GeneralConstants.APPLICATION_ERROR_TYPE);
		return new ResponseEntity<ServiceError>(serviceError,HttpStatus.NO_CONTENT);
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
