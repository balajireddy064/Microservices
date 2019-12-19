package com.ayusha.payments.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.payments.services.entity.CashReceiptEntity;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticketmanagement persistance CRUD operations
 *
 */
public interface ICashReceiptRepository
		extends JpaRepository<CashReceiptEntity, Integer>, JpaSpecificationExecutor<CashReceiptEntity> {

	@Query("SELECT t FROM CashReceiptEntity t WHERE t.jobCode = ?1")
	CashReceiptEntity requestedItemsByJobId(String jobId);

	List<CashReceiptEntity> findByRequestedBy(String requestedBy);

	CashReceiptEntity findByJobCode(String jobCode);
}
