package com.ayusha.tickets.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.tickets.entity.CustomerEntity;
import com.ayusha.tickets.entity.TicketEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Model class Defines the methods for
 *         the ticket management persistance CRUD operations
 *
 */
@Component
@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity, Integer> {

	List<CustomerEntity> findById(Long id);

	@Query("SELECT t FROM CustomerEntity t WHERE t.email=?1")
	CustomerEntity findCustomerByEmailId(String emailId);

	@Query("SELECT t FROM CustomerEntity t WHERE t.email = ?1")
	CustomerEntity findCustomerByMobileNumber(String phoneNumber);

	@Query("SELECT t FROM CustomerEntity t WHERE t.customerId = ?1")
	CustomerEntity findCustomerByCustomerCode(String customerId);

	CustomerEntity findCustomerByCustomerId(String customerId);

//	Page<CustomerEntity> findByCity(Pageable pageable, String city);

	List<CustomerEntity> findByCustomerId(String customerId);

	@Query("SELECT t FROM CustomerEntity t WHERE t.customerId = ?1")
	CustomerEntity findByCustId(String customerId);

	@Query("SELECT t FROM CustomerEntity t WHERE t.city LIKE :city_filter")
	List<CustomerEntity> findByCity(String city_filter, Pageable pageable);

	@Query("SELECT t FROM CustomerEntity t WHERE t.city LIKE :city%")
	Page<CustomerEntity> findByCity(Pageable pageable, @Param("city") String city);

}
