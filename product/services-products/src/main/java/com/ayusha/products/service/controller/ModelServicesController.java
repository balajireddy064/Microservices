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
import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.products.service.IModelService;
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
@RequestMapping("/products/model")
@CrossOrigin
public class ModelServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(ModelServicesController.class);

	/** iModelService **/
	@Autowired
	private IModelService iModelService;

	/**
	 * @method addModel
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return modelDataModel
	 */
	@PostMapping("/add")
	public String addModel(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		ModelDataModel modelDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		modelDataModel = (ModelDataModel) JSONConverter.convertStringToPOJO(requestBody, ModelDataModel.class);
		iModelService.add(modelDataModel);
		return JSONConverter.convertPOJOToString(modelDataModel);
	}

	/**
	 * @method updateModel
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return modelDataModel
	 */
	@PostMapping("/update")
	public String updateModel(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		LOG.info("entered job notes creation - start");
		ModelDataModel modelDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		modelDataModel = (ModelDataModel) JSONConverter.convertStringToPOJO(requestBody, ModelDataModel.class);
		iModelService.add(modelDataModel);
		return JSONConverter.convertPOJOToString(modelDataModel);
	}

	/**
	 * @method findModel
	 * @param makeId
	 * @param categoryId
	 * @param subCategoryId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getModels
	 */
	@GetMapping("/find")
	public String findModel(@RequestParam("makeId") String makeId, @RequestParam("categoryId") String categoryId,
			@RequestParam("subCategoryId") String subCategoryId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iModelService.getModels(makeId, categoryId, subCategoryId));
	}

	/**
	 * get ByModelName
	 */
	@GetMapping("/getByModelName")
	public ModelDataModel getByName(@RequestParam("name") String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException ,ResourceNotFoundException{
		return iModelService.getByName(name);
	}

	/**
	 * findAll
	 */
	@GetMapping("/findAll")
	public String findModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iModelService.getAllModel());
	}
}
