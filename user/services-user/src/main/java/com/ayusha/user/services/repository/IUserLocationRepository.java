package com.ayusha.user.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ayusha.user.services.entity.UserLocationEntity;

/**
 * 
 * @author Finch Date : 01-Aug-2019 User Service Methods
 *
 */
@Component
@Repository
public interface IUserLocationRepository extends JpaRepository<UserLocationEntity, Integer> {

	@Query("SELECT t FROM UserLocationEntity t WHERE t.locationid =:locationid and serving=:serving")
	List<UserLocationEntity> findUsersByLocations(@Param("locationid") String locationid,
			@Param("serving") String serving);

	@Query("SELECT t FROM UserLocationEntity t WHERE t.userId =:userId and serving=:status")
	List<UserLocationEntity> findLocationsByUserId(@Param("userId") String userId, @Param("status") String status);

	UserLocationEntity findByUserId(String userId);

}
