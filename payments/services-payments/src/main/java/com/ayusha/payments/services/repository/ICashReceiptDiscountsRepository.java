package com.ayusha.payments.services.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ayusha.payments.services.entity.CashReceiptDiscountsEntity;
import com.ayusha.payments.services.entity.CashReceiptEntity;
import com.ayusha.payments.services.entity.EstimateEntity;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticketmanagement persistance CRUD operations
 *
 */
public interface ICashReceiptDiscountsRepository extends CrudRepository<CashReceiptDiscountsEntity, Integer> {

	/** find discount amount **/
	@Query("SELECT t FROM CashReceiptDiscountsEntity t WHERE t.couponCode = ?1")
	CashReceiptDiscountsEntity findById(String couponCode);

}
