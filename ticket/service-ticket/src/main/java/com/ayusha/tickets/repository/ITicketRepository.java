package com.ayusha.tickets.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.tickets.entity.TicketEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticket management persistance CRUD operations
 *
 */
@Component
@Repository
public interface ITicketRepository
		extends JpaRepository<TicketEntity, Integer>, JpaSpecificationExecutor<TicketEntity> {

	@Query("SELECT t FROM TicketEntity t WHERE t.userId=?1")
	List<TicketEntity> findTicketTicketCode(String userId);

	@Query("Select t FROM TicketEntity t WHERE t.ticketId=?1")
	TicketEntity findByTicketId(String ticketId);

	@Query("Select t FROM TicketEntity t WHERE t.ticketId=?1")
	TicketEntity findByTickId(String ticketId);
	
	Page<TicketEntity> findAll(Pageable pageable);

	List<TicketEntity> findByUserId(String userId);

	@Query("select t from TicketEntity t where  t.visitDate >= ?1")
	List<TicketEntity> findTicketsByVisitDate(Date visitDate, Pageable pageable);

	@Query("Select t FROM TicketEntity t WHERE t.ticketId=?1 and t.status=?2")
	List<TicketEntity> findBYTicketIdAndStatus(String ticketId, String status);

	@Query("Select t FROM TicketEntity t WHERE t.ticketId=?1 and t.status=?2")
	List<TicketEntity> findTicketByStatus(String ticketCode, String status);

	TicketEntity findByTicketIdAndCustomerId(String ticketId, String customerId);

	@Query("SELECT t FROM TicketEntity t ")
	List<TicketEntity> findAllTickets(Pageable pg);

}