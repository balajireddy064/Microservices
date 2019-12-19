package com.ayusha.user.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayusha.user.services.entity.UserEducationDetailsEntity;

public interface UserEducationDetailsRepositry extends JpaRepository<UserEducationDetailsEntity, Integer> {

	List<UserEducationDetailsEntity> findByUserId(String userId);

	@Query("SELECT t FROM UserEducationDetailsEntity t WHERE t.id = ?1")
	UserEducationDetailsEntity findIndividualClientId(int id);

}
