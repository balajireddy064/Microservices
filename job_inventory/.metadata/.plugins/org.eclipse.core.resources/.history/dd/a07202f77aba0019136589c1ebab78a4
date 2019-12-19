package com.ayusha.repair.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.repair.services.entity.SymptomEntity;
import com.ayusha.repair.services.entity.SymptomsEntity;


/**
 * 
 * @author author1
 * Date : 01-Aug-2019
 * Ticket Model class
 * Defines the methods for the ticketmanagement persistance CRUD operations
 *
 */
@Component
@Repository
public interface ISymptomRepository extends CrudRepository<SymptomsEntity,Integer>  {
  
    @Query("SELECT t FROM SymptomsEntity t WHERE t.job_id = ?1")
    List<SymptomsEntity> findAllSymptomByJobId(String jobId);
    
    
    
}
