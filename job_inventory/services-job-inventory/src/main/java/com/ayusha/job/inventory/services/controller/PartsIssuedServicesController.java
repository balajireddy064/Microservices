package com.ayusha.job.inventory.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.job.inventory.data.models.PartIssuedDataModel;
import com.ayusha.job.inventory.data.models.PartRequestedDataModel;
import com.ayusha.job.inventory.data.models.PartsIssuedDataModel;
import com.ayusha.job.inventory.data.models.PartsRequestedDataModel;
import com.ayusha.job.inventory.service.IPartsIssuedService;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 2019-05-03
 */
@RestController
@RequestMapping("/jobinventory/partsissued")
public class PartsIssuedServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(PartsIssuedServicesController.class);

	/** repository **/
	@Autowired
	private IPartsIssuedService iPartsIssuedService;

	/**
	 * @method addPartsIssued
	 * @param PartsIssuedDataModel
	 * @return requestBody
	 */
	@PostMapping("/add")
	public String addPartsIssued(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		List<PartIssuedDataModel> lstPartIssuedDataModel = null;
		PartsIssuedDataModel partsIssuedDataModel = null;

		int size = 0;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		partsIssuedDataModel = (PartsIssuedDataModel) JSONConverter.convertStringToPOJO(requestBody,
				PartsIssuedDataModel.class);

		iPartsIssuedService.save(partsIssuedDataModel);

		LOG.info("successfully completed add job notes operation ");
		return requestBody;
	}

	/**
	 * @method updatePartIssued
	 * @param requestBody
	 * @return requestBody
	 */
	@PostMapping("/update")
	public String updatePartIssued(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		List<PartIssuedDataModel> lstPartIssuedDataModel = null;
		PartsIssuedDataModel partsIssuedDataModel = null;

		int size = 0;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		partsIssuedDataModel = (PartsIssuedDataModel) JSONConverter.convertStringToPOJO(requestBody,
				PartsIssuedDataModel.class);

		iPartsIssuedService.save(partsIssuedDataModel);

		LOG.info("successfully completed add job notes operation ");
		return requestBody;
	}

	/**
	 * @method findPartsIssued
	 * @param jobid
	 * @return getPartsIssued
	 */
	@GetMapping("/find")
	public String findPartsIssued(@RequestParam("jobid") String jobid, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iPartsIssuedService.getPartsIssued(jobid));
	}
}
