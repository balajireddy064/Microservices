package com.ayusha.payments.services.controller;

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
import com.ayusha.payments.data.models.CashReceiptDiscountsDataModel;
import com.ayusha.payments.service.ICashReceiptDiscountsService;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author Swathi
 * @version 1.0
 * @since 2019-08-29
 */
@RestController
@RequestMapping("/payments/cashreceiptsdiscounts")
public class CashReceiptDiscountsServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(CashReceiptServicesController.class);

	/** repository **/
	@Autowired
	private ICashReceiptDiscountsService iCashReceiptDiscountsService;

	/**
	 * @method addCashReceiptDiscounts
	 * @param requestBody
	 * @return cashReceiptDiscountsDataModel
	 */
	@PostMapping("/add")
	public String addCashReceiptDiscounts(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered cash receipt dicount details creation - start");
		CashReceiptDiscountsDataModel cashReceiptDiscountsDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);

		cashReceiptDiscountsDataModel = (CashReceiptDiscountsDataModel) JSONConverter.convertStringToPOJO(requestBody,
				CashReceiptDiscountsDataModel.class);
		iCashReceiptDiscountsService.save(cashReceiptDiscountsDataModel);
		LOG.info("Successfully completed adding cash receipt dicount details");
		return JSONConverter.convertPOJOToString(cashReceiptDiscountsDataModel);
	}

	/**
	 * @method getDiscountAmount
	 * @param couponCode
	 * @return couponCode
	 **/
	@GetMapping("/get")
	public String getDiscountAmount(@RequestParam("couponCode") String couponCode,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		return JSONConverter.convertPOJOToString(iCashReceiptDiscountsService.findDiscountAmount(couponCode));
	}

}
