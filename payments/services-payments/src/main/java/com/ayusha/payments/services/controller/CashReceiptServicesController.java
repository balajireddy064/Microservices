package com.ayusha.payments.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ayusha.job.specification.SearchSpecification;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.payments.data.models.CashReceiptDataModel;
import com.ayusha.payments.data.models.EstimatesDataModel;
import com.ayusha.payments.data.models.FinalReport;
import com.ayusha.payments.data.models.TicketDetailsDataModel;
import com.ayusha.payments.propertise.ConstantPropertise;
import com.ayusha.payments.service.ICashReceiptService;
import com.ayusha.payments.services.entity.CashReceiptEntity;
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
@RequestMapping("/payments/cashreceipt")
@CrossOrigin
public class CashReceiptServicesController extends ServiceRequestProcessor {
	/** Logger **/
	private static Logger LOG = LogManager.getLogger(CashReceiptServicesController.class);

	/** repository **/
	@Autowired
	private ICashReceiptService iCashReceiptService;

	/**
	 * @method addCashReceiptDetails
	 * @param requestBody
	 * @return cashReceiptDataModel
	 **/
	@PostMapping("/add")
	public String addCashReceiptDetails(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered cash receipt details creation - start");
		CashReceiptDataModel cashReceiptDataModel = null;

		cashReceiptDataModel = (CashReceiptDataModel) JSONConverter.convertStringToPOJO(requestBody,
				CashReceiptDataModel.class);
		iCashReceiptService.save(cashReceiptDataModel);

		LOG.info("Successfully completed creating cash receipt details");
		return JSONConverter.convertPOJOToString(cashReceiptDataModel);
	}

	/**
	 * @method getReport
	 * @param jobCode
	 * @return finalReport
	 * 
	 */
	@RequestMapping(value = "/getInvoice", method = RequestMethod.GET)
	public String getReport(@RequestParam("JobCode") String jobCode, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		FinalReport finalReport = new FinalReport();
		/**
		 * Estimate service
		 */
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ConstantPropertise.GET_ESTIMATE)
				.queryParam("jobCode", jobCode);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<EstimatesDataModel> response = rest.exchange(builder.toUriString(), HttpMethod.GET, null,
				EstimatesDataModel.class);
		EstimatesDataModel resp = response.getBody();

		/*
		 * ticket service
		 */
		RestTemplate ticketRest = new RestTemplate();
		HttpHeaders ticketHeaders = new HttpHeaders();
		ticketHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder ticketBuilder = UriComponentsBuilder.fromHttpUrl(ConstantPropertise.GET_JOB_DETAILS)
				.queryParam("jobCode", jobCode);

		ResponseEntity<List<TicketDetailsDataModel>> TicketResponse = ticketRest.exchange(ticketBuilder.toUriString(),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<TicketDetailsDataModel>>() {
				});
		List<TicketDetailsDataModel> ticketresp = TicketResponse.getBody();

		CashReceiptEntity cashReceiptEntity = iCashReceiptService.getAll(jobCode);
		finalReport.setDate(cashReceiptEntity.getGeneratedOn());
		finalReport.setJobCode(cashReceiptEntity.getJobCode());
		finalReport.setStatusId("Completed");
		int eSize = resp.getLstEstimates().size();
		for (int i = 0; i < eSize; i++) {
			finalReport.setEstimateAmount(resp.getLstEstimates().get(i).getGrandTotal());

		}
		for (TicketDetailsDataModel t : ticketresp) {
			finalReport.setCustomerName(t.getCustomerDataModels().getFirstName());
			finalReport.setServiceEngineer(t.getLoggedby());

		}
		finalReport.setInvoiceAmount(cashReceiptEntity.getGrandTotal());

		return JSONConverter.convertPOJOToString(finalReport);
	}

	/**
	 * 
	 * @method getAllInvoice
	 * @return getAllInvoice
	 * 
	 */
	@GetMapping("/getAllInvoice")
	public Page<CashReceiptEntity> getAllInvoice()
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		return iCashReceiptService.getAllInvoice();
	}



//	@RequestMapping(value = "/cashReceiptSearch", method = RequestMethod.GET)
	@GetMapping("/cashReceiptSearch")
	public Page<CashReceiptDataModel> searchBy(@RequestParam("cashSearch") String cashSearch) {
		// call to user specification class
		SearchSpecification userSpecification = new SearchSpecification(cashSearch);
		return iCashReceiptService.searchDetails(userSpecification);
	}
}
