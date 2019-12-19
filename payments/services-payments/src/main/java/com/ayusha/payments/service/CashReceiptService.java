package com.ayusha.payments.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ayusha.job.specification.SearchSpecification;
import com.ayusha.payments.data.models.CashReceiptDataModel;
import com.ayusha.payments.services.entity.CashReceiptEntity;
import com.ayusha.payments.services.repository.ICashReceiptRepository;
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
public class CashReceiptService implements ICashReceiptService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(CashReceiptService.class);

	/** repository **/
	@Autowired
	private ICashReceiptRepository iCashReceiptRepository;

	/**
	 * default constructor
	 */
	public CashReceiptService() {
		LOG.info("Cash Receipt Service Constructor");
	}

	/**
	 * @method save
	 * @param cashReceiptDataModel
	 * @return cashReceiptDataModel
	 */
	public CashReceiptDataModel save(CashReceiptDataModel cashReceiptDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		DateTimeFormatter generatedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		CashReceiptEntity cashReceiptEntity = new CashReceiptEntity();

		cashReceiptEntity.setGeneratedOn(generatedDate.format(now));
		cashReceiptEntity.setJobCode(cashReceiptDataModel.getJobCode());
		cashReceiptEntity.setCouponCode(cashReceiptDataModel.getCouponCode());
		cashReceiptEntity.setSubTotal(cashReceiptDataModel.getSubTotal());
		cashReceiptEntity.setGrandTotal(cashReceiptDataModel.getGrandTotal());
		cashReceiptEntity.setPaidStatus(cashReceiptDataModel.getPaidStatus());
		cashReceiptEntity.setRequestedBy(cashReceiptDataModel.getRequestedBy());
		iCashReceiptRepository.save(cashReceiptEntity);
		return cashReceiptDataModel;
	}

	/**
	 * @method getAll
	 * @param Jobcode
	 * @return cashReceiptEntity
	 */
	@Override
	public CashReceiptEntity getAll(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		// TODO Auto-generated method stub

		CashReceiptEntity cashReceiptEntity = iCashReceiptRepository.requestedItemsByJobId(jobCode);
		return cashReceiptEntity;
	}

	/**
	 * @method getAllInvoice
	 * @return page
	 */
	@Override
	public Page<CashReceiptEntity> getAllInvoice()
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		Page<CashReceiptEntity> page = iCashReceiptRepository
				.findAll(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "generatedOn")));
		return page;
	}

 

	/**
	 * search
	 */
	@Override
	public Page<CashReceiptDataModel> searchDetails(SearchSpecification userSpecification) {

		// pageable
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);
		List<CashReceiptEntity> cashEntities = iCashReceiptRepository.findAll(userSpecification);

		List<CashReceiptDataModel> lstCashDataModel = new ArrayList<CashReceiptDataModel>();

		CashReceiptDataModel cashReceiptDataModel = null;

		CashReceiptEntity cashReceiptEntity = null;
		int size = cashEntities.size();

		for (int index = 0; index < size; index++) {

			List<CashReceiptDataModel> userlst = new ArrayList<CashReceiptDataModel>();
			cashReceiptEntity = new CashReceiptEntity();
			cashReceiptDataModel = new CashReceiptDataModel();
			cashReceiptEntity = cashEntities.get(index);
			cashReceiptDataModel.setCouponCode(cashReceiptEntity.getCouponCode());
			cashReceiptDataModel.setGeneratedOn(cashReceiptEntity.getGeneratedOn());
			cashReceiptDataModel.setGrandTotal(cashReceiptEntity.getGrandTotal());
			cashReceiptDataModel.setJobCode(cashReceiptEntity.getJobCode());
			cashReceiptDataModel.setRequestedBy(cashReceiptEntity.getRequestedBy());
			cashReceiptDataModel.setSubTotal(cashReceiptEntity.getSubTotal());
			cashReceiptDataModel.setPaidStatus(cashReceiptEntity.getPaidStatus());
			lstCashDataModel.add(cashReceiptDataModel);

		}
		Page<CashReceiptDataModel> result = new PageImpl<CashReceiptDataModel>(lstCashDataModel);
		return result;
	}

}
