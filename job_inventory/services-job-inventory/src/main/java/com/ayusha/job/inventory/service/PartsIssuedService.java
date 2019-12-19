package com.ayusha.job.inventory.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.job.inventory.data.models.PartIssuedDataModel;
import com.ayusha.job.inventory.data.models.PartsIssuedDataModel;
import com.ayusha.job.inventory.services.entity.PartIssuedEntity;
import com.ayusha.job.inventory.services.repository.IPartsIssuedRepository;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class
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
public class PartsIssuedService implements IPartsIssuedService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(PartsIssuedService.class);

	/** repository **/
	@Autowired
	private IPartsIssuedRepository iPartsIssuedRepository;

	/**
	 * default constructor
	 */
	public PartsIssuedService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * @method save
	 * @param PartsIssuedDataModel
	 * @return artsIssuedDataModel
	 */
	public PartsIssuedDataModel save(PartsIssuedDataModel artsIssuedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		// system generater time and time
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/YY-HH:mm:ss");
		Date date = new Date();

		PartIssuedEntity partIssuedEntity = null;
		PartIssuedDataModel partIssuedDataModel = null;
		int size = 0;

		List<PartIssuedDataModel> lstPartIssuedDataModel = artsIssuedDataModel.getLstPartsIssuedDataModel();

		size = lstPartIssuedDataModel.size();

		try {
			for (int index = 0; index < size; index++) {
				partIssuedDataModel = lstPartIssuedDataModel.get(index);
				partIssuedEntity = new PartIssuedEntity();
				partIssuedEntity.setId(partIssuedDataModel.getId());
				partIssuedEntity.setJobId(partIssuedDataModel.getJobId());
				partIssuedEntity.setQuantity(partIssuedDataModel.getQuantity());
				partIssuedEntity.setType(partIssuedDataModel.getType());
				partIssuedEntity.setIssuedOn(dateFormat1.format(date));
				System.out
						.println(" GGGGGGGGGGGGGGGGGGGGGGGG " + (JSONConverter.convertPOJOToString(partIssuedEntity)));
				iPartsIssuedRepository.save(partIssuedEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artsIssuedDataModel;
	}

	/**
	 * @method getPartsIssuedDataModel
	 * @return partsIssuedDataModel
	 * @param jobId
	 */
	public PartsIssuedDataModel getPartsIssuedDataModel(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		PartsIssuedDataModel partsIssuedDataModel = new PartsIssuedDataModel();
		partsIssuedDataModel.setJobId(jobId);
		partsIssuedDataModel.setLstPartsIssuedDataModel(getPartsIssued(jobId));
		return partsIssuedDataModel;
	}

	/**
	 * @method getPartsIssued
	 * @return lstPartIssuedDataModel
	 * @param jobId
	 */
	public List<PartIssuedDataModel> getPartsIssued(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		List<PartIssuedDataModel> lstPartIssuedDataModel = new ArrayList();
		PartIssuedDataModel partIssuedDataModel = null;
		PartIssuedEntity partIssuedEntity = null;
		int size = 0;

		List<PartIssuedEntity> lstPartIssuedEntity = iPartsIssuedRepository.findAllIssuedItemsByJobId(jobId);

		size = lstPartIssuedEntity.size();

		for (int index = 0; index < size; index++) {
			partIssuedDataModel = new PartIssuedDataModel();
			partIssuedEntity = lstPartIssuedEntity.get(index);
			partIssuedDataModel.setId(Integer.parseInt("" + partIssuedEntity.getId()));
			partIssuedDataModel.setJobId(partIssuedEntity.getJobId());
			partIssuedDataModel.setPartId(partIssuedEntity.getPartid());
			partIssuedDataModel.setQuantity(partIssuedEntity.getQuantity());
			partIssuedDataModel.setType(partIssuedEntity.getType());
			lstPartIssuedDataModel.add(partIssuedDataModel);

		}

		return lstPartIssuedDataModel;
	}

	/**
	 * @method deletePartsIssued
	 * @param jobId
	 */
	public void deletePartsIssued(String jobId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		iPartsIssuedRepository.deletePartsIssuedByJobId(jobId);
	}

	/**
	 * @method updatePartsIssued
	 * @param PartIssuedDataModel
	 * @return partIssuedDataModel
	 */
	public PartIssuedDataModel updatePartsIssued(PartIssuedDataModel partIssuedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		PartIssuedEntity partIssuedEntity = iPartsIssuedRepository.findByJobId(partIssuedDataModel.getJobId());
		partIssuedEntity.setPartid(partIssuedDataModel.getPartId());
		partIssuedEntity.setQuantity(partIssuedDataModel.getQuantity());
		partIssuedEntity.setType(partIssuedDataModel.getType());
		iPartsIssuedRepository.save(partIssuedEntity);
		return partIssuedDataModel;
	}

}
