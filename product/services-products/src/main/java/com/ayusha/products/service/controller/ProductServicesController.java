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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.products.data.models.ProductDataModel;
import com.ayusha.products.service.IProductService;
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
@RequestMapping("/products/product")
public class ProductServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(ProductServicesController.class);

	/** iProductService **/
	@Autowired
	private IProductService iProductService;
	/**
	 * RestTemplte
	 * 
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * @method addProduct
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return productDataModel
	 */
	@PostMapping("/add")
	public String addProduct(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		ProductDataModel productDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		productDataModel = (ProductDataModel) JSONConverter.convertStringToPOJO(requestBody, ProductDataModel.class);
		iProductService.add(productDataModel);
		return JSONConverter.convertPOJOToString(productDataModel);
	}

	/**
	 * @method addProduct
	 * @return TESTED
	 */
	@GetMapping("/ping")
	public String addProduct() throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		return "TESTED";
	}

	/**
	 * @method updateProduct
	 * @param requestBody
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return productDataModel
	 */
	@PostMapping("/update")
	public String updateProduct(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		LOG.info("entered job notes creation - start");
		ProductDataModel productDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		productDataModel = (ProductDataModel) JSONConverter.convertStringToPOJO(requestBody, ProductDataModel.class);
		iProductService.add(productDataModel);
		return JSONConverter.convertPOJOToString(productDataModel);
	}

	/**
	 * @method findCategory
	 * @param productId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getProductById
	 */
	@GetMapping("/find/productid")
	public String findCategory(@RequestParam("id") String productId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered symptoms finding - start");
		return JSONConverter.convertPOJOToString(iProductService.getProductById(productId));
	}

	/**
	 * @method findProducts
	 * @param categoryId
	 * @param subCategoryId
	 * @param makeId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * 
	 */
	@GetMapping("/find/productfor")
	public String findProducts(@RequestParam("categoryid") String categoryId,
			@RequestParam("subcategoryid") String subCategoryId, @RequestParam("makeid") String makeId,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered symptoms finding - start  <<<< >>>> " + categoryId + "=" + subCategoryId + "==" + makeId);
		return JSONConverter.convertPOJOToString(iProductService.getProductFor(categoryId, subCategoryId, makeId));
	}

	/**
	 * @method findProductsFor
	 * @param categoryId
	 * @param subCategoryId
	 * @param makeId
	 * @param modelId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return getProductFor
	 */
	@GetMapping("/find/productforId")
	public String findProductsFor(@RequestParam("categoryid") String categoryId,
			@RequestParam("subcategoryid") String subCategoryId, @RequestParam("makeid") String makeId,
			@RequestParam("modelid") String modelId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered product finding - start " + modelId);
		return JSONConverter
				.convertPOJOToString(iProductService.getProductFor(categoryId, subCategoryId, makeId, modelId));
	}

	@GetMapping("/find/findByProductId")
	public String findProduct(@RequestParam("productId") String product_id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		return JSONConverter.convertPOJOToString(iProductService.getProduct(product_id));
	}

	/**
	 * 
	 * @return RestTemplate
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
