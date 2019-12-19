package com.ayusha.job.inventory.service;

import java.util.List;

import com.ayusha.job.inventory.data.models.PartReturnedDataModel;
import com.ayusha.job.inventory.data.models.PartsReturnedDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface IReturnedItemsService {

	/** add ticket **/
	public PartsReturnedDataModel save(PartsReturnedDataModel partsReturnedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** find job notes by id **/
	public PartsReturnedDataModel getPartsReturnedDataModel(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** find job notes by id **/
	public List<PartReturnedDataModel> getPartsReturned(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** delete By JobId **/
	public void deletePartsReturned(String jobId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** update **/
	public PartReturnedDataModel updatePartsReturned(PartReturnedDataModel partReturnedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;
	
	public PartReturnedDataModel getByJobId(String jobCode) throws ResourceNotFoundException;

}
