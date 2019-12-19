package com.ayusha.general.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.general.services.data.model.CountriesDataModel;
import com.ayusha.general.services.data.model.CountryDataModel;
import com.ayusha.general.services.data.model.StatesDataModel;
import com.ayusha.general.services.entity.CountryEntity;
import com.ayusha.general.services.entity.StateEntity;
import com.ayusha.general.services.repository.ICountryRepository;
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
public class CountryService implements ICountryService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(CountryService.class);

	/** repository **/
	@Autowired
	private ICountryRepository iCountryRepository;

	@Autowired
	private IStateRepository iStateRepositry;

	/**
	 * default constructor
	 */
	public CountryService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * add ticket
	 */
	public CountryDataModel save(CountryDataModel countryDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setCountryCode(countryDataModel.getCountryCode());
		countryEntity.setName(countryDataModel.getName());
		iCountryRepository.save(countryEntity);
		return countryDataModel;
	}

	/** find job notes by id **/
	public CountryDataModel getCountry(String countryId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		CountryEntity countryEntity = iCountryRepository.findCountryByIdId(countryId);
		CountryDataModel countryDataModel = new CountryDataModel();
		countryDataModel.setCountryCode(countryEntity.getCountryCode());
		countryDataModel.setName(countryEntity.getName());
		List list = new ArrayList();
		List<StateEntity> lst = iStateRepositry.findStatesByCountryId(countryId);
		return countryDataModel;
	}

	/** find job notes by id **/
	public CountriesDataModel getCountries() throws DataPersistenceOperationException, InvalidServiceRequestException,
			ResourceNotFoundException, Exception {
		CountryEntity countryEntity = null;
		CountryDataModel countryDataModel = new CountryDataModel();

		List<CountryEntity> lstCountryEntities = iCountryRepository.findAllCountries();

		List<CountryDataModel> lstCountryDataModel = new ArrayList();
		int size = 0;
		size = lstCountryEntities.size();

		for (int index = 0; index < size; index++) {
			countryDataModel = new CountryDataModel();
			countryEntity = lstCountryEntities.get(index);

			countryDataModel.setCountryCode(countryEntity.getCountryCode());
			countryDataModel.setName(countryEntity.getName());
			lstCountryDataModel.add(countryDataModel);

		}

		CountriesDataModel countriesDataModel = new CountriesDataModel();
		StatesDataModel statesDataModel = new StatesDataModel();
		countriesDataModel.setLstCountryDataModel(lstCountryDataModel);

		return countriesDataModel;
	}

}
