package com.ayusha.products.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.products.data.models.ServiceTypeDataModel;
import com.ayusha.products.data.models.SymptomDataModel;
import com.ayusha.products.service.ISymptomService;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
* This is the service api interface.
* This controls the access to the services
*
* @author  Finch
* @version 1.0
* @since   01-Aug-2019 
*/
@RestController
@RequestMapping("/products/symptoms")
public class SymptomsServicesController extends ServiceRequestProcessor{
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(SymptomsServicesController.class);
	
	/** iSymptomService **/
	@Autowired
	private ISymptomService iSymptomService;
	/**
	 * @method addSymptom
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return symptomDataModel
	 */
	@PostMapping("/add")
	public String addSymptom(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered job notes creation - start");
		SymptomDataModel symptomDataModel = null;
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		symptomDataModel = (SymptomDataModel)JSONConverter.convertStringToPOJO(requestBody, SymptomDataModel.class);
		iSymptomService.add(symptomDataModel);
		return JSONConverter.convertPOJOToString(symptomDataModel);
	}
	/**
	 * @method updateSymptom
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return symptomDataModel
	 */
	@PostMapping("/update")
	public String updateSymptom(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		
		LOG.info("entered job notes creation - start");
		SymptomDataModel symptomDataModel = null;
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		symptomDataModel = (SymptomDataModel)JSONConverter.convertStringToPOJO(requestBody, SymptomDataModel.class);
		iSymptomService.add(symptomDataModel);
		return JSONConverter.convertPOJOToString(symptomDataModel);
	}
	
	/**
	 * @method findSymptom
	 * @param productid
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getProductSymptom
	 */
	@GetMapping("/find")
	public String findSymptom(@RequestParam("id") String productid,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered symptoms finding - start "+productid);
		return JSONConverter.convertPOJOToString(iSymptomService.getProductSymptom(productid));
	}
	
	}
