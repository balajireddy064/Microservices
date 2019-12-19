package com.ayusha.payments.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ayusha.job.data.models.JobDataModel;
import com.ayusha.job.inventory.data.models.PartReturnedDataModel;
import com.ayusha.job.specification.EstimateSearchSpecificatoin;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.payments.data.models.CashReceiptDataModel;
import com.ayusha.payments.data.models.EstimateDataModel;
import com.ayusha.payments.data.models.EstimatesDataModel;
import com.ayusha.payments.services.entity.CashReceiptEntity;
import com.ayusha.payments.services.entity.EstimateEntity;
import com.ayusha.payments.services.repository.ICashReceiptRepository;
import com.ayusha.payments.services.repository.IEstimationRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.ticket.data.models.TicketDataModelAndCustomerDataModels;
import com.ayusha.user.data.models.UserDataModel;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch
 * @Date : 01-Aug-2019 Ticket Model class
 * 
 *       1. Recording (single): a. Persisting in DB b. on Success - sending an
 *       email/sms to customer c. assigning service invoking
 * 
 *       2. Update: a. On change of status - sending an email/sms notification
 * 
 *       3. Batch Recording: a. Persisting in DB b. on Success - sending an
 *       email/sms to customer -seggregating and sending an single email c.
 *       assigning service invoking - Individually
 * 
 *       4. Search: a. search based on date, user, customer,logged date,
 *       issue,servicetype,serialnumber
 * 
 *       5. Sorting: a. soring based on logged date,status,servicetype (ASC |
 *       DSC)
 */
@Service
public class EstimateService implements IEstimateService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(EstimateService.class);

	/** repository **/
	@Autowired
	private IEstimationRepository iEstimationRepository;
	@Autowired
	private ICashReceiptRepository iCashReceiptRepository;

	/**
	 * default constructor
	 **/
	public EstimateService() {
		LOG.info("Estimate Service Constructor");
	}

	/** this is generated random number **/
	private int value = 000;

	private String getNextSequenceNumber() {
		value++;
		String seq_num = String.format("%03d", value);
		return seq_num;
	}

	/**
	 * @method save
	 * @param estimateDataModel
	 * @return estimateDataModel
	 */
	@Override
	public EstimateDataModel save(EstimateDataModel estimateDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		EstimateEntity estimateEntity = new EstimateEntity();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyHHmmss-");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		// estimateDataModel.setEstimateId("ATAS-EST-" + dateFormat.format(now) +
		// getNextSequenceNumber());
		estimateDataModel.setGeneratedOn(dtf.format(now));
		estimateEntity.setId(Integer.parseInt("" + estimateDataModel.getId()));
//		estimateId from estimate line response
		estimateEntity.setEstimateId(estimateDataModel.getEstimateId());
		estimateEntity.setGeneratedOn(estimateDataModel.getGeneratedOn());
		estimateEntity.setJobCode(estimateDataModel.getJobCode());
		estimateEntity.setApprovalStatus(estimateDataModel.getApprovalStatus());
		estimateEntity.setRequestedBy(estimateDataModel.getRequestedBy());
		estimateEntity.setGrandTotal(estimateDataModel.getGrandTotal());
		iEstimationRepository.save(estimateEntity);
		return estimateDataModel;

	}

	/**
	 * 
	 * @method populateEstimateDataModel
	 * @param estimateEntity
	 * @param estimateDataModel
	 * 
	 */
	private void populateEstimateDataModel(EstimateEntity estimateEntity, EstimateDataModel estimateDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		estimateDataModel.setId(Integer.parseInt("" + estimateEntity.getId()));
		estimateDataModel.setEstimateId(estimateEntity.getEstimateId());
		estimateDataModel.setJobCode(estimateEntity.getJobCode());
		estimateDataModel.setGeneratedOn(estimateEntity.getGeneratedOn());
		estimateDataModel.setApprovalStatus(estimateEntity.getApprovalStatus());
		estimateDataModel.setRequestedBy(estimateEntity.getRequestedBy());
		estimateDataModel.setGrandTotal(estimateEntity.getGrandTotal());

	}

	/**
	 * @method getEstimateForJobCodeTime
	 * @param jobCode
	 * @return estimatesDataModel
	 */
	@Override
	public EstimatesDataModel getEstimateForJobCodeTime(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		List<EstimateEntity> lstEstimateEntity = iEstimationRepository.findEstimateForJobCodeTime(jobCode);
		EstimateDataModel estimateDataModel = new EstimateDataModel();
		EstimatesDataModel estimatesDataModel = new EstimatesDataModel();
		EstimateEntity estimateEntity = new EstimateEntity();
		System.out.println(iEstimationRepository.findEstimateForJobCodeTime(jobCode));
		List<EstimateDataModel> lstEstimateDataModel = new ArrayList();

		int size = lstEstimateEntity.size();
		for (int index = 0; index < size; index++) {
			estimateDataModel = new EstimateDataModel();
			estimateEntity = lstEstimateEntity.get(index);
			populateEstimateDataModel(estimateEntity, estimateDataModel);
			lstEstimateDataModel.add(estimateDataModel);
		}
		estimatesDataModel.setLstEstimates(lstEstimateDataModel);
		return estimatesDataModel;
	}

	/**
	 * @method getLastItem
	 * @param jobCode
	 * @return
	 */
	@Override
	public EstimatesDataModel getLastItem(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException {

		Page<EstimateEntity> page = iEstimationRepository
				.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "jobCode")));

		// EstimatesDataModel estimatesDataModel=
		String a = JSONConverter.convertPOJOToString(page.getContent());

		LOG.info("response last item" + a);
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @method getAllEstimates
	 * @return page
	 */
	@Override
	public Page<EstimateEntity> getAllEstimates()
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		Page<EstimateEntity> page = iEstimationRepository
				.findAll(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, "jobCode")));
		return page;
	}

	/**
	 * get By RequestParam
	 */
	public Page<EstimateDataModel> getByRequested(String requestedBy)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		List<EstimateEntity> lstEntities = iEstimationRepository.findByRequestedBy(requestedBy);
		EstimateDataModel estimateDataModel = null;
		List<EstimateDataModel> lstCashReceiptDataModels = new ArrayList<EstimateDataModel>();
		if (lstEntities != null) {
			for (EstimateEntity estimateEntity : lstEntities) {
				estimateDataModel = new EstimateDataModel();
				BeanUtils.copyProperties(estimateEntity, estimateDataModel);
				lstCashReceiptDataModels.add(estimateDataModel);
			}
			Page<EstimateDataModel> paginationResult = new PageImpl<EstimateDataModel>(lstCashReceiptDataModels);
			return paginationResult;
		} else {
			throw new ResourceNotFoundException("Data is not present.");
		}
	}

	/**
	 * get By JobCode
	 */
	public EstimateEntity getEstimateByJobCode(String jobCode)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		EstimateEntity sEntity = iEstimationRepository.findByJobCode(jobCode);
		if (sEntity != null) {
			return sEntity;
		} else {
			throw new ResourceNotFoundException("JobCode not present.");
		}
	}

	/**
	 * search
	 */
	@Override
	public Page<EstimateDataModel> searchDetails(EstimateSearchSpecificatoin userSpecification) {

		// pageable
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);
		List<EstimateEntity> estimateEntities = iEstimationRepository.findAll(userSpecification);

		List<EstimateDataModel> lstEstDataModel = new ArrayList<EstimateDataModel>();

		EstimateDataModel estimateDataModel = null;

		EstimateEntity estimateEntity = null;
		int size = estimateEntities.size();

		for (int index = 0; index < size; index++) {

			List<CashReceiptDataModel> userlst = new ArrayList<CashReceiptDataModel>();
			estimateEntity = new EstimateEntity();
			estimateDataModel = new EstimateDataModel();
			estimateEntity = estimateEntities.get(index);
			estimateDataModel.setApprovalStatus(estimateEntity.getApprovalStatus());
			estimateDataModel.setEstimateId(estimateEntity.getEstimateId());
			estimateDataModel.setGeneratedOn(estimateEntity.getGeneratedOn());
			estimateDataModel.setGrandTotal(estimateEntity.getGrandTotal());
			estimateDataModel.setJobCode(estimateEntity.getJobCode());
			estimateDataModel.setRequestedBy(estimateEntity.getRequestedBy());
			lstEstDataModel.add(estimateDataModel);

		}
		Page<EstimateDataModel> result = new PageImpl<EstimateDataModel>(lstEstDataModel);
		return result;
	}

	/**
	 * Report in excel
	 */
	public String createExcelSheet(HttpServletResponse httpServletResponse) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Job Data");

		SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Job Start Date");
		header.createCell(1).setCellValue("Job End Date");
		header.createCell(2).setCellValue("Customer Name");
		header.createCell(3).setCellValue("Address Line1");
		header.createCell(4).setCellValue("Address line2");
		header.createCell(5).setCellValue("City");
		header.createCell(6).setCellValue("State");
		header.createCell(7).setCellValue("PinCode");

		header.createCell(8).setCellValue("Category");
		header.createCell(9).setCellValue("SubCategory");
		header.createCell(10).setCellValue("Brand");
		header.createCell(11).setCellValue("Model");

		header.createCell(12).setCellValue("Service Engineer");
		header.createCell(13).setCellValue("Issue");
		header.createCell(14).setCellValue("Status");
		header.createCell(15).setCellValue("Estimated");
		header.createCell(16).setCellValue("Invoice");
		header.createCell(17).setCellValue("Parts Returns");
		header.createCell(18).setCellValue("ticketId");
		header.createCell(19).setCellValue("ticketDate");

		List<EstimateEntity> lstestimateEntity = iEstimationRepository.findAll();
		int rowNum = 1;
		for (EstimateEntity estimateEntity : lstestimateEntity) {

			CashReceiptEntity cashReceiptEntity = iCashReceiptRepository.findByJobCode(estimateEntity.getJobCode());
			RestTemplate restTemplate = new RestTemplate();
			/** get job Details information **/
			JobDataModel jobDataModel = restTemplate.getForObject(
					"http://134.209.147.111:8092/jobs/job/findJobForJobId?jobid=" + estimateEntity.getJobCode(),
					JobDataModel.class);
			/** get ticket Details informaton **/
			TicketDataModelAndCustomerDataModels ticketDataModel = restTemplate.getForObject(
					"http://134.209.147.111:8091/tickets/ticket/getByTicketId?ticketId=" + jobDataModel.getTicketId(),
					TicketDataModelAndCustomerDataModels.class);

			PartReturnedDataModel partReturnedDataModel = restTemplate
					.getForObject("http://134.209.147.111:8097/jobinventory/partsreturned/getByJobCode?jobCode="
							+ estimateEntity.getJobCode(), PartReturnedDataModel.class);

			Row dataRow = sheet.createRow(rowNum++);
			dataRow.createCell(0).setCellValue(jobDataModel.getStartDate());
			dataRow.createCell(1).setCellValue(jobDataModel.getActualEndDate());
			dataRow.createCell(2).setCellValue(jobDataModel.getCustomerName());
			dataRow.createCell(3).setCellValue(ticketDataModel.getAddress1());
			dataRow.createCell(4).setCellValue(ticketDataModel.getAddress2());
			dataRow.createCell(5).setCellValue(ticketDataModel.getCity());
			dataRow.createCell(6).setCellValue(ticketDataModel.getState());
			dataRow.createCell(7).setCellValue(ticketDataModel.getPinCode());

			dataRow.createCell(8).setCellValue(ticketDataModel.getCategory());
			dataRow.createCell(9).setCellValue(ticketDataModel.getSubCategory());
			dataRow.createCell(10).setCellValue(ticketDataModel.getBrand());
			dataRow.createCell(11).setCellValue(ticketDataModel.getModel());
			dataRow.createCell(12).setCellValue(jobDataModel.getUserId());
			dataRow.createCell(13).setCellValue(ticketDataModel.getDescription());
			dataRow.createCell(14).setCellValue(jobDataModel.getStatus());
			dataRow.createCell(15).setCellValue(estimateEntity.getGrandTotal());
			dataRow.createCell(16).setCellValue(cashReceiptEntity.getGrandTotal());
			dataRow.createCell(17).setCellValue(partReturnedDataModel.getRemarks());
			dataRow.createCell(18).setCellValue(jobDataModel.getTicketId());
			dataRow.createCell(19).setCellValue(ticketDataModel.getVisitDate());

		}

		ByteArrayOutputStream by = new ByteArrayOutputStream();

		// FileOutputStream out = new FileOutputStream(new
		// File("F:\\Report\\Job_Report.xlsx"));

		workbook.write(by);
		httpServletResponse.getOutputStream().write(by.toByteArray());

		by.close();

		// out.close();
		return "excel read";

	}
//
//	public String createExcelSheetAllInformation(HttpServletResponse httpServletResponse) throws IOException {
//
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet userSheet = workbook.createSheet("User Data");
//		XSSFSheet jobSheet = workbook.createSheet("Job Data");
//		XSSFSheet ticketSheet = workbook.createSheet("Ticket Data");
//		XSSFSheet paymentSheet = workbook.createSheet("Payment Data");
//
//		/** this is a UserINformation **/
//		SheetConditionalFormatting sheetCF = userSheet.getSheetConditionalFormatting();
//		Row header = userSheet.createRow(0);
//		header.createCell(0).setCellValue("About Me");
//		header.createCell(1).setCellValue("Address");
//		header.createCell(2).setCellValue("Age");
//		header.createCell(3).setCellValue("City");
//		header.createCell(4).setCellValue("CreatedOn");
//		header.createCell(5).setCellValue("dob");
//		header.createCell(6).setCellValue("doj");
//		header.createCell(7).setCellValue("email");
//
//		header.createCell(8).setCellValue("ExpertLevel");
//		header.createCell(9).setCellValue("FirstName");
//		header.createCell(10).setCellValue("Gender");
//		header.createCell(11).setCellValue("LastName");
//
//		header.createCell(12).setCellValue("Phone Number");
//		header.createCell(13).setCellValue("PinCode");
//		header.createCell(14).setCellValue("Role");
//		header.createCell(15).setCellValue("Salary");
//		header.createCell(16).setCellValue("skills");
//		header.createCell(17).setCellValue("state");
//		header.createCell(18).setCellValue("UserId");
//
//		/** this is a TicketInformation **/
//		SheetConditionalFormatting ticketsheetCF = userSheet.getSheetConditionalFormatting();
//		Row ticketHeader = ticketSheet.createRow(0);
//		ticketHeader.createCell(0).setCellValue("CallType");
//		ticketHeader.createCell(1).setCellValue("Brand");
//		ticketHeader.createCell(2).setCellValue("Category");
//		ticketHeader.createCell(3).setCellValue("SubCategory");
//		ticketHeader.createCell(4).setCellValue("Model");
//
//		ticketHeader.createCell(5).setCellValue("SerialNumber");
//		ticketHeader.createCell(6).setCellValue("TimeOfVisit");
//		ticketHeader.createCell(7).setCellValue("VisitDate");
//
//		ticketHeader.createCell(8).setCellValue("WarrantyInformation");
//		ticketHeader.createCell(9).setCellValue("TicketId");
//		ticketHeader.createCell(10).setCellValue("CustomerId");
//		ticketHeader.createCell(11).setCellValue("CustomerName");
//
//		ticketHhttp: // 134.209.147.111:8095/users/user/getAlleader.createCell(12).setCellValue("Addres1");
//		ticketHeader.createCell(13).setCellValue("Addres2");
//		ticketHeader.createCell(14).setCellValue("Street");
//		ticketHeader.createCell(15).setCellValue("State");
//		ticketHeader.createCell(16).setCellValue("City");
//		ticketHeader.createCell(17).setCellValue("Pincode");
//		ticketHeader.createCell(18).setCellValue("Email");
//
//		ticketHeader.createCell(19).setCellValue("ContectNumber");
//		ticketHeader.createCell(20).setCellValue("DealerName");
//		ticketHeader.createCell(21).setCellValue("Problem");
//		ticketHeader.createCell(22).setCellValue("LastUpdatedOn");
//		ticketHeader.createCell(23).setCellValue("status");
//		ticketHeader.createCell(24).setCellValue("userId");
//		ticketHeader.createCell(25).setCellValue("Product");
//
////		List<EstimateEntity> lstestimateEntity = iEstimationRepository.findAll();
////		int rowNum = 1;
////		for (EstimateEntity estimateEntity : lstestimateEntity) {
//
////			CashReceiptEntity cashReceiptEntity = iCashReceiptRepository.findByJobCode(estimateEntity.getJobCode());
//		RestTemplate restTemplate = new RestTemplate();
////			/** get job Details information **/
////			JobDataModel jobDataModel = restTemplate.getForObject(
////					"http://localhost:8092/jobs/job/findJobForJobId?jobid=" + estimateEntity.getJobCode(),
////					JobDataModel.class);
////			UserDataModel userDataModel = restTemplate.getForObject("http://134.209.147.111:8095//users/user/getAll",
////					UserDataModel.class);
////			/** get ticket Details informaton **/
////			TicketDataModel ticketDataModel = restTemplate
////					.getForObject("http://134.209.147.111:8091/tickets/ticket/getAllTickets", TicketDataModel.class);
//
////
////			PartReturnedDataModel partReturnedDataModel = restTemplate.getForObject(
////					"http://localhost:8097/jobinventory/partsreturned/find?jobid=" + estimateEntity.getJobCode(),
////					PartReturnedDataModel.class);
////
////			com.ayusha.ticket.data.models.CustomerDataModel response = ticketDataModel.getCustomerDataModel();
////			 
////
////			CustomerDataModel customerDataModel = new CustomerDataModel();
////			customerDataModel.setFirstName(ticketDataModel.getCustomerDataModel().getCustomerName());
////			customerDataModel.setAddr1(ticketDataModel.getCustomerDataModel().getAddress1());
////			customerDataModel.setAddr2(ticketDataModel.getCustomerDataModel().getAddress2());
////			customerDataModel.setState(ticketDataModel.getCustomerDataModel().getState());
////			customerDataModel.setCity(ticketDataModel.getCustomerDataModel().getCity());
////			customerDataModel.setPin(ticketDataModel.getCustomerDataModel().getPinCode());
////			customerDataModel.setEmail(ticketDataModel.getCustomerDataModel().getEmail());
////			customerDataModel.setPhoneNumber(ticketDataModel.getCustomerDataModel().getContactNumber());
////			customerDataModel.setCustomerId(ticketDataModel.getCustomerId());
//
//		ResponseEntity<UserDataModel> responseEntity = new RestTemplate()
//				.getForEntity("http://134.209.147.111:8095/users/user/getAll", UserDataModel.class);
//		UserDataModel user = responseEntity.getBody();
//		System.out.println("-----------------============== " + user.getCity());
//
////		UserDataModel lstuserDataModel = restTemplate.getForObject("http://134.209.147.111:8095/users/user/getAll",
////				UserDataModel.class);
////		List<UserDataModel> lstuserDataModel = restTemplate.getForObject("http://localhost:8095/users/user/getAllDemo",
////				UserDataModel.class);
////		
//		Collection<UserDataModel> list = restTemplate.getForObject("http://134.209.147.111:8095/users/user/getAll",
//				Collection.class);
//		int size = list.size();
//		System.out.println("------------------------" + size);
//		List<UserDataModel> arr = new ArrayList<UserDataModel>();
//		// arr.add(lstuserDataModel);
//
//		int usetNowNum = 1;
//		for (UserDataModel userDataModel : arr) {
//			System.out.println("userD" + userDataModel.getDateOfBirth());
//
//			Row userDataRow = userSheet.createRow(usetNowNum++);
//			userDataRow.createCell(0).setCellValue(userDataModel.getAboutMe());
//			userDataRow.createCell(1).setCellValue(userDataModel.getAddress());
//			userDataRow.createCell(2).setCellValue(userDataModel.getAge());
//			userDataRow.createCell(3).setCellValue(userDataModel.getCity());
//			userDataRow.createCell(4).setCellValue(userDataModel.getCreatedOn());
//			userDataRow.createCell(5).setCellValue(userDataModel.getDateOfBirth());
//			userDataRow.createCell(6).setCellValue(userDataModel.getDateOfJoining());
//			userDataRow.createCell(7).setCellValue(userDataModel.getEmail());
//
//			userDataRow.createCell(8).setCellValue(userDataModel.getExpertiseLevel());
//			userDataRow.createCell(9).setCellValue(userDataModel.getFirstName());
//			userDataRow.createCell(10).setCellValue(userDataModel.getGender());
//			userDataRow.createCell(11).setCellValue(userDataModel.getLastName());
//			userDataRow.createCell(12).setCellValue(userDataModel.getPhoneNumber());
//			userDataRow.createCell(13).setCellValue(userDataModel.getPinCode());
//			userDataRow.createCell(14).setCellValue(userDataModel.getRole());
//			userDataRow.createCell(15).setCellValue(userDataModel.getSalary());
//			userDataRow.createCell(16).setCellValue(userDataModel.getSkills());
//			userDataRow.createCell(17).setCellValue(userDataModel.getState());
//			userDataRow.createCell(18).setCellValue(userDataModel.getUserId());
//		}
//		/** informationData **/
//
//		int TicketRowNum = 1;
//		/** get ticket Details informaton **/
//		TicketDataModel lsitticketDataModel = restTemplate
//				.getForObject("http://134.209.147.111:8091/tickets/ticket/getAllTickets", TicketDataModel.class);
//		System.out.println("=================== " + lsitticketDataModel.getCustomerId());
//		List<TicketDataModel> array = new ArrayList<TicketDataModel>();
//		array.add(lsitticketDataModel);
//		for (TicketDataModel ticketDataModel : array) {
//
//			Row ticketDataRow = ticketSheet.createRow(TicketRowNum++);
//
//			ticketDataRow.createCell(0).setCellValue(ticketDataModel.getCallType());
//			ticketDataRow.createCell(1).setCellValue(ticketDataModel.getBrand());
//			ticketDataRow.createCell(2).setCellValue(ticketDataModel.getCategory());
//			ticketDataRow.createCell(3).setCellValue(ticketDataModel.getSubCategory());
//			ticketDataRow.createCell(4).setCellValue(ticketDataModel.getModel());
//
//			ticketDataRow.createCell(5).setCellValue(ticketDataModel.getSerialNumber());
//			ticketDataRow.createCell(6).setCellValue(ticketDataModel.getVisitTime());
//			ticketDataRow.createCell(7).setCellValue(ticketDataModel.getVisitDate());
//
//			ticketDataRow.createCell(8).setCellValue(ticketDataModel.getWarranty());
//			ticketDataRow.createCell(9).setCellValue(ticketDataModel.getTicketId());
//			ticketDataRow.createCell(10).setCellValue(ticketDataModel.getCustomerId());
//
////			ticketDataRow.createCell(11).setCellValue(customerDataModel.getFirstName());
////
////			ticketDataRow.createCell(12).setCellValue(customerDataModel.getAddr1());
////			ticketDataRow.createCell(13).setCellValue(customerDataModel.getAddr2());
////			ticketDataRow.createCell(14).setCellValue(customerDataModel.getState());
////			ticketDataRow.createCell(15).setCellValue(customerDataModel.getState());
////			ticketDataRow.createCell(16).setCellValue(customerDataModel.getCity());
////			ticketDataRow.createCell(17).setCellValue(customerDataModel.getPin());
////			ticketDataRow.createCell(18).setCellValue(customerDataModel.getEmail());
////
////			ticketDataRow.createCell(19).setCellValue(customerDataModel.getPhoneNumber());
//			ticketDataRow.createCell(20).setCellValue(ticketDataModel.getDealerName());
//			ticketDataRow.createCell(21).setCellValue(ticketDataModel.getDescription());
//			ticketDataRow.createCell(22).setCellValue(ticketDataModel.getLastupdatedon());
//			ticketDataRow.createCell(23).setCellValue(ticketDataModel.getStatus());
//			ticketDataRow.createCell(24).setCellValue(ticketDataModel.getUserId());
//			ticketDataRow.createCell(25).setCellValue(ticketDataModel.getProductId());
//
//		}
//
//		ByteArrayOutputStream by = new ByteArrayOutputStream();
//
//		// FileOutputStream out = new
//		// FileOutputStream(newhttp://134.209.147.111:8095//users/user/getAll
//		// File("F:\\Report\\Job_Report.xlsx"));
//
//		workbook.write(by);
//
//		httpServletResponse.getOutputStream().write(by.toByteArray());
//
//		by.close();
//
//		// out.close();
//		return "excel read";
//
//	}

}
