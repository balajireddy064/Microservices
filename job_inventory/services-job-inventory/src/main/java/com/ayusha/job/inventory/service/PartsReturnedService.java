package com.ayusha.job.inventory.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.job.inventory.data.models.PartReturnedDataModel;
import com.ayusha.job.inventory.data.models.PartsReturnedDataModel;
import com.ayusha.job.inventory.services.entity.PartReturnedDataEntity;
import com.ayusha.job.inventory.services.entity.PartReturnedEntity;
import com.ayusha.job.inventory.services.repository.IPartsReturnedRepository;
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
public class PartsReturnedService implements IReturnedItemsService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(PartsReturnedService.class);

	/** repository **/
	@Autowired
	private IPartsReturnedRepository iPartsReturnedRepository;

	/**
	 * default constructor
	 */
	public PartsReturnedService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * @method save
	 * @param PartsReturnedDataModel
	 * @return partsReturnedDataModel
	 */
	public PartsReturnedDataModel save(PartsReturnedDataModel partsReturnedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		// system generated time and date

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		PartReturnedDataEntity partReturnedEntity = null;
		PartReturnedDataModel partReturnedDataModel = null;
		int size = 0;

		List<PartReturnedDataModel> lstPartReturnedDataModel = partsReturnedDataModel.getLstPartsReturnedDataModel();

		size = lstPartReturnedDataModel.size();

		try {
			for (int index = 0; index < size; index++) {
				partReturnedDataModel = lstPartReturnedDataModel.get(index);
				partReturnedEntity = new PartReturnedDataEntity();
				partReturnedEntity.setId(partReturnedDataModel.getId());
				partReturnedEntity.setJobId(partReturnedDataModel.getJobId());
				partReturnedEntity.setMedia(partReturnedDataModel.getMedia());
				partReturnedEntity.setQuantity(partReturnedDataModel.getQuantity());
				partReturnedEntity.setRemarks(partReturnedDataModel.getRemarks());
				partReturnedEntity.setType(partReturnedDataModel.getType());
				partReturnedEntity.setReturnedOn(dtf.format(now));
				partReturnedEntity.setVerified('N');

				System.out.println(
						" GGGGGGGGGGGGGGGGGGGGGGGG " + (JSONConverter.convertPOJOToString(partReturnedEntity)));
				iPartsReturnedRepository.save(partReturnedEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partsReturnedDataModel;
	}

	/**
	 * @method getPartsReturnedDataModel
	 * @param jobId
	 * @return partsReturnedDataModel
	 */
	public PartsReturnedDataModel getPartsReturnedDataModel(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		PartsReturnedDataModel partsReturnedDataModel = new PartsReturnedDataModel();
		partsReturnedDataModel.setJobId(jobId);
		partsReturnedDataModel.setLstPartsReturnedDataModel(getPartsReturned(jobId));
		return partsReturnedDataModel;
	}

	/**
	 * @method getPartsReturned
	 * @param jobId
	 * @return lstPartReturnedDataModel
	 */
	public List<PartReturnedDataModel> getPartsReturned(String jobId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		List<PartReturnedDataModel> lstPartReturnedDataModel = new ArrayList();
		PartReturnedDataModel partReturnedDataModel = null;
		PartReturnedDataEntity partReturnedEntity = null;
		int size = 0;

		List<PartReturnedDataEntity> lstPartReturnedEntity = iPartsReturnedRepository
				.findAllReturnedItemsByJobId(jobId);

		size = lstPartReturnedEntity.size();

		for (int index = 0; index < size; index++) {
			partReturnedDataModel = new PartReturnedDataModel();
			partReturnedEntity = lstPartReturnedEntity.get(index);
			partReturnedDataModel.setId(Integer.parseInt("" + partReturnedEntity.getId()));
			partReturnedDataModel.setJobId(partReturnedEntity.getJobId());
			partReturnedDataModel.setPartId(partReturnedEntity.getPartid());
			partReturnedDataModel.setMedia(partReturnedEntity.getMedia());
			partReturnedDataModel.setHeading(partReturnedEntity.getRemarks());
			partReturnedDataModel.setQuantity(partReturnedEntity.getQuantity());
			partReturnedDataModel.setType(partReturnedEntity.getType());
			lstPartReturnedDataModel.add(partReturnedDataModel);

		}

		return lstPartReturnedDataModel;
	}

	/**
	 * @method deletePartsReturned
	 * @param jobId
	 */
	public void deletePartsReturned(String jobId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		iPartsReturnedRepository.deleteReturnedItemsByJobId(jobId);
	}

	/**
	 * @method updatePartsReturned
	 * @param PartReturnedDataModel
	 * @return partReturnedDataModel
	 */
	public PartReturnedDataModel updatePartsReturned(PartReturnedDataModel partReturnedDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		PartReturnedEntity partReturnedEntity = iPartsReturnedRepository.findByJobId(partReturnedDataModel.getJobId());
		partReturnedEntity.setMedia(partReturnedDataModel.getMedia());
		partReturnedEntity.setPartid(partReturnedDataModel.getPartId());
		partReturnedEntity.setQuantity(partReturnedDataModel.getQuantity());
		partReturnedEntity.setType(partReturnedDataModel.getType());

		iPartsReturnedRepository.save(partReturnedEntity);
		return partReturnedDataModel;
	}

	public PartReturnedDataModel getByJobId(String jobCode) throws ResourceNotFoundException {
		PartReturnedDataEntity partReturnedEntity = iPartsReturnedRepository.getByJobId(jobCode);
		PartReturnedDataModel partReturnedDataModel = new PartReturnedDataModel();
		if (partReturnedEntity != null) {
			partReturnedDataModel.setJobId(partReturnedEntity.getJobId());
			partReturnedDataModel.setRemarks(partReturnedEntity.getRemarks());
			partReturnedDataModel.setQuantity(partReturnedEntity.getQuantity());
			partReturnedDataModel.setType(partReturnedEntity.getType());
			return partReturnedDataModel;
		} else {
			partReturnedDataModel.setHeading("");
			partReturnedDataModel.setJobId("");
			partReturnedDataModel.setPartId("");
			partReturnedDataModel.setQuantity(0);
			partReturnedDataModel.setRemarks("item not returned");
			partReturnedDataModel.setType("");
			partReturnedDataModel.setReturnedOn("");
			return partReturnedDataModel;
			// throw new ResourceNotFoundException(" not partReturned ");
		}

	}

}
