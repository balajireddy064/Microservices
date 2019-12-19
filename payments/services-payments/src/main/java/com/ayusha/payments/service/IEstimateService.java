package com.ayusha.payments.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.ayusha.job.specification.EstimateSearchSpecificatoin;
import com.ayusha.payments.data.models.EstimateDataModel;
import com.ayusha.payments.data.models.EstimatesDataModel;
import com.ayusha.payments.services.entity.EstimateEntity;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface IEstimateService {

	/** Save estimate detail **/
	public EstimateDataModel save(EstimateDataModel estimateDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get estimate detail **/
	public EstimatesDataModel getEstimateForJobCodeTime(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get estimate detail **/
	public EstimatesDataModel getLastItem(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get all estimates **/
	public Page<EstimateEntity> getAllEstimates()
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get ByJobCode **/
	public EstimateEntity getEstimateByJobCode(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

	/** get All Requested **/
	public Page<EstimateDataModel> getByRequested(String requestedBy)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

	public Page<EstimateDataModel> searchDetails(EstimateSearchSpecificatoin userSpecification);

	/** excel information **/
	public String createExcelSheet(HttpServletResponse httpServletResponse) throws IOException;

//	public String createExcelSheetAllInformation(HttpServletResponse httpServletResponse) throws IOException;

}
