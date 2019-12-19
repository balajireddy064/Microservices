package com.ayusha.payments.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.payments.data.models.EstimateCashReceiptLineItemDataModel;
import com.ayusha.payments.data.models.EstimateCashReceiptLineItemsDataModel;
import com.ayusha.payments.services.entity.EstimateCashReceiptLineItemEntity;
import com.ayusha.payments.services.repository.IEstimationCashReceiptLineItemsRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch
 *
 */
@Service
public class EstimateCashReceiptLineItemService implements IEstimateCashReceiptLineItemService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(EstimateCashReceiptLineItemService.class);

	/** repository **/
	@Autowired
	private IEstimationCashReceiptLineItemsRepository iEstimationCashReceiptLineItemsRepository;

	/**
	 * default constructor
	 */
	public EstimateCashReceiptLineItemService() {
		LOG.info("Estimate Cash Receipt Line Item Service");
	}

	/** generated randum number like 001 **/
	private long value = 000;

	/** this method incriment the number **/
	private String getNextSequenceNumber() {
		value++;
		String seq_num = String.format("%03d", value);
		return seq_num;
	}

	/**
	 * @method add
	 * @param estimateCashReceiptLineItemsDataModel
	 * @return estimateCashReceiptLineItemsDataModel
	 */

	@Override
	public EstimateCashReceiptLineItemsDataModel add(
			EstimateCashReceiptLineItemsDataModel estimateCashReceiptLineItemsDataModel) {

		EstimateCashReceiptLineItemEntity estimateCashReceiptLineItemEntity = null;
		DateFormat loggedDate = new SimpleDateFormat("ddMMYY-HH:mm:ss");

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyHHmmss-");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		EstimateCashReceiptLineItemDataModel estimateCashReciptLineItemDataModel = null;
		List<EstimateCashReceiptLineItemDataModel> listLineItems = estimateCashReceiptLineItemsDataModel.getLineItems();
		int size = listLineItems.size();
		estimateCashReceiptLineItemsDataModel
				.setEstimateId("ATAS-EST-" + dateFormat.format(now) + getNextSequenceNumber());
		estimateCashReceiptLineItemsDataModel.setLoggedOn(dtf.format(now));
		for (int i = 0; i < size; i++) {
			estimateCashReciptLineItemDataModel = listLineItems.get(i);
			estimateCashReceiptLineItemEntity = new EstimateCashReceiptLineItemEntity();
			estimateCashReceiptLineItemEntity.setEstimateId(estimateCashReceiptLineItemsDataModel.getEstimateId());
			estimateCashReceiptLineItemEntity.setJobCode(estimateCashReceiptLineItemsDataModel.getJobCode());
			estimateCashReceiptLineItemEntity.setLoggedOn(dtf.format(now));
			estimateCashReceiptLineItemEntity.setItemId(estimateCashReciptLineItemDataModel.getItemId());
			estimateCashReceiptLineItemEntity.setAmount(estimateCashReciptLineItemDataModel.getAmount());
			estimateCashReceiptLineItemEntity.setQuantity(estimateCashReciptLineItemDataModel.getQuantity());
			estimateCashReceiptLineItemEntity.setTax(estimateCashReciptLineItemDataModel.getTax());
			estimateCashReceiptLineItemEntity.setType(estimateCashReciptLineItemDataModel.getType());
			iEstimationCashReceiptLineItemsRepository.save(estimateCashReceiptLineItemEntity);
		}
		return estimateCashReceiptLineItemsDataModel;

	}

	/**
	 * @method getItemsByJobCode
	 * @param estimateId
	 * @return lstItems
	 */
	@Override
	public List<EstimateCashReceiptLineItemEntity> getItemsByJobCode(String estimateId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		List<EstimateCashReceiptLineItemEntity> lstItems = iEstimationCashReceiptLineItemsRepository
				.findItemsByJobCode(estimateId);
		return lstItems;

	}
}
