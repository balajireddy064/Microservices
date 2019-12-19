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

import com.ayusha.data.model.RatingDataModel;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.rating.entity.RatingEntity;
import com.ayusha.rating.service.IRatingService;
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
@RequestMapping("/rating/customer")
@RestController
public class RatingController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(RatingController.class);

	@Autowired
	private IRatingService ratingService;

	/**
	 * @method add
	 * @param requestBody
	 * @return JSONConverter.convertPOJOToString(ratingDataModel)
	 */
	@PostMapping("/addRatingByCustomer")
	public String add(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		RatingDataModel ratingDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);
		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		ratingDataModel = (RatingDataModel) JSONConverter.convertStringToPOJO(requestBody, RatingDataModel.class);
		ratingService.addRating(ratingDataModel);
		return JSONConverter.convertPOJOToString(ratingDataModel);

	}

	/**
	 * @method getByCustomer
	 * @param customerName
	 * @return customerName
	 */

	@GetMapping("/getByCustomerName")
	public List<RatingEntity> getByCustomer(@RequestParam("customerName") String customerName)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		List<RatingEntity> lst = ratingService.getByCustomerName(customerName);
		return lst;
	}

	/**
	 * 
	 * update By Customer
	 */
	@PutMapping("/updateCustomer")
	public RatingDataModel updateRating(@RequestParam("jobId") String jobId,
			@RequestBody RatingDataModel ratingDataModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		return ratingService.updateRating(jobId, ratingDataModel);
	}

}
