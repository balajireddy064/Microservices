package com.ayusha.products.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.products.data.models.MakeDataModel;
import com.ayusha.products.service.IMakeService;
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
 * @since 01-Aug-2019
 */
@RestController
@RequestMapping("/products/make")
@CrossOrigin
public class MakeServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(MakeServicesController.class);

	/** iMakeService **/
	@Autowired
	private IMakeService iMakeService;

	/**
	 * @method addMake
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return makeDataModel
	 */
	@PostMapping("/add")
	public String addMake(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		MakeDataModel makeDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		makeDataModel = (MakeDataModel) JSONConverter.convertStringToPOJO(requestBody, MakeDataModel.class);
		iMakeService.add(makeDataModel);
		return JSONConverter.convertPOJOToString(makeDataModel);
	}

	/**
	 * @method updateMake
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return makeDataModel
	 */
	@PostMapping("/update")
	public String updateMake(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		LOG.info("entered job notes creation - start");
		MakeDataModel makeDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		makeDataModel = (MakeDataModel) JSONConverter.convertStringToPOJO(requestBody, MakeDataModel.class);
		iMakeService.add(makeDataModel);
		return JSONConverter.convertPOJOToString(makeDataModel);
	}

	/**
	 * @method findMake
	 * @param id
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getMake
	 */
	@GetMapping("/find")
	public String findMake(@RequestParam("id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iMakeService.getMake(id));
	}

	/**
	 * @method findAllMake
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getAllMake
	 */
	@GetMapping("/findall")
	public String findAllMake(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iMakeService.getAllMake());
	}

	/**
	 * getBy MakeName
	 */
	@GetMapping("findByMakeName")
	public MakeDataModel getByName(@RequestParam("name") String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		return iMakeService.getByName(name);
	}

}
