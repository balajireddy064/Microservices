package com.ayusha.job.inventory.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.job.inventory.data.models.PartRequestedDataModel;
import com.ayusha.job.inventory.data.models.PartsRequestedDataModel;
import com.ayusha.job.inventory.services.entity.PartRequestedEntity;
import com.ayusha.job.inventory.services.repository.IPartsRequestedRepository;
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
public class PartsRequestedService implements IPartsRequestedService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(PartsRequestedService.class);

	/** repository **/
	@Autowired
	private IPartsRequestedRepository iPartsRequestedRepository;

	/**
	 * default constructor
	 */
	public PartsRequestedService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * @method save
	 * @param PartsRequestedDataModel
	 * @return partsRequestedDataModel
	 */
	public PartsRequestedDataModel save(PartsRequestedDataModel partsRequestedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		PartRequestedEntity partRequestedEntity = null;
		PartRequestedDataModel partRequestedDataModel = null;
		int size = 0;

		List<PartRequestedDataModel> lstPartRequestedDataModel = partsRequestedDataModel
				.getLstPartsRequestedDataModel();

		size = lstPartRequestedDataModel.size();

		try {
			for (int index = 0; index < size; index++) {
				partRequestedDataModel = lstPartRequestedDataModel.get(index);
				partRequestedEntity = new PartRequestedEntity();
				partRequestedEntity.setId(partRequestedDataModel.getId());
				partRequestedEntity.setJobId(partRequestedDataModel.getJobId());
				partRequestedEntity.setStatus(partRequestedDataModel.getStatus());
				partRequestedEntity.setPartId(partRequestedDataModel.getPartid());
				partRequestedEntity.setQuantity(partRequestedDataModel.getRequestedQuantity());
				partRequestedEntity.setType(partRequestedDataModel.getType());
				partRequestedEntity.setIssuedOn(dtf.format(now));
				System.out.println(
						" GGGGGGGGGGGGGGGGGGGGGGGG " + (JSONConverter.convertPOJOToString(partRequestedEntity)));
				iPartsRequestedRepository.save(partRequestedEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partsRequestedDataModel;
	}

	/**
	 * @method getPartsRequestedDataModel
	 * @param jobId
	 * @return partsRequestedDataModel
	 **/
	public PartsRequestedDataModel getPartsRequestedDataModel(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		PartsRequestedDataModel partsRequestedDataModel = new PartsRequestedDataModel();
		partsRequestedDataModel.setJobId(jobId);
		partsRequestedDataModel.setLstPartsRequestedDataModel(getPartsRequested(jobId));
		return partsRequestedDataModel;
	}

	/**
	 * @method getPartsRequested
	 * @param jobId
	 **/
	public List<PartRequestedDataModel> getPartsRequested(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		List<PartRequestedDataModel> lstPartRequestedDataModel = new ArrayList();
		PartRequestedDataModel partRequestedDataModel = null;
		PartRequestedEntity partRequestedEntity = null;
		int size = 0;

		List<PartRequestedEntity> lstPartRequestedEntity = iPartsRequestedRepository
				.findAllRequestedItemsByJobId(jobId);

		size = lstPartRequestedEntity.size();

		for (int index = 0; index < size; index++) {
			partRequestedDataModel = new PartRequestedDataModel();
			partRequestedEntity = lstPartRequestedEntity.get(index);
			partRequestedDataModel.setId(Integer.parseInt("" + partRequestedEntity.getId()));
			partRequestedDataModel.setStatus(partRequestedEntity.getStatus());
			partRequestedDataModel.setJobId(partRequestedEntity.getJobId());
			partRequestedDataModel.setPartid(partRequestedEntity.getPartId());
			partRequestedDataModel.setRequestedQuantity(partRequestedEntity.getQuantity());
			partRequestedDataModel.setType(partRequestedEntity.getType());
			partRequestedDataModel.setRequestedOn(partRequestedEntity.getIssuedOn());
			lstPartRequestedDataModel.add(partRequestedDataModel);

		}
		PartsRequestedDataModel partsRequestedDataModel = new PartsRequestedDataModel();

		return lstPartRequestedDataModel;
	}

	/**
	 * @method deletePartsRequested
	 * @param jobId
	 */
	public void deletePartsRequested(String jobId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		iPartsRequestedRepository.deleteRequestedItemsByJobId(jobId);
	}

	/**
	 * updated parts Requested
	 */
	public PartRequestedDataModel updatePartsRequested(PartRequestedDataModel partRequestedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		PartRequestedEntity partRequestedEntity = iPartsRequestedRepository
				.findByJobId(partRequestedDataModel.getJobId());
		partRequestedEntity.setQuantity(partRequestedDataModel.getRequestedQuantity());
		partRequestedEntity.setType(partRequestedDataModel.getType());
		partRequestedEntity.setStatus(partRequestedDataModel.getStatus());

		iPartsRequestedRepository.save(partRequestedEntity);
		return partRequestedDataModel;
	}
}
