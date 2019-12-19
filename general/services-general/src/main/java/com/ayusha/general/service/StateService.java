package com.ayusha.general.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.general.services.data.model.CityDataModel;
import com.ayusha.general.services.data.model.StateDataModel;
import com.ayusha.general.services.data.model.StatesDataModel;
import com.ayusha.general.services.entity.CityEntity;
import com.ayusha.general.services.entity.StateEntity;
import com.ayusha.general.services.repository.ICityRepository;
import com.ayusha.general.services.repository.IStateRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class
 * 
 *         1. Recording (single): a. Persisting in DB b. on Success - sending an
 *         email/sms to customer c. assigning service invoking
 * 
 *         2. Update: a. On change of status - sending an email/sms notification
 * 
 *         3. Batch Recording: a. Persisting in DB b. on Success - sending an
 *         email/sms to customer -seggregating and sending an single email c.
 *         assigning service invoking - Individually
 * 
 *         4. Search: a. search based on date, user, customer,logged date,
 *         issue,servicetype,serialnumber
 * 
 *         5. Sorting: a. soring based on logged date,status,servicetype (ASC |
 *         DSC)
 */
@Service
public class StateService implements IStateService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(StateService.class);

	/** repository **/
	@Autowired
	private IStateRepository iStateRepository;

	@Autowired
	private ICityRepository iCityRepositry;

	/**
	 * default constructor
	 */
	public StateService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * add ticket
	 */
	public StateDataModel save(StateDataModel stateDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		StateEntity stateEntity = new StateEntity();
		stateEntity.setStateCode(stateDataModel.getStateCode());
		stateEntity.setCountryCode(stateDataModel.getCountryCode());
		stateEntity.setName(stateDataModel.getName());
		iStateRepository.save(stateEntity);
		return stateDataModel;
	}

	/** find job notes by id **/
	public List<StateDataModel> getState() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		StateDataModel stateDataModel = new StateDataModel();
		List<StateEntity> lstStateEntity = iStateRepository.findAllStates();
		List<StateDataModel> lstStateDataModel = new ArrayList();
		StateEntity stateEntity = null;
		
		int size = 0;

		size = lstStateEntity.size();
		for (int index = 0; index < size; index++) {
			stateDataModel = new StateDataModel();
			stateEntity = lstStateEntity.get(index);
			stateDataModel.setId(Integer.parseInt("" + stateEntity.getId()));
			stateDataModel.setCountryCode(stateEntity.getCountryCode());
			stateDataModel.setName(stateEntity.getName());
			stateDataModel.setStateCode(stateEntity.getStateCode());
			lstStateDataModel.add(stateDataModel);
		}
		
		return lstStateDataModel;
//		stateDataModel.setCountryCode(stateEntity.getCountryCode());
//		stateDataModel.setName(stateEntity.getName());
//		stateDataModel.setStateCode(stateEntity.getStateCode());
//		List lst = new ArrayList();
//
//		List<CityEntity> lstCity = iCityRepositry.findAll();
//		lst.add(stateDataModel);
//		lst.add(lstCity);
//
//		return lst;
	}

	/** find job notes by id **/
	public StatesDataModel getStates(String countryId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		StateEntity stateEntity = null;
		StateDataModel stateDataModel = new StateDataModel();
		CityDataModel cityDataModel = new CityDataModel();

		List<StateEntity> lstStateEntities = iStateRepository.findStatesByCountryId(countryId);
		// List<CityEntity>lstCity=iCityRepositry.findByStateCode(lstStateEntities.)

		List lstStateDataModel = new ArrayList();
		int size = 0;
		size = lstStateEntities.size();

		for (int index = 0; index < size; index++) {
			stateDataModel = new StateDataModel();
			stateEntity = lstStateEntities.get(index);
			CityEntity cityEntity = new CityEntity();

			// int size1 = 0;
			List<CityEntity> lstCity = iCityRepositry.findByStateCode(stateEntity.getStateCode());

			stateDataModel.setCountryCode(stateEntity.getCountryCode());

			System.out.println(
					stateEntity.getStateCode() + "====================================" + stateEntity.getStateCode());
			stateDataModel.setName(stateEntity.getName());
			stateDataModel.setStateCode(stateEntity.getStateCode());
			// stateDataModel.getLstCityDataModel();

			lstStateDataModel.add(stateDataModel);
			lstStateDataModel.add(lstCity);

		}

		StatesDataModel statesDataModel = new StatesDataModel();
		statesDataModel.setLstStatesDataModel(lstStateDataModel);

		return statesDataModel;
	}

}
