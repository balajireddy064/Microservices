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
import com.ayusha.products.data.models.MakeDataModel;
import com.ayusha.products.data.models.ServiceTypeDataModel;
import com.ayusha.products.service.IServiceTypeService;
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
* @since  01-Aug-2019 
*/
@RestController
@RequestMapping("/products/servicetype")
public class ServiceTypeServicesController extends ServiceRequestProcessor{
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(ServiceTypeServicesController.class);
	
	/** iServiceTypeService **/
	@Autowired
	private IServiceTypeService iServiceTypeService;
	/**
	 * @method addServiceType
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return serviceTypeDataModel
	 */
	@PostMapping("/add")
	public String addServiceType(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered job notes creation - start");
		ServiceTypeDataModel serviceTypeDataModel = null;
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		serviceTypeDataModel = (ServiceTypeDataModel)JSONConverter.convertStringToPOJO(requestBody, ServiceTypeDataModel.class);
		iServiceTypeService.add(serviceTypeDataModel);
		return JSONConverter.convertPOJOToString(serviceTypeDataModel);
	}
	/**
	 * @method updateServiceType
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return serviceTypeDataModel
	 */
	@PostMapping("/update")
	public String updateServiceType(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		
		LOG.info("entered job notes creation - start");
		ServiceTypeDataModel serviceTypeDataModel = null;
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		serviceTypeDataModel = (ServiceTypeDataModel)JSONConverter.convertStringToPOJO(requestBody, ServiceTypeDataModel.class);
		iServiceTypeService.add(serviceTypeDataModel);
		return JSONConverter.convertPOJOToString(serviceTypeDataModel);
	}
	
	/**
	 * @method findServiceType
	 * @param id
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getServiceType
	 */
	@GetMapping("/find")
	public String findServiceType(@RequestParam("id") String id,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iServiceTypeService.getServiceType(id));
	}
}
