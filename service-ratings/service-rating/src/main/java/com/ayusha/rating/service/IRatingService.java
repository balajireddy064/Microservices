package com.ayusha.rating.service;

import java.util.List;

import com.ayusha.data.model.RatingDataModel;
import com.ayusha.rating.entity.RatingEntity;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

public interface IRatingService {
	/** addRating **/
	public RatingDataModel addRating(RatingDataModel ratngDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** getByCustomername **/
	public List<RatingEntity> getByCustomerName(String customerName)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	public RatingDataModel updateRating(String jobId, RatingDataModel ratingDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

}
