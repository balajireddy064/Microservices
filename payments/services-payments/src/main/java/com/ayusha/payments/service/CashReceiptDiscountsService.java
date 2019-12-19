package com.ayusha.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ayusha.payments.data.models.CashReceiptDiscountsDataModel;
import com.ayusha.payments.services.entity.CashReceiptDiscountsEntity;
import com.ayusha.payments.services.repository.ICashReceiptDiscountsRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
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
public class CashReceiptDiscountsService implements ICashReceiptDiscountsService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(CashReceiptService.class);

	/** repository **/
	@Autowired
	private ICashReceiptDiscountsRepository iCashReceiptDiscountsRepository;

	/**
	 * default constructor
	 */
	public CashReceiptDiscountsService() {
		LOG.info("Discounts service constructor");
	}

	/**
	 * @method save
	 * @param cashReceiptDiscountsDataModel
	 * @return cashReceiptDiscountsDataModel
	 * 
	 */
	public CashReceiptDiscountsDataModel save(CashReceiptDiscountsDataModel cashReceiptDiscountsDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CashReceiptDiscountsEntity cashReceiptDiscountsEntity = new CashReceiptDiscountsEntity();
		cashReceiptDiscountsEntity.setCouponCode(cashReceiptDiscountsDataModel.getCouponCode());
		cashReceiptDiscountsEntity.setAmount(cashReceiptDiscountsDataModel.getAmount());

		iCashReceiptDiscountsRepository.save(cashReceiptDiscountsEntity);
		return cashReceiptDiscountsDataModel;

	}

	/**
	 * @method findDiscountAmount
	 * @param couponCode
	 * @return cashReceiptDiscountsDataModel
	 */
	@Override
	public CashReceiptDiscountsDataModel findDiscountAmount(@RequestParam("couponCode") String couponCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		CashReceiptDiscountsDataModel cashReceiptDiscountsDataModel = new CashReceiptDiscountsDataModel();
		CashReceiptDiscountsEntity cashReceiptDiscountsEntity = iCashReceiptDiscountsRepository.findById(couponCode);
		cashReceiptDiscountsDataModel.setAmount(cashReceiptDiscountsEntity.getAmount());
		cashReceiptDiscountsDataModel.setCouponCode(cashReceiptDiscountsEntity.getCouponCode());
		return cashReceiptDiscountsDataModel;
	}

}