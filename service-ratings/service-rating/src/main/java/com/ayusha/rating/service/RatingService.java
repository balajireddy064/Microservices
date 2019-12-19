package com.ayusha.rating.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.data.model.RatingDataModel;
import com.ayusha.rating.entity.RatingEntity;
import com.ayusha.rating.repositry.RatingRepositry;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

/**
 * 
 * @author Finch
 * @Date 01 Aug
 *
 */

@Service
public class RatingService implements IRatingService {
	@Autowired
	private RatingRepositry ratingRepositry;

	/**
	 * @method addRating
	 * @param ratingDataModel
	 * @return ratingDataModel
	 */

	@Override
	public RatingDataModel addRating(RatingDataModel ratingDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		RatingEntity ratingEntity = new RatingEntity();
		BeanUtils.copyProperties(ratingDataModel, ratingEntity);

		RatingEntity ratingEntity1 = ratingRepositry.save(ratingEntity);
		return ratingDataModel;
	}

	/**
	 * @method getByCustomerName
	 * @param customerName
	 * @return ratingDataModel
	 */

	@Override
	public List<RatingEntity> getByCustomerName(String customerName)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		System.out.println("inservice ");
		List<RatingEntity> ratingEntity = ratingRepositry.findByCustomerName(customerName);

		return ratingEntity;
	}

	/**
	 * update Rating Based On jobId
	 */
	public RatingDataModel updateRating(String jobId, RatingDataModel ratingDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {

		RatingEntity ratingEntity = ratingRepositry.findByJobId(jobId);

		if (ratingEntity != null) {
			ratingEntity.setComments(ratingDataModel.getComments());
			ratingEntity.setRate(ratingDataModel.getRate());
			ratingEntity.setCustomerName(ratingDataModel.getCustomerName());
			ratingEntity.setJobId(jobId);
			ratingEntity.setTechName(ratingDataModel.getTechName());
			ratingRepositry.save(ratingEntity);
			return ratingDataModel;

		} else {
			throw new ResourceNotFoundException("jobId is not prsent");
		}
	}
}
