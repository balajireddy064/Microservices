package com.ayusha.ticketproduct.data;

import com.ayusha.http.client.ServicesClient;
import com.ayusha.http.client.models.ServiceResponse;
import com.ayusha.http.constants.HTTPConstants;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.products.data.models.ProductDataModel;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.services.constants.TicketServiceConstants;

/**
 * 
 * @author Finch
 * Date:01-Aug-2019
 * Ticket Service Methods
 *
 */
public class ProductDataHandler {
	

	/**productDataModel  **/
	private ProductDataModel productDataModel = null;
	
	/** servicesClient **/
	private ServicesClient servicesClient;
	/**
	 * default constructor
	 */
	public ProductDataHandler() {
		servicesClient = new ServicesClient();
		servicesClient.setBaseURL(ServicesClient.PRODUCT_BASE_URL);	
		System.out.println(" CHECK THIS URRRRRRRRRRRRRRRRRRR LLLLL "+servicesClient);
	}
	

	/**
	 * @method findProductData
	 * @param category
	 * @param subCategory
	 * @param make
	 * @param model
	 * @return ProductDataModel
	 */
	public ProductDataModel findProductData(String category,String subCategory,String make,String model) throws ServicesDataException{
		
		
		ServiceResponse serviceResponse = null;
		ServicesDataException servicesDataException = null;
		String productService=TicketServiceConstants.FIND_PRODUCT_DETAILS_SERVICE_NAME;
		
		productService = productService.replace("$1", category);
		productService = productService.replace("$2", subCategory);
		productService = productService.replace("$3", make);
		productService = productService.replace("$4", model);
		
		serviceResponse = servicesClient.execute(productService,"",HTTPConstants.GET);
		
		try {
			return ((ProductDataModel)JSONConverter.convertStringToPOJO(""+serviceResponse.getResponse(),ProductDataModel.class));
		}catch(Exception e) {
			servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		}
	}
}
