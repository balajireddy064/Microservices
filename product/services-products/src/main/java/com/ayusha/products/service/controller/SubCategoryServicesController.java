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
import com.ayusha.products.data.models.SubCategoryDataModel;
import com.ayusha.products.service.ISubCategoryService;
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
@RequestMapping("/products/subcategory")
@CrossOrigin
public class SubCategoryServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(SubCategoryServicesController.class);

	/** iSubCategoryService **/
	@Autowired
	private ISubCategoryService iSubCategoryService;

	/**
	 * @method addSubCategory
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return subCategoryDataModel
	 */
	@PostMapping("/add")
	public String addSubCategory(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		SubCategoryDataModel subCategoryDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		subCategoryDataModel = (SubCategoryDataModel) JSONConverter.convertStringToPOJO(requestBody,
				SubCategoryDataModel.class);
		iSubCategoryService.add(subCategoryDataModel);
		return JSONConverter.convertPOJOToString(subCategoryDataModel);
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
	 * @method updateSubCategory
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return subCategoryDataModel
	 */
	@PostMapping("/update")
	public String updateSubCategory(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		LOG.info("entered job notes creation - start");
		SubCategoryDataModel subCategoryDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		subCategoryDataModel = (SubCategoryDataModel) JSONConverter.convertStringToPOJO(requestBody,
				SubCategoryDataModel.class);
		iSubCategoryService.add(subCategoryDataModel);
		return JSONConverter.convertPOJOToString(subCategoryDataModel);
	}

	/**
	 * @method findCategory
	 * @param makeId
	 * @param categoryId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getSubCategory
	 */
	@GetMapping("/find")
	public String findCategory(@RequestParam("makeId") String makeId, @RequestParam("categoryId") String categoryId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iSubCategoryService.getSubCategory(makeId, categoryId));
	}

	@RequestMapping("findBySubCatname")
	public SubCategoryDataModel getByName(@RequestParam("name") String name)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		return iSubCategoryService.getByName(name);
	}
	
	@GetMapping("/findAll")
	public String findSubCategory(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iSubCategoryService.getAllSubCategory());
	}

}
