package com.ayusha.payments.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ayusha.payments.services.entity.CashReceiptEntity;
import com.ayusha.payments.services.entity.EstimateCashReceiptLineItemEntity;
import com.ayusha.payments.services.entity.EstimateEntity;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticketmanagement persistance CRUD operations
 *
 */
public interface IEstimationCashReceiptLineItemsRepository
		extends CrudRepository<EstimateCashReceiptLineItemEntity, Integer> {

	@Query(value="SELECT * FROM line_items WHERE line_items.estimate_id=?1 ORDER BY line_items.estimate_id DESC", nativeQuery = true)
	List<EstimateCashReceiptLineItemEntity> findItemsByJobCode(String estimateId);

 
}
