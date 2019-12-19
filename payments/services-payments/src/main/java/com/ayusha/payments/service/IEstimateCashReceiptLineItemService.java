package com.ayusha.payments.service;

import java.util.List;

import com.ayusha.payments.data.models.EstimateCashReceiptLineItemDataModel;
import com.ayusha.payments.data.models.EstimateCashReceiptLineItemsDataModel;
import com.ayusha.payments.data.models.EstimatesDataModel;
import com.ayusha.payments.services.entity.EstimateCashReceiptLineItemEntity;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface IEstimateCashReceiptLineItemService {

	/** add line items **/
	public EstimateCashReceiptLineItemsDataModel add(
			EstimateCashReceiptLineItemsDataModel estimateCashReceiptLineItemsDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get line items **/
	public List<EstimateCashReceiptLineItemEntity> getItemsByJobCode(String estimateId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;
}
