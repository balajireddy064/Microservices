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
import com.ayusha.products.service.ICategoryService;
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
@RequestMapping("/products/category")
@CrossOrigin
public class CategoryServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(CategoryServicesController.class);

	/** iCategoryService **/
	@Autowired
	private ICategoryService iCategoryService;

	/**
	 * @method addCategory
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return categoryDataModel
	 */
	@PostMapping("/add")
	public String addCategory(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		CategoryDataModel categoryDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		categoryDataModel = (CategoryDataModel) JSONConverter.convertStringToPOJO(requestBody, CategoryDataModel.class);
		iCategoryService.add(categoryDataModel);
		return JSONConverter.convertPOJOToString(categoryDataModel);
	}

	/**
	 * @method addCategory
	 * @return TESTED
	 */
	@GetMapping("/ping")
	public String addCategory() throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		return "TESTED";
	}

	/**
	 * @method updateCategory
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return categoryDataModel
	 */
	@PostMapping("/update")
	public String updateCategory(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		LOG.info("entered job notes creation - start");
		CategoryDataModel categoryDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		categoryDataModel = (CategoryDataModel) JSONConverter.convertStringToPOJO(requestBody, CategoryDataModel.class);
		iCategoryService.add(categoryDataModel);
		return JSONConverter.convertPOJOToString(categoryDataModel);
	}

	/**
	 * @method findCategory
	 * @param id
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getCategory
	 */
	@GetMapping("/find")
	public String findCategory(@RequestParam("id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iCategoryService.getCategory(id));
	}

	/**
	 * @method findCategory
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getAllCategory
	 */
	@GetMapping("/findall")
	public String findCategory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iCategoryService.getAllCategory());
	}

	/**
	 *findByCategoryName 
	 **/
	@RequestMapping("/findByCategoryName")
	public CategoryDataModel getByName(@RequestParam("name") String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		return iCategoryService.getByName(name);
	}

}
