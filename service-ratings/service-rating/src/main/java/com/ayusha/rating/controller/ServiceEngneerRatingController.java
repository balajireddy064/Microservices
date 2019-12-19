package com.ayusha.rating.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.data.model.ServiceEngineerRatingModel;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.rating.entity.ServiceEngineerRatingEntity;
import com.ayusha.rating.service.IServiceEngineerRating;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch
 * @Date 01 Aug
 *
 */
@RestController
@RequestMapping("/rating/serviceEng")
public class ServiceEngneerRatingController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(ServiceEngneerRatingController.class);

	@Autowired
	private IServiceEngineerRating ratingService;

	/**
	 * @method add
	 * @param requestBody
	 * @return serviceEngneerRatingModel
	 */

	@PostMapping("/addRatingByEng")
	public String add(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		ServiceEngineerRatingModel serviceEngineerRatingModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);
		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		serviceEngineerRatingModel = (ServiceEngineerRatingModel) JSONConverter.convertStringToPOJO(requestBody,
				ServiceEngineerRatingModel.class);
		ratingService.add(serviceEngineerRatingModel);
		return JSONConverter.convertPOJOToString(serviceEngineerRatingModel);

	}

	/**
	 * @method getRatingByUser
	 * @param userId
	 * @return RatingDataModel class
	 */

	@GetMapping("/getRatingByUser")
	public List<ServiceEngineerRatingEntity> getByUserId(@RequestParam("userId") String userId)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		System.out.println(userId);
		return ratingService.getByUserId(userId);
	}

	/**
	 * 
	 * update rating
	 */

	@PutMapping("/update")
	public ServiceEngineerRatingModel update(@RequestParam("jobId") String jobId,
			@RequestBody ServiceEngineerRatingModel serviceEngineerRatingModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		return ratingService.updateEngRating(jobId, serviceEngineerRatingModel);

	}
}
