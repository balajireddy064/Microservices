package com.ayusha.rating.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayusha.rating.entity.ServiceEngineerRatingEntity;

public interface ServiceEngineerRatingRepositry extends JpaRepository<ServiceEngineerRatingEntity, Integer> {

	List<ServiceEngineerRatingEntity> findByUserId(String userId);

	 

	ServiceEngineerRatingEntity findByJobId(String jobId);

}
