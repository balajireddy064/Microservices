package com.ayusha.rating.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayusha.data.model.RatingDataModel;
import com.ayusha.rating.entity.RatingEntity;

public interface RatingRepositry extends JpaRepository<RatingEntity, Integer> {

	List<RatingEntity> findByCustomerName(String customerName);

	RatingEntity findByJobId(String jobId);

	void save(RatingDataModel ratingDataModel);

}
