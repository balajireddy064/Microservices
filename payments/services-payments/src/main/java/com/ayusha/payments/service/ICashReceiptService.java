package com.ayusha.payments.service;

import org.springframework.data.domain.Page;

import com.ayusha.job.specification.SearchSpecification;
import com.ayusha.payments.data.models.CashReceiptDataModel;
import com.ayusha.payments.services.entity.CashReceiptEntity;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the ticket
 *         service methods
 *
 */
public interface ICashReceiptService {

	/** save cash receipt details **/
	public CashReceiptDataModel save(CashReceiptDataModel cashReceiptDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get all based on job code **/
	public CashReceiptEntity getAll(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** get all **/

	public Page<CashReceiptEntity> getAllInvoice()
			throws DataPersistenceOperationException, InvalidServiceRequestException;

 

	public Page<CashReceiptDataModel> searchDetails(SearchSpecification userSpecification);
}
