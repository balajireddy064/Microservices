package com.ayusha.general.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.general.services.entity.ClientContectEntity;


public interface IClientContectRepositry extends JpaRepository<ClientContectEntity, Integer> {

	List<ClientContectEntity> findByClientId(String clientId);

	// public List<ClientContectEntity>save(ClientContectEntity
	// clientContectEntity);

	

	@Query("SELECT t FROM ClientContectEntity t WHERE t.clientId = ?1")
	List<ClientContectEntity> findByClieintId(String customerId);
	
	@Query("SELECT t FROM ClientContectEntity t WHERE t.id = ?1")
	ClientContectEntity findIndividualClientId(int id);
	
}
