package com.ayusha.rating.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayusha.data.model.ServiceEngineerRatingModel;
import com.ayusha.rating.entity.ServiceEngineerRatingEntity;
import com.ayusha.rating.repositry.ServiceEngineerRatingRepositry;
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
public class ServiceEngineerRating implements IServiceEngineerRating {
	/** serviceRepositry **/
	@Autowired
	private ServiceEngineerRatingRepositry serviceRepositry;

	/**
	 * @mehtod add
	 * @param serviceEngineerRatingModel
	 * @return serviceEngineerRatingModeloo
	 */
	@Override
	public ServiceEngineerRatingModel add(ServiceEngineerRatingModel serviceEngineerRatingModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		ServiceEngineerRatingEntity serviceEntity = new ServiceEngineerRatingEntity();
		System.out.println(serviceEngineerRatingModel.getCustomerName());
		BeanUtils.copyProperties(serviceEngineerRatingModel, serviceEntity);
		serviceEntity = serviceRepositry.save(serviceEntity);
		return serviceEngineerRatingModel;
	}

	/**
	 * @method getByUsername
	 * @param serviceEng
	 * @return ratingDataModel
	 */
	@Override
	public List<ServiceEngineerRatingEntity> getByUserId(String userId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		List<ServiceEngineerRatingEntity> serviceratingEntity = serviceRepositry.findByUserId(userId);
		return serviceratingEntity;
	}

	/**
	 * 
	 */
	@Override
	public ServiceEngineerRatingModel updateEngRating(String jobId,
			ServiceEngineerRatingModel serviceEngineerRatingModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		ServiceEngineerRatingEntity ratingEntity = serviceRepositry.findByJobId(jobId);
		if (ratingEntity != null) {
			ratingEntity.setComments(serviceEngineerRatingModel.getComments());
			ratingEntity.setCustomerName(serviceEngineerRatingModel.getCustomerName());
			ratingEntity.setTechName(serviceEngineerRatingModel.getTechName());
			ratingEntity.setJobId(jobId);
			ratingEntity.setUserId(serviceEngineerRatingModel.getUserId());
			ratingEntity.setRate(serviceEngineerRatingModel.getRate());
			serviceRepositry.save(ratingEntity);
			return serviceEngineerRatingModel;

		} else {
			throw new ResourceNotFoundException("job id  is not present..");
		}

	}
}
