package com.ayusha.payments.services.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.payments.data.models.EstimateCashReceiptLineItemsDataModel;
import com.ayusha.payments.data.models.LineItemsCombinedDataModel;
import com.ayusha.payments.data.models.PaymentItemDataModel;
import com.ayusha.payments.propertise.ConstantPropertise;
import com.ayusha.payments.service.IEstimateCashReceiptLineItemService;
import com.ayusha.payments.services.entity.EstimateCashReceiptLineItemEntity;
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
 * @param <Body>
 * @since 2019-05-03
 */
@RestController
@RequestMapping("/payments/lineitems")
@CrossOrigin
public class EstimateCashReceiptLineItemServicesController<Body> extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(EstimateServicesController.class);

	/** repository **/
	@Autowired
	private IEstimateCashReceiptLineItemService iEstimateLineItemService;

	/**
	 * @method addlineitems
	 * @param requestBody
	 * @return estimateCashReceiptLineItemsDataModel
	 **/
	@PostMapping("/add")
	public String addLineItems(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered line items creation - start");
		EstimateCashReceiptLineItemsDataModel estimateCashReceiptLineItemsDataModel = null;

		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);

		estimateCashReceiptLineItemsDataModel = (EstimateCashReceiptLineItemsDataModel) JSONConverter
				.convertStringToPOJO(requestBody, EstimateCashReceiptLineItemsDataModel.class);

		iEstimateLineItemService.add(estimateCashReceiptLineItemsDataModel);
		LOG.info("successfully completed estimating line items");
		return JSONConverter.convertPOJOToString(estimateCashReceiptLineItemsDataModel);
	}

	/**
	 * @method findlineitems
	 * @param estimateId
	 * @return getListItem
	 **/
	@GetMapping("/get")
	public String findLineItems(@RequestParam("estimateId") String estimateId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		List<EstimateCashReceiptLineItemEntity> items = iEstimateLineItemService.getItemsByJobCode(estimateId);
		List<LineItemsCombinedDataModel> getListItem = getListItem = new ArrayList<>();
		PaymentItemDataModel paymentItemDataModel = null;

		ResponseEntity<PaymentItemDataModel> response = null;
		List<EstimateCashReceiptLineItemEntity> body = new ArrayList<EstimateCashReceiptLineItemEntity>();
		for (EstimateCashReceiptLineItemEntity e : items) {

			paymentItemDataModel = new PaymentItemDataModel();
			System.out.println("item id" + e.getItemId());
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ConstantPropertise.GET_ITEM)
					.queryParam("id", e.getItemId());
			response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, PaymentItemDataModel.class);
			getListItem.add(new LineItemsCombinedDataModel(e.getId(), e.getItemId(), e.getAmount(), e.getTax(),
					e.getQuantity(), e.getType(), e.getJobCode(), e.getLoggedOn(), response.getBody()));
		}

		return JSONConverter.convertPOJOToString(getListItem);
	}
}