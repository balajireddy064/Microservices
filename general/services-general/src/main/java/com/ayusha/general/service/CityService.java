package com.ayusha.general.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.general.services.data.model.CitiesDataModel;
import com.ayusha.general.services.data.model.CityDataModel;
import com.ayusha.general.services.entity.CityEntity;
import com.ayusha.general.services.repository.ICityRepository;
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
public class CityService implements ICityService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(StateService.class);

	/** repository **/
	@Autowired
	private ICityRepository iCityRepository;

	/**
	 * default constructor
	 */
	public CityService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * add ticket
	 */
	public CityDataModel save(CityDataModel cityDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		CityEntity cityEntity = new CityEntity();
		cityEntity.setCityCode(cityDataModel.getCityCode());
		cityEntity.setName(cityDataModel.getName());
		cityEntity.setStateCode(cityDataModel.getStateCode());
	
		iCityRepository.save(cityEntity);
		return cityDataModel;
	}

	/** find job notes by id **/
	public CityDataModel getCity(String cityId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		CityEntity cityEntity = iCityRepository.findCityByCityId(cityId);
		CityDataModel cityDataModel = new CityDataModel();
		cityDataModel.setStateCode(cityEntity.getStateCode());
		cityDataModel.setName(cityEntity.getName());
		cityDataModel.setCityCode(cityEntity.getCityCode());
		return cityDataModel;
	}

	/** find job notes by id **/
	public List<CityDataModel> getCities(String stateCode) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		CityEntity cityEntity = null;
		CityDataModel cityDataModel = new CityDataModel();
		List<CityEntity> lstCityEntities = iCityRepository.findByStateCode(stateCode);
		System.out.println("data from repository:::::::" +lstCityEntities);
		List<CityDataModel> lstCitiesDataModel = new ArrayList();
		int size = 0;
		size = lstCityEntities.size();

		for (int index = 0; index < size; index++) {
			cityDataModel = new CityDataModel();
			cityEntity = lstCityEntities.get(index);
			cityDataModel.setStateCode(cityEntity.getStateCode());
			cityDataModel.setName(cityEntity.getName());
			cityDataModel.setCityCode(cityEntity.getCityCode());
			lstCitiesDataModel.add(cityDataModel);
		}
		return lstCitiesDataModel;
	}

	/**
	 * update city
	 */
	public CityDataModel updateCity(CityDataModel cityDataModel) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		CityEntity cityEntity = iCityRepository.findByCityCode(cityDataModel.getCityCode());
		cityEntity.setCityCode(cityDataModel.getCityCode());
		cityEntity.setName(cityDataModel.getName());
		cityEntity.setStateCode(cityDataModel.getCityCode());
		iCityRepository.save(cityEntity);
		return cityDataModel;

	}
}
