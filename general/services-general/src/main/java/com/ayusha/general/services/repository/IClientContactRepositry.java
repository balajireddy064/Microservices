package com.ayusha.general.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.general.services.entity.ClientContactEntity;

public interface IClientContactRepositry extends JpaRepository<ClientContactEntity, Integer> {

	List<ClientContactEntity> findByClientId(String clientId);

	@Query("SELECT t FROM ClientContactEntity t WHERE t.clientId = ?1")
	List<ClientContactEntity> findByClieintId(String customerId);

	@Query("SELECT t FROM ClientContactEntity t WHERE t.id = ?1")
	ClientContactEntity findIndividualClientId(int id);

}
