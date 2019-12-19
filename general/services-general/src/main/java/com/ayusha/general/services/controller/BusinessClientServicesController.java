package com.ayusha.general.services.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.general.service.IBusinessClientService;
import com.ayusha.general.services.data.model.BusinessClientDataModel;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class BusinessClientServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(BusinessClientServicesController.class);

	/** service **/
	@Autowired
	private IBusinessClientService iBusinessClientService;

	/**
	 * Client creation
	 */
	@PostMapping("/create")
	public ResponseEntity<?> addClients(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		LOG.info("entered client creation - start");

		BusinessClientDataModel businessClientDataModel = null;
		businessClientDataModel = (BusinessClientDataModel) JSONConverter.convertStringToPOJO(requestBody,
				BusinessClientDataModel.class);

		ResponseEntity<?> response = iBusinessClientService.save(businessClientDataModel);

		LOG.info("Successfully completed client creation operation");
		return response;
	}

	/**
	 * Find client by Client Id
	 */
	@GetMapping("/findByClientId")
	public String findClientById(@RequestParam("clientId") String clientId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		return JSONConverter.convertPOJOToString(iBusinessClientService.getByClient(clientId));
	}

	/**
	 * Find all clients
	 */
	@GetMapping("/findAllClients")
	public Page<BusinessClientDataModel> findAll(Pageable pageable) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		return iBusinessClientService.getAllClients(pageable);
	}

	/**
	 * Update client by client Id
	 */
	@RequestMapping(value = "/updateClientDetails", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClient(@RequestParam("clientId") String clientId, @RequestBody String body,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {

		LOG.info("entered client updation - start");
		BusinessClientDataModel businessClientDataModel = null;
		businessClientDataModel = (BusinessClientDataModel) JSONConverter.convertStringToPOJO(body,
				BusinessClientDataModel.class);
		ResponseEntity<?> response = iBusinessClientService.updateClientRequest(clientId, businessClientDataModel);
		LOG.info("successfully completed client updation - start");
		return response;
	}
}
