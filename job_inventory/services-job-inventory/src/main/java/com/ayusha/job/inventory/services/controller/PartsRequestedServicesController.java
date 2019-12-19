package com.ayusha.job.inventory.services.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ayusha.job.inventory.data.models.PartRequestedDataModel;
import com.ayusha.job.inventory.data.models.PartsRequestedDataModel;
import com.ayusha.job.inventory.service.IPartsRequestedService;
import com.ayusha.job.inventory.service.validations.ConstantProperpertise;
import com.ayusha.job.inventory.services.entity.Body;
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
@RequestMapping("/jobinventory/partsrequested")
public class PartsRequestedServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(PartsRequestedServicesController.class);

	/** repository **/
	@Autowired
	private IPartsRequestedService iPartsRequestedService;

	/**
	 * reposity
	 */

	@Autowired
	RestTemplate restTemplate;

	/**
	 * @method addPartsRequested
	 * @param requestBody
	 * @return partsRequestedDataModel
	 */
	@PostMapping("/add")
	public String addPartsRequested(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		List<PartRequestedDataModel> lstPartRequestedDataModel = null;
		PartsRequestedDataModel partsRequestedDataModel = null;

		int size = 0;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		partsRequestedDataModel = (PartsRequestedDataModel) JSONConverter.convertStringToPOJO(requestBody,
				PartsRequestedDataModel.class);

		iPartsRequestedService.save(partsRequestedDataModel);

		LOG.info("successfully completed add job notes operation ");
		return requestBody;
	}

	/**
	 * @method updatePartRequested
	 * @param requestBody
	 * @return requestBody
	 */
	@PostMapping("/update")
	public String updatePartRequested(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		List<PartRequestedDataModel> lstPartRequestedDataModel = null;
		PartsRequestedDataModel partsRequestedDataModel = null;

		int size = 0;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		partsRequestedDataModel = (PartsRequestedDataModel) JSONConverter.convertStringToPOJO(requestBody,
				PartsRequestedDataModel.class);

		iPartsRequestedService.save(partsRequestedDataModel);

		LOG.info("successfully completed add job notes operation ");
		return requestBody;
	}

	/**
	 * 
	 * @method findPartsRequestedd
	 * @param jobid
	 * @return body
	 * 
	 */
	@GetMapping("/find")
	@ResponseBody
	public String findPartsRequestedd(@RequestParam("jobid") String jobid, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		String partialDetail = JSONConverter.convertPOJOToString(iPartsRequestedService.getPartsRequested(jobid));
		List<Body> body = new ArrayList<Body>();
		List<PartRequestedDataModel> partRequestedDataModel = iPartsRequestedService.getPartsRequested(jobid);
		ResponseEntity<Body> response = null;
		for (PartRequestedDataModel p : partRequestedDataModel) {
			System.out.println("id" + p.getPartid());
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromHttpUrl(ConstantProperpertise.FIND_ITEM_BASEDON_PRODUCT_TYPE)
					.queryParam("productId", p.getPartid()).queryParam("type", p.getType());

			HttpEntity<?> entity = new HttpEntity<>(headers);

			response = rest.exchange(builder.toUriString(), HttpMethod.GET, null, Body.class);

			body.add(response.getBody());

		}
		return JSONConverter.convertPOJOToString(body);
	}

	/**
	 * @method getItem
	 * @param jobid
	 * @return getBody
	 */
	@GetMapping("/findByID")
	@ResponseBody
	public String getItem(@RequestParam("jobid") String jobid, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("get intercall");

		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(ConstantProperpertise.FIND_ITEM_BASEDON_PRODUCT_TYPE).queryParam("productId", "12")
				.queryParam("type", "part");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<Body> response = rest.exchange(builder.toUriString(), HttpMethod.GET, null, Body.class);
		// ResponseEntity<Void> response =
		// restTemplate.exchange("http://localhost:8093/products/item/find/producttype?productId=12&type=part",HttpMethod.DELETE,
		// null, Void.class);
		// Void body = response.getBody();

		Body resp = response.getBody();

		return JSONConverter.convertPOJOToString(response.getBody());
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
