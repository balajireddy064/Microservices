package com.ayusha.products.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.products.data.models.ItemDataModel;
import com.ayusha.products.service.IItemService;
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
@RequestMapping("/products/item")
public class ItemServicesController extends ServiceRequestProcessor{
	
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(ItemServicesController.class);
	
	/**iItemService  **/
	@Autowired
	private IItemService iItemService;
	/*
	 * restTemplete
	 */
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * @method addItem
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return itemDataModel
	 */
	@PostMapping("/add")
	public String addItem(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered job notes creation - start");
		ItemDataModel itemDataModel = null;
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		itemDataModel = (ItemDataModel)JSONConverter.convertStringToPOJO(requestBody, ItemDataModel.class);
		iItemService.add(itemDataModel);
		return JSONConverter.convertPOJOToString(itemDataModel);
	}
	/**
	 * @method updateItem
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return itemDataModel
	 */
	@PostMapping("/update")
	public String updateItem(@RequestBody String requestBody,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		
		LOG.info("entered job notes creation - start");
		ItemDataModel itemDataModel = null;
		String strRequestBody = preProcess(requestBody,httpServletRequest,httpServletResponse);
		
		strRequestBody = postProcess(requestBody,httpServletRequest,httpServletResponse);
		itemDataModel = (ItemDataModel)JSONConverter.convertStringToPOJO(requestBody, ItemDataModel.class);
		iItemService.add(itemDataModel);
		return JSONConverter.convertPOJOToString(itemDataModel);
	}
	/**
	 * @method findItem
	 * @param itemId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getItem
	 */
	@GetMapping("/find")
	public String findItem(@RequestParam("id") String itemId,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iItemService.getItem(itemId));
	}
	/**
	 * @method findItemForProduct
	 * @param productId
	 * @param type
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getItemByProductIdandType
	 */
	@GetMapping("/find/producttype")
	public String findItemForProduct(@RequestParam("productId") String productId,@RequestParam("type") String type,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iItemService.getItemByProductIdandType(productId, type));
	}
	
	/**
	 * @method findItemForProductAndType
	 * @param productId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getItemsForProductId
	 */
	@GetMapping("/find/product")
	public String findItemForProductAndType(@RequestParam("productId") String productId,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws DataPersistenceOperationException,Exception,InvalidServiceRequestException,ResourceNotFoundException{
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iItemService.getItemsForProductId(productId));
	}
	
	
}
