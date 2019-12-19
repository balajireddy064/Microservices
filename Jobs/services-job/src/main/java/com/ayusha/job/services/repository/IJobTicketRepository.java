package com.ayusha.job.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ayusha.job.services.entity.JobTicketEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Job Model class Defines the methods for the
 *         job management persistance CRUD operations
 *
 */
public interface IJobTicketRepository extends CrudRepository<JobTicketEntity, Integer> {

	@Query("SELECT t FROM JobTicketEntity t WHERE t.ticketId = ?1")
	JobTicketEntity findJobByTicketId(String ticketId);

	@Query("SELECT t FROM JobTicketEntity t WHERE t.ticketId = ?1")
	List<JobTicketEntity> findByJobIdByTicketId(String ticketId);

}
