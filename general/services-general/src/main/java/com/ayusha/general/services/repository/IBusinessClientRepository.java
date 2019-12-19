package com.ayusha.general.services.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.general.services.entity.BusinessClientEntity;

public interface IBusinessClientRepository extends JpaRepository<BusinessClientEntity, Integer> {

	@Query("select t FROM BusinessClientEntity t")
	List<BusinessClientEntity> findAllClients();

	List<BusinessClientEntity> findByClientId(String clientId);

	@Query("select t FROM BusinessClientEntity t")
	List<BusinessClientEntity> findAllClients(Pageable pageable);

	@Query("Select t FROM BusinessClientEntity t WHERE t.clientId=?1")
	BusinessClientEntity findById(String clientId);

}
