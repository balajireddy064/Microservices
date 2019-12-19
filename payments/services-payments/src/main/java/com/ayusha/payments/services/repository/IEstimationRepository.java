package com.ayusha.payments.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ayusha.payments.services.entity.CashReceiptEntity;
import com.ayusha.payments.services.entity.EstimateEntity;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticketmanagement persistance CRUD operations
 *
 */
public interface IEstimationRepository
		extends JpaRepository<EstimateEntity, Integer>, JpaSpecificationExecutor<EstimateEntity> {

	@Query(value = "SELECT * FROM estimates WHERE estimates.job_code=?1 ORDER BY estimates.estimate_id DESC LIMIT 1", nativeQuery = true)
	List<EstimateEntity> findEstimateForJobCodeTime(@Param("jobCode") String jobCode);

	List<EstimateEntity> findByRequestedBy(String requestedBy);

	EstimateEntity findByJobCode(String jobCode);

}
