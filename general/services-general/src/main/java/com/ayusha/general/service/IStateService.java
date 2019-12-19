package com.ayusha.general.service;

import java.util.List;

import com.ayusha.general.services.data.model.StateDataModel;
import com.ayusha.general.services.data.model.StatesDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface IStateService {

	/**
	 * add ticket
	 */
	public StateDataModel save(StateDataModel stateDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** find job notes by id **/
	public List<StateDataModel> getState() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** find job notes by id **/
	public StatesDataModel getStates(String countryId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;
}
