package com.ayusha.rating.service;

import java.util.List;

import com.ayusha.data.model.ServiceEngineerRatingModel;
import com.ayusha.rating.entity.ServiceEngineerRatingEntity;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

public interface IServiceEngineerRating {
	/** add rating **/
	public ServiceEngineerRatingModel add(ServiceEngineerRatingModel ServiceEngineerRatingModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** getByUserId **/

	public List<ServiceEngineerRatingEntity> getByUserId(String userId)
			throws DataPersistenceOperationException, InvalidServiceRequestException;

	/** update **/
	public ServiceEngineerRatingModel updateEngRating(String jobId,
			ServiceEngineerRatingModel serviceEngineerRatingModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException;

}
