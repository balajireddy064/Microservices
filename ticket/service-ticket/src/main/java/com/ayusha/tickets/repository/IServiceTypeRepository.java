package com.ayusha.tickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.tickets.entity.CustomerEntity;
import com.ayusha.tickets.entity.ServiceTypeEntity;


/**
 * 
 * @author Finch
 * Date : 01-Aug-2019
 * Ticket Model class
 * Defines the methods for the ticket management persistance CRUD operations
 *
 */
@Component
@Repository
public interface IServiceTypeRepository extends CrudRepository<ServiceTypeEntity,Integer>  {
      
    @Query("SELECT t FROM ServiceTypeEntity t WHERE t.code = ?1")
    ServiceTypeEntity findServiceTypeByCode(String code);
    
}
