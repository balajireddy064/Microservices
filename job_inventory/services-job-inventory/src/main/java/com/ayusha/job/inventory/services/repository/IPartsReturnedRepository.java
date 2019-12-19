package com.ayusha.job.inventory.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.job.inventory.services.entity.PartReturnedDataEntity;
import com.ayusha.job.inventory.services.entity.PartReturnedEntity;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticketmanagement persistance CRUD operations
 *
 */
public interface IPartsReturnedRepository extends JpaRepository<PartReturnedDataEntity, Integer> {

	@Query("SELECT t FROM PartReturnedDataEntity t WHERE t.jobId = ?1")
	List<PartReturnedDataEntity> findAllReturnedItemsByJobId(String jobId);

	@Query("delete FROM PartReturnedEntity t WHERE t.jobId = ?1")
	void deleteReturnedItemsByJobId(String jobId);

	PartReturnedEntity findByJobId(String jobId);

	void save(PartReturnedEntity partReturnedEntity);

	@Query(value = "SELECT * FROM parts_returned WHERE parts_returned.job_id=?1 ORDER BY parts_returned.job_id DESC LIMIT 1", nativeQuery = true)
	PartReturnedDataEntity getByJobId(String jobCode);
}
