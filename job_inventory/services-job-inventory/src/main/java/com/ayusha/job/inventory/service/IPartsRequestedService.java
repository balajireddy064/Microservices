package com.ayusha.job.inventory.service;

import java.util.List;

import com.ayusha.job.inventory.data.models.PartRequestedDataModel;
import com.ayusha.job.inventory.data.models.PartReturnedDataModel;
import com.ayusha.job.inventory.data.models.PartsIssuedDataModel;
import com.ayusha.job.inventory.data.models.PartsRequestedDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface IPartsRequestedService {

	/** add ticket **/
	public PartsRequestedDataModel save(PartsRequestedDataModel partsRequestedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** find job notes by id **/
	public PartsRequestedDataModel getPartsRequestedDataModel(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** find job notes by id **/
	public List<PartRequestedDataModel> getPartsRequested(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** add ticket **/
	public void deletePartsRequested(String jobId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** add ticket **/
	public PartRequestedDataModel updatePartsRequested(PartRequestedDataModel partsRequestedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	

}
