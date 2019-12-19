package com.ayusha.payments.services.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ayusha.job.specification.EstimateSearchSpecificatoin;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.payments.data.models.EstimateDataModel;
import com.ayusha.payments.service.EstimateService;
import com.ayusha.payments.service.IEstimateService;
import com.ayusha.payments.services.entity.EstimateEntity;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author author1r
 * @version 1.0
 * @since 2019-05-03
 */
@RestController
@RequestMapping("/payments/estimate")
@CrossOrigin
public class EstimateServicesController extends ServiceRequestProcessor {

	@PersistenceContext
	EntityManager entityManager;

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(EstimateServicesController.class);

	/** repository **/
	@Autowired
	private IEstimateService iEstimateService;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EstimateService estservice;

	/**
	 * @method addEstimate
	 * @param requestBody
	 * @return estimateDataModel
	 */
	@PostMapping("/add")
	public String addEstimate(@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered job notes creation - start");
		EstimateDataModel estimateDataModel = null;
		int size = 0;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		estimateDataModel = (EstimateDataModel) JSONConverter.convertStringToPOJO(requestBody, EstimateDataModel.class);
		iEstimateService.save(estimateDataModel);
		LOG.info("successfully completed add job notes operation ");
		return JSONConverter.convertPOJOToString(estimateDataModel);
	}

	/**
	 * @method findEstimate
	 * @param jobCode
	 * @return getEstimateForJobCodeTime
	 */
	@GetMapping("/get")
	public String findEstimate(@RequestParam("jobCode") String jobCode, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		return JSONConverter.convertPOJOToString(iEstimateService.getEstimateForJobCodeTime(jobCode));
	}

	/**
	 * 
	 * @method findlast
	 * @param jobCode
	 * @return getLastItem
	 */
	@GetMapping("/getLast")
	public String findlast(@RequestParam("jobCode") String jobCode, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		return JSONConverter.convertPOJOToString(iEstimateService.getLastItem(jobCode));
	}

	/**
	 * @method getAllEstimates
	 * @return getAllEstimates
	 */
	@GetMapping("/getAllEstimates")
	public Page<EstimateEntity> getAllEstimates() throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		return iEstimateService.getAllEstimates();
	}

	@GetMapping("/estimateSearch")
	public Page<EstimateDataModel> searchBy(@RequestParam("estimateSearch") String estimateSearch) {
		// call to user specification class
		EstimateSearchSpecificatoin estimateSpecification = new EstimateSearchSpecificatoin(estimateSearch);
		return iEstimateService.searchDetails(estimateSpecification);
	}

	@GetMapping("reportInExcel")
	public String createExcelSheet() throws IOException {
		// iEstimateService.createExcelSheet();
		return "data Created";
	}

//	@GetMapping("reportInExceldemo")
//	public String createExcelSheetdemo() throws IOException {
//		estservice.createExcelSheetdemo();
//		return "data Created";
//	}

	@GetMapping("/userdetail/stream/csv")
	@Transactional(readOnly = true)
	public void generateCSVUsingStream(HttpServletResponse response) {
		response.addHeader("Content-Type", "application/csv");
		response.addHeader("Content-Disposition", "attachment; filename=user_details.csv");
		response.setCharacterEncoding("UTF-8");
		try {
			String userDetailsStream = iEstimateService.createExcelSheet(response);
			// PrintWriter out = response.getWriter();

			// entityManager.detach(userDetailsStream);
			// out.flush();
			// out.close();
			// return "data Created";

		} catch (IOException ix) {
			throw new RuntimeException("There is an error while downloading user_details.csv", ix);
		}
	}
//
//	@GetMapping("/alldetail/stream/csv")
//	@Transactional(readOnly = true)
//	public void generateCSVUsingStreamAllInformation(HttpServletResponse response) {
//		response.addHeader("Content-Type", "application/csv");
//		response.addHeader("Content-Disposition", "attachment; filename=AyushaAll_details.csv");
//		response.setCharacterEncoding("UTF-8");
//		try {
//			String userDetailsStream = iEstimateService.createExcelSheetAllInformation(response);
//			// PrintWriter out = response.getWriter();
//
//			// entityManager.detach(userDetailsStream);
//			// out.flush();
//			// out.close();
//			// return "data Created";
//
//		} catch (IOException ix) {
//			throw new RuntimeException("There is an error while downloading user_details.csv", ix);
//		}
//	}

}
