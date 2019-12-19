package com.ayusha.general.service;

import java.util.List;

import com.ayusha.general.services.data.model.CitiesDataModel;
import com.ayusha.general.services.data.model.CityDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface ICityService {

	/**
	 * add ticket
	 */
	public CityDataModel save(CityDataModel stateDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** find job notes by id **/
	public CityDataModel getCity(String cityId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** find job notes by id **/
	public List<CityDataModel> getCities(String stateCode) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;

	/** update city **/
	public CityDataModel updateCity(CityDataModel cityDataModel) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception;
}
