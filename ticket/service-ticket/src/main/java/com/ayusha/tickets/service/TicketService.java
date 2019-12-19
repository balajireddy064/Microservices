package com.ayusha.tickets.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ayusha.job.data.models.JobDataModel;
import com.ayusha.products.data.models.CategoryDataModel;
import com.ayusha.products.data.models.MakeDataModel;
import com.ayusha.products.data.models.ModelDataModel;
import com.ayusha.products.data.models.ProductDataModel;
import com.ayusha.products.data.models.SubCategoryDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.NoContentException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.assinment.ConstantPropertise;
import com.ayusha.ticket.data.models.CustomerDataModel;
import com.ayusha.ticket.data.models.TicketAndCustomerDataModel;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.ticket.data.models.TicketDataModelAndCustomerDataModels;
import com.ayusha.ticket.data.models.TicketDataModelUpdate;
import com.ayusha.ticket.data.models.TicketUpdateDataModel;
import com.ayusha.ticket.data.models.TicketUserDataModel;
import com.ayusha.tickets.entity.CustomerEntity;
import com.ayusha.tickets.entity.CustomerHistryEntity;
import com.ayusha.tickets.entity.TicketEntity;
import com.ayusha.tickets.entity.TicketHistryEntity;
import com.ayusha.tickets.repository.CustomerHistryRepoistry;
import com.ayusha.tickets.repository.ICustomerRepository;
import com.ayusha.tickets.repository.IServiceTypeRepository;
import com.ayusha.tickets.repository.ITicketRepository;
import com.ayusha.tickets.repository.TicketHistryRepositry;
import com.ayusha.tickets.service.validations.TicketDataValidation;
import com.ayusha.tickets.specification.UserSpecification;
import com.ayusha.user.data.models.UserDataModel;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
import com.google.protobuf.TextFormat.ParseException;

/**
 * 
 * @author Finch Date : 01-Aug-2019 Ticket Service Methods
 * 
 *         1. Recording (single): a. Persisting in DB b. on Success - sending an
 *         email/sms to customer c. assigning service invoking
 * 
 *         2. Update: a. On change of status - sending an email/sms notification
 * 
 *         3. Batch Recording: a. Persisting in DB b. on Success - sending an
 *         email/sms to customer -seggregating and sending an single email c.
 *         assigning service invoking - Individually
 * 
 *         4. Search: a. search based on date, user, customer,logged date,
 *         issue,servicetype,serialnumber
 * 
 *         5. Sorting: a. sorting based on logged date,status,servicetype (ASC |
 *         DSC)
 */
@Service
public class TicketService implements ITicketService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(TicketService.class);

	/** iTicketRepository **/
	@Autowired
	private ITicketRepository iTicketRepository;

	/** iCustomerRepository **/
	@Autowired
	private ICustomerRepository iCustomerRepository;

	/** iCustomerService **/
	@Autowired
	private ICustomerService iCustomerService;

	/** ticketDataValidation **/
	@Autowired
	private TicketDataValidation ticketDataValidation;

	/** iServiceTypeRepository **/
	@Autowired
	private IServiceTypeRepository iServiceTypeRepository;

	/** customerHistryRepositry **/
	@Autowired
	private CustomerHistryRepoistry customerHistryRepositry;

	/** ticketHistryRepositry **/
	@Autowired
	private TicketHistryRepositry ticketHistryRepositry;

	/**
	 * default constructor
	 */
	public TicketService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * service call to get tickets by status no other service dependency
	 */
	@SuppressWarnings("unused")
	public List<TicketEntity> getTicketByStatus(String ticketCode, String status)
			throws DataPersistenceOperationException, InvalidServiceRequestException, NoContentException,
			ResourceNotFoundException, Exception {

		List<TicketEntity> ticketEntity = iTicketRepository.findTicketByStatus(ticketCode, status);
		int size = ticketEntity.size();
		if (ticketEntity.isEmpty() || ticketEntity == null) {
			LOG.info("No Data Found for the ticket id " + ticketCode);
			throw new ResourceNotFoundException("No Data Found for the ticket ");
		}
		return ticketEntity;
	}

	/**
	 * to generate random numbers
	 */
	private long value = 000;

	public String getNextSequenceNumber() {
		value++;
		String seq_num = String.format("%03d", value);
		return seq_num;
	}

	/**
	 * service to create ticket, dependency on user, product and job services
	 */
	public TicketDataModel add(TicketDataModel ticketDataModel) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		RestTemplate restTemplate = new RestTemplate();
		ProductDataModel response;

		TicketDataValidation ticketDataValidation = new TicketDataValidation();
		boolean validation = ticketDataValidation.validation(ticketDataModel);

		// check for mandatory field validation
		if (validation == true) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyHHmmss-");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter timeFormate = DateTimeFormatter.ofPattern("HH:mm:ss.SSS'Z'");
			LocalTime time = LocalTime.now();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

			ProductDataModel productModel = null;
			TicketEntity ticketEntity = new TicketEntity();
			CustomerDataModel customerDataModel = null;
			CustomerEntity customerEntity = null;
			ticketEntity.setLastupdatedon(dtf.format(now));
			ticketEntity.setLoggedon(dtf.format(now));
			String userId = null;

			ticketEntity.setBrand(ticketDataModel.getBrand());
			ticketEntity.setCallType(ticketDataModel.getCallType());
			ticketEntity.setCategory(ticketDataModel.getCategory());
			ticketEntity.setSubCategory(ticketDataModel.getSubCategory());
			ticketEntity.setModel(ticketDataModel.getModel());
			ticketEntity.setDealerName(ticketDataModel.getDealerName());
			ticketEntity.setSerialNumber(ticketDataModel.getSerialNumber());
			ticketEntity.setWarranty(ticketDataModel.getWarranty());
			ticketEntity.setVisitDate(ticketDataModel.getVisitDate());
			ticketEntity.setVisitTime(ticketDataModel.getVisitTime());

			String customerId = "";
			ticketEntity.setTicketId("ATAS-TKT-" + dateFormat.format(now) + getNextSequenceNumber());
			ticketDataModel.setTicketId("ATAS-TKT-" + dateFormat.format(now) + getNextSequenceNumber());

			// fetch product information from product table
			try {

				// fetch category name from category service
				CategoryDataModel categoryDataModel = restTemplate.getForObject(
						ConstantPropertise.PRODUCT_CATEGORY_NAME + ticketDataModel.getCategory(),
						CategoryDataModel.class);

				// fetch sub category name from sub category service
				SubCategoryDataModel subCategoryDataModel = restTemplate.getForObject(
						ConstantPropertise.PRODUCT_SUB_CATEGORY_NAME + ticketDataModel.getSubCategory(),
						SubCategoryDataModel.class);

				// fetch make name from make service
				MakeDataModel makeDataModel = restTemplate.getForObject(
						ConstantPropertise.PRODUCT_BRAND + ticketDataModel.getBrand(), MakeDataModel.class);

				// fetch model name from model service
				ModelDataModel modelDataModel = restTemplate.getForObject(
						ConstantPropertise.PRODUCT_MODEL + ticketDataModel.getModel(), ModelDataModel.class);

				// fetch product id from product service
				try {
					response = restTemplate
							.getForObject(
									ConstantPropertise.PRODUCT_PRODUCTID + categoryDataModel.getCategoryId()
											+ "&subcategoryid=" + subCategoryDataModel.getSubCategoryId() + "&makeid="
											+ makeDataModel.getMakeId() + "&modelid=" + modelDataModel.getModelId(),
									ProductDataModel.class);
					ticketEntity.setProductId(response.getProductId());

					// set the status of ticket as open
					ticketDataModel.setStatus("OPEN");

					LOG.info("No Data Found for the given set of data  =================================== "
							+ response.getProductId());

					// check if customer is available in db if not create new customer
					if (ticketDataModel.getCustomerDataModel() != null) {
						customerDataModel = ticketDataModel.getCustomerDataModel();

						// check customer phone no and email if customer is present in db
						try {
							customerEntity = iCustomerService.getCustomerByEmailId(customerDataModel.getEmail());
							if (customerEntity == null) {
								customerEntity = iCustomerService
										.getCustomerByPhoneNumber(customerDataModel.getContactNumber());
							} else {
								customerId = customerEntity.getCustomerId();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						// if customer is not present create new customer
						if (customerEntity == null) {
							customerDataModel
									.setCustomerId("ATAS-CUST-" + dateFormat.format(now) + getNextSequenceNumber());
							customerDataModel = iCustomerService.add(customerDataModel);
							customerId = customerDataModel.getCustomerId();
						}
					}
					populateTicketEntity(ticketDataModel, ticketEntity);
					ticketEntity.setCustomerId(customerId);
					ResponseEntity<List<TicketUserDataModel>> responseEntity = restTemplate.exchange(
							ConstantPropertise.GET_ALL_USERS, HttpMethod.GET, null,
							new ParameterizedTypeReference<List<TicketUserDataModel>>() {
							});
					for (TicketUserDataModel t : responseEntity.getBody()) {
						if (t.getPinCode().equalsIgnoreCase(ticketDataModel.getCustomerDataModel().getPinCode())) {
//							if (t.getPinCode().equalsIgnoreCase("Service Engineer")) {
								userId = t.getUserId();
								ticketEntity.setUserId(userId);
				//			}
						}
					}

					System.out.println("in last block data model " + ticketEntity.getTicketId());
					iTicketRepository.save(ticketEntity);
				} catch (ResourceNotFoundException e) {
					throw new ResourceNotFoundException("category subCar  brand or model is not present ");
				}
			} catch (Exception e) {
				throw new ResourceNotFoundException("Product not available");
			}
		}
		return ticketDataModel;
	}

	/**
	 * updated ticket based on ticketId
	 */
	public ResponseEntity<?> updateTicketBasedOnTicketId(String ticketId, TicketDataModel ticketDataModel)
			throws ParseException, ServicesDataException, ResourceNotFoundException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();
		TicketEntity ticketEntity = iTicketRepository.findByTickId(ticketId);
		CustomerEntity customerEntity = iCustomerRepository.findCustomerByCustomerId(ticketEntity.getCustomerId());

		if (ticketEntity == null) {
			LOG.info("No Data Found for the ticket id " + ticketId);
			throw new ResourceNotFoundException("Ticket id doesn't exit");
		}
		TicketEntity findCustomerIdAndTIcketId = iTicketRepository.findByTicketIdAndCustomerId(ticketId,
				ticketEntity.getCustomerId());
		RestTemplate restTemplate = new RestTemplate();
		CustomerHistryEntity customerHistryEntity = new CustomerHistryEntity();
		TicketHistryEntity ticketHistryEntity = new TicketHistryEntity();

		// when customer is not present create new customer
		CustomerEntity newCustomerEntity = new CustomerEntity();

		RestTemplate restTemplate1 = new RestTemplate();
		UserDataModel userLocationsUsersDataModel = restTemplate1.getForObject(
				ConstantPropertise.USERS_USER_FINDBY_USERID + ticketDataModel.getUserId(), UserDataModel.class);
		if (userLocationsUsersDataModel.getRole().equalsIgnoreCase("Service Engineer")) {
			if (userLocationsUsersDataModel != null) {
				if (customerEntity != null) {

					// insert old records in history table
					updatedOldRecords(ticketEntity, customerEntity);

					// update customer data
					customerEntity.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
					customerEntity.setAddress1(ticketDataModel.getCustomerDataModel().getAddress1());
					customerEntity.setAddress2(ticketDataModel.getCustomerDataModel().getAddress2());
					customerEntity.setStreet(ticketDataModel.getCustomerDataModel().getStreet());
					customerEntity.setCity(ticketDataModel.getCustomerDataModel().getCity());
					customerEntity.setAlternateContact(ticketDataModel.getCustomerDataModel().getAlternateContact());
					iCustomerRepository.save(customerEntity);

				} else {
					newCustomerEntity.setCustomerId(ticketEntity.getCustomerId());
					newCustomerEntity.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
					newCustomerEntity.setAddress1(ticketDataModel.getCustomerDataModel().getAddress1());
					newCustomerEntity.setAddress2(ticketDataModel.getCustomerDataModel().getAddress2());
					newCustomerEntity.setStreet(ticketDataModel.getCustomerDataModel().getStreet());
					newCustomerEntity.setCity(ticketDataModel.getCustomerDataModel().getCity());
					newCustomerEntity.setAlternateContact(ticketDataModel.getCustomerDataModel().getAlternateContact());
					newCustomerEntity.setPinCode(ticketDataModel.getCustomerDataModel().getPinCode());
					newCustomerEntity.setContactNumber(ticketDataModel.getCustomerDataModel().getContactNumber());
					newCustomerEntity.setEmail(ticketDataModel.getCustomerDataModel().getEmail());
					iCustomerRepository.save(newCustomerEntity);
				}

				// update ticket data
				ticketEntity.setCallType(ticketDataModel.getCallType());
				ticketEntity.setWarranty(ticketDataModel.getWarranty());
				ticketEntity.setDescription(ticketDataModel.getDescription());
				ticketEntity.setCategory(ticketDataModel.getCategory());
				ticketEntity.setSubCategory(ticketDataModel.getSubCategory());
				ticketEntity.setBrand(ticketDataModel.getBrand());
				ticketEntity.setModel(ticketDataModel.getModel());
				ticketEntity.setDealerName(ticketDataModel.getDealerName());
				ticketEntity.setVisitDate(ticketDataModel.getVisitDate());
				ticketEntity.setVisitTime(ticketDataModel.getVisitTime());
				ticketEntity.setLastupdatedon(dtf.format(now));
				ticketEntity.setSerialNumber(ticketDataModel.getSerialNumber());
				ticketEntity.setUserId(ticketDataModel.getUserId());
				iTicketRepository.save(ticketEntity);

				ticketDataModel.setTicketId(ticketEntity.getTicketId());
				ticketDataModel.setCustomerId(ticketEntity.getCustomerId());

				boolean bol = false;
				// call assign user to ticket method
				try {
					bol = assignuserToTiket(userLocationsUsersDataModel, ticketDataModel);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				Map<String, Object> result = new HashMap<String, Object>();
				result.put("message", "Ticket updated successfully");
				result.put("status", "200");

				return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
			} else {
				throw new ResourceNotFoundException("User id is not found");
			}
		} else {
			throw new ResourceNotFoundException("User is not a service engineer");
		}
	}

	// method to assign user to ticket based on the role of user
	private boolean assignuserToTiket(UserDataModel locationsUsersDataModel, TicketDataModel ticketDataModel)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {

		try {

			if (locationsUsersDataModel != null
					&& locationsUsersDataModel.getRole().equalsIgnoreCase("Service Engineer")) {
				LOG.info("================================ " + locationsUsersDataModel.getRole());
				RestTemplate restTemplate = new RestTemplate();
				JobDataModel jobDataModel = restTemplate.postForObject(ConstantPropertise.JOB_ASSIGN_WITH_WEB,
						ticketDataModel, JobDataModel.class);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// store the data during ticket update
	public void updatedOldRecords(TicketEntity ticketEntity, CustomerEntity customerEntity) {

		CustomerHistryEntity customerHistryEntity = new CustomerHistryEntity();
		TicketHistryEntity ticketHistryEntity = new TicketHistryEntity();

		customerHistryEntity.setCustomerId(customerEntity.getCustomerId());
		customerHistryEntity.setCustomerName(customerEntity.getCustomerName());
		customerHistryEntity.setAddress1(customerEntity.getAddress1());
		customerHistryEntity.setAddress2(customerEntity.getAddress2());
		customerHistryEntity.setCity(customerEntity.getCity());
		customerHistryEntity.setState(customerEntity.getState());
		customerHistryEntity.setStreet(customerEntity.getStreet());
		customerHistryEntity.setEmail(customerEntity.getEmail());
		customerHistryEntity.setPinCode(customerEntity.getPinCode());
		customerHistryEntity.setContactNumber(customerEntity.getContactNumber());
		customerHistryEntity.setAlternateContact(customerEntity.getAlternateContact());
		customerHistryRepositry.save(customerHistryEntity);

		// ticket information
		ticketHistryEntity.setTicketId(ticketEntity.getTicketId());
		ticketHistryEntity.setCallType(ticketEntity.getCallType());
		ticketHistryEntity.setCategory(ticketEntity.getCategory());
		ticketHistryEntity.setSubCategory(ticketEntity.getSubCategory());
		ticketHistryEntity.setBrand(ticketEntity.getBrand());
		ticketHistryEntity.setModel(ticketEntity.getModel());
		ticketHistryEntity.setDealerName(ticketEntity.getDealerName());
		ticketHistryEntity.setDescription(ticketEntity.getDescription());
		ticketHistryEntity.setSerialNumber(ticketEntity.getSerialNumber());
		ticketHistryEntity.setProductId(ticketEntity.getProductId());
		ticketHistryEntity.setVisitDate(ticketEntity.getVisitDate());
		ticketHistryEntity.setVisitTime(ticketEntity.getVisitTime());
		ticketHistryEntity.setUserId(ticketEntity.getUserId());
		ticketHistryEntity.setCustomerId(ticketEntity.getCustomerId());
		ticketHistryEntity.setStatus(ticketEntity.getStatus());
		ticketHistryEntity.setWarranty(ticketEntity.getWarranty());
		ticketHistryRepositry.save(ticketHistryEntity);

	}

	/**
	 * service to set the data from ticket model to ticket entity, no other service
	 * dependency
	 */
	private void populateTicketEntity(TicketDataModel ticketDataModel, TicketEntity ticketEntity)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception {

		ticketEntity.setDescription(ticketDataModel.getDescription());
		ticketEntity.setSerialNumber(ticketDataModel.getSerialNumber());
		ticketEntity.setStatus(ticketDataModel.getStatus());
		ticketEntity.setTicketId(ticketDataModel.getTicketId());
		ticketEntity.setLoggedon(ticketDataModel.getLoggedon());

		if (ticketDataModel.getCustomerDataModel() != null
				&& ticketDataModel.getCustomerDataModel().getCustomerId() != null) {
			ticketEntity.setCustomerId((ticketDataModel.getCustomerDataModel().getCustomerId()));
		}
	}

	/**
	 * service to update ticket status based on ticket ID, no other service
	 * dependency
	 */
	public TicketEntity updatePartlyBy(String ticketId, TicketDataModelUpdate ticketUpdateDataModel) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YY-HH:mm:ss");
		Date date = new Date();
		TicketEntity ticketEntity = iTicketRepository.findByTicketId(ticketId);
		ticketEntity.setStatus(ticketUpdateDataModel.getStatus());
		iTicketRepository.save(ticketEntity);
		return ticketEntity;
	}

	/**
	 * service method to get ticket details based on ticket id, no other service
	 * dependency
	 */
	public TicketEntity getTicketDetails(String ticketId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		// get ticket details from db
		TicketEntity ticketEntity = iTicketRepository.findByTicketId(ticketId);
		return ticketEntity;
	}

	/**
	 * service call to get all tickets details, dependency on product service
	 */
	public Page<TicketDataModel> getTickets(Pageable pg) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		TicketDataModel ticketDataModel;
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);
		List<TicketEntity> lstTicketEntities = iTicketRepository.findAllTickets(pg);
		List<TicketDataModel> ticketAndCustomerDataModel = new ArrayList<TicketDataModel>();
		ProductDataModel productDataModel = new ProductDataModel();
		ProductDataModel pm;
		RestTemplate restTemplate = new RestTemplate();
		CustomerDataModel cusE;
		TicketEntity ticketEntity = null;
		CustomerEntity ce;

		LOG.info(" Data Found for the ticket ");
		for (TicketEntity p : lstTicketEntities) {
			ticketDataModel = new TicketDataModel();
			cusE = new CustomerDataModel();
			pm = new ProductDataModel();
			ticketDataModel.setBrand(p.getBrand());
			ticketDataModel.setCallType(p.getCallType());
			ticketDataModel.setCategory(p.getCategory());
			ticketDataModel.setSubCategory(p.getSubCategory());
			ticketDataModel.setModel(p.getModel());
			ticketDataModel.setDescription(p.getDescription());
			ticketDataModel.setSerialNumber(p.getSerialNumber());
			ticketDataModel.setVisitDate(p.getVisitDate());
			ticketDataModel.setVisitTime(p.getVisitTime());
			ticketDataModel.setWarranty(p.getWarranty());
			ticketDataModel.setTicketId(p.getTicketId());
			ticketDataModel.setDealerName(p.getDealerName());
			ticketDataModel.setStatus(p.getStatus());
			ticketDataModel.setLoggedon(p.getLoggedon());
			ticketDataModel.setLastupdatedon(p.getLastupdatedon());
			ticketDataModel.setUserId(p.getUserId());
			ticketDataModel.setCustomerId(p.getCustomerId());
			ticketDataModel.setProductId(p.getProductId());

			// get product details from product service
			productDataModel = restTemplate.getForObject(ConstantPropertise.PRODUCT_FINDBY_PRODUCTID + p.getProductId(),
					ProductDataModel.class);
			if (productDataModel == null) {
				pm.setId(0);
				pm.setCategoryId("");
				pm.setCategoryName("");
				pm.setMakeId("");
				pm.setMakeName("");
				pm.setProductId("");
				pm.setSubCategoryId("");
				pm.setSubCategoryName("");
				pm.setModelName("");
				pm.setModelId("");
				ticketDataModel.setProductModel(pm);
			} else {
				ticketDataModel.setProductModel(productDataModel);
			}

			// get customer info
			ce = iCustomerRepository.findCustomerByCustomerCode(p.getCustomerId());
			if (ce == null) {
				cusE.setAddress1("");
				cusE.setAddress2("");
				cusE.setAlternateContact("");
				cusE.setCity("");
				cusE.setContactNumber("");
				cusE.setCustomerId("");
				cusE.setCustomerName("");
				cusE.setEmail("");
				cusE.setPinCode("");
				cusE.setState("");
				cusE.setStreet("");
				ticketDataModel.setCustomerDataModel(cusE);
			} else {
				cusE.setId(ce.getId());
				cusE.setAddress1(ce.getAddress1());
				cusE.setAddress2(ce.getAddress2());
				cusE.setAlternateContact(ce.getAlternateContact());
				cusE.setCity(ce.getCity());
				cusE.setContactNumber(ce.getContactNumber());
				cusE.setCustomerId(ce.getCustomerId());
				cusE.setCustomerName(ce.getCustomerName());
				cusE.setEmail(ce.getEmail());
				cusE.setPinCode(ce.getPinCode());
				cusE.setState(ce.getState());
				cusE.setStreet(ce.getStreet());
				ticketDataModel.setCustomerDataModel(cusE);
			}
			ticketAndCustomerDataModel.add(ticketDataModel);
		}
		Page<TicketDataModel> result = new PageImpl<TicketDataModel>(ticketAndCustomerDataModel);
		return result;
	}

	/**
	 * get ticket by user ID dependency on product service
	 */
	@SuppressWarnings("unused")
	public List<TicketDataModel> getByUserId(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		TicketDataModel ticketDataModel = new TicketDataModel();
		List<TicketEntity> lstTicketEntities = iTicketRepository.findByUserId(userId);
		List<TicketDataModel> ticketAndCustomerDataModel = new ArrayList<TicketDataModel>();
		ProductDataModel productDataModel = new ProductDataModel();
		ProductDataModel pm;
		RestTemplate restTemplate = new RestTemplate();
		CustomerDataModel cusE;
		TicketEntity ticketEntity = null;
		CustomerEntity ce;

		// ticket information
		for (TicketEntity p : lstTicketEntities) {
			ticketDataModel = new TicketDataModel();
			cusE = new CustomerDataModel();
			pm = new ProductDataModel();
			ticketDataModel.setBrand(p.getBrand());
			ticketDataModel.setCallType(p.getCallType());
			ticketDataModel.setCategory(p.getCategory());
			ticketDataModel.setSubCategory(p.getSubCategory());
			ticketDataModel.setModel(p.getModel());
			ticketDataModel.setDescription(p.getDescription());
			ticketDataModel.setSerialNumber(p.getSerialNumber());
			ticketDataModel.setVisitDate(p.getVisitDate());
			ticketDataModel.setVisitTime(p.getVisitTime());
			ticketDataModel.setWarranty(p.getWarranty());
			ticketDataModel.setTicketId(p.getTicketId());
			ticketDataModel.setDealerName(p.getDealerName());
			ticketDataModel.setStatus(p.getStatus());
			ticketDataModel.setLoggedon(p.getLoggedon());
			ticketDataModel.setLastupdatedon(p.getLastupdatedon());
			ticketDataModel.setUserId(p.getUserId());
			ticketDataModel.setCustomerId(p.getCustomerId());
			ticketDataModel.setProductId(p.getProductId());

			// get product id
			productDataModel = restTemplate.getForObject(ConstantPropertise.PRODUCT_FINDBY_PRODUCTID + p.getProductId(),
					ProductDataModel.class);
			if (productDataModel == null) {
				pm.setId(0);
				pm.setCategoryId("");
				pm.setCategoryName("");
				pm.setMakeId("");
				pm.setMakeName("");
				pm.setProductId("");
				pm.setSubCategoryId("");
				pm.setSubCategoryName("");
				pm.setModelName("");
				pm.setModelId("");
				ticketDataModel.setProductModel(pm);
			} else {
				ticketDataModel.setProductModel(productDataModel);
			}

			// get customer info
			ce = iCustomerRepository.findCustomerByCustomerCode(p.getCustomerId());
			if (ce == null) {
				cusE.setAddress1("");
				cusE.setAddress2("");
				cusE.setAlternateContact("");
				cusE.setCity("");
				cusE.setContactNumber("");
				cusE.setCustomerId("");
				cusE.setCustomerName("");
				cusE.setEmail("");
				cusE.setPinCode("");
				cusE.setState("");
				cusE.setStreet("");
				ticketDataModel.setCustomerDataModel(cusE);
			} else {
				cusE.setId(ce.getId());
				cusE.setAddress1(ce.getAddress1());
				cusE.setAddress2(ce.getAddress2());
				cusE.setAlternateContact(ce.getAlternateContact());
				cusE.setCity(ce.getCity());
				cusE.setContactNumber(ce.getContactNumber());
				cusE.setCustomerId(ce.getCustomerId());
				cusE.setCustomerName(ce.getCustomerName());
				cusE.setEmail(ce.getEmail());
				cusE.setPinCode(ce.getPinCode());
				cusE.setState(ce.getState());
				cusE.setStreet(ce.getStreet());
				ticketDataModel.setCustomerDataModel(cusE);
			}
			ticketAndCustomerDataModel.add(ticketDataModel);
		}
		return ticketAndCustomerDataModel;
	}

	/**
	 * service to fetch tickets based on visit date, deoendency on product service
	 */
	public Page<TicketDataModel> getByDate(Date visitDate, Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		TicketDataModel ticketDataModel = new TicketDataModel();
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);
		List<TicketDataModel> ticketAndCustomerDataModel = new ArrayList<TicketDataModel>();
		LOG.info("Get all tickets greater than and equal to visitDate");

		List<TicketEntity> lstTicketEntities = iTicketRepository.findTicketsByVisitDate(visitDate, pageable);
		ProductDataModel productDataModel = new ProductDataModel();
		ProductDataModel pm;
		RestTemplate restTemplate = new RestTemplate();
		CustomerDataModel cusE;
		TicketEntity ticketEntity = null;
		CustomerEntity ce;

		// ticket information
		for (TicketEntity p : lstTicketEntities) {
			ticketDataModel = new TicketDataModel();
			cusE = new CustomerDataModel();
			pm = new ProductDataModel();
			ticketDataModel.setBrand(p.getBrand());
			ticketDataModel.setCallType(p.getCallType());
			ticketDataModel.setCategory(p.getCategory());
			ticketDataModel.setSubCategory(p.getSubCategory());
			ticketDataModel.setModel(p.getModel());
			ticketDataModel.setDescription(p.getDescription());
			ticketDataModel.setSerialNumber(p.getSerialNumber());
			ticketDataModel.setVisitDate(p.getVisitDate());
			ticketDataModel.setVisitTime(p.getVisitTime());
			ticketDataModel.setWarranty(p.getWarranty());
			ticketDataModel.setTicketId(p.getTicketId());
			ticketDataModel.setDealerName(p.getDealerName());
			ticketDataModel.setStatus(p.getStatus());
			ticketDataModel.setLoggedon(p.getLoggedon());
			ticketDataModel.setLastupdatedon(p.getLastupdatedon());
			ticketDataModel.setUserId(p.getUserId());
			ticketDataModel.setCustomerId(p.getCustomerId());
			ticketDataModel.setProductId(p.getProductId());

			// find product id
			productDataModel = restTemplate.getForObject(ConstantPropertise.PRODUCT_FINDBY_PRODUCTID + p.getProductId(),
					ProductDataModel.class);
			if (productDataModel == null) {
				pm.setId(0);
				pm.setCategoryId("");
				pm.setCategoryName("");
				pm.setMakeId("");
				pm.setMakeName("");
				pm.setProductId("");
				pm.setSubCategoryId("");
				pm.setSubCategoryName("");
				pm.setModelName("");
				pm.setModelId("");
				ticketDataModel.setProductModel(pm);
			} else {
				ticketDataModel.setProductModel(productDataModel);
			}

			// get customer information
			ce = iCustomerRepository.findCustomerByCustomerCode(p.getCustomerId());
			if (ce == null) {
				cusE.setAddress1("");
				cusE.setAddress2("");
				cusE.setAlternateContact("");
				cusE.setCity("");
				cusE.setContactNumber("");
				cusE.setCustomerId("");
				cusE.setCustomerName("");
				cusE.setEmail("");
				cusE.setPinCode("");
				cusE.setState("");
				cusE.setStreet("");
				ticketDataModel.setCustomerDataModel(cusE);
			} else {
				cusE.setId(ce.getId());
				cusE.setAddress1(ce.getAddress1());
				cusE.setAddress2(ce.getAddress2());
				cusE.setAlternateContact(ce.getAlternateContact());
				cusE.setCity(ce.getCity());
				cusE.setContactNumber(ce.getContactNumber());
				cusE.setCustomerId(ce.getCustomerId());
				cusE.setCustomerName(ce.getCustomerName());
				cusE.setEmail(ce.getEmail());
				cusE.setPinCode(ce.getPinCode());
				cusE.setState(ce.getState());
				cusE.setStreet(ce.getStreet());
				ticketDataModel.setCustomerDataModel(cusE);
			}
			ticketAndCustomerDataModel.add(ticketDataModel);
		}

		Page<TicketDataModel> result = new PageImpl<TicketDataModel>(ticketAndCustomerDataModel);
		return result;

	}

	/**
	 * service to get tickets based on cities, service dependency on product service
	 */
	@Override
	public Page<TicketAndCustomerDataModel> getCities(String city_filter, Pageable pageable)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception {

		List<TicketEntity> ticketEntities = iTicketRepository.findAll();
		CustomerEntity customerEntity = null;
		List<TicketAndCustomerDataModel> ticketAndCustomerDataModel = new ArrayList<TicketAndCustomerDataModel>();
		CustomerDataModel customerDataModel = new CustomerDataModel();

		for (int i = 0; i < ticketEntities.size(); i++) {

			customerEntity = iCustomerRepository.findCustomerByCustomerCode(ticketEntities.get(i).getCustomerId());
			if (customerEntity.getCity().equalsIgnoreCase(city_filter)) {
				customerDataModel.setId(customerEntity.getId());
				customerDataModel.setAddress1(customerEntity.getAddress1());
				customerDataModel.setAddress2(customerEntity.getAddress2());
				customerDataModel.setCity(customerEntity.getCity());
				customerDataModel.setContactNumber(customerEntity.getContactNumber());
				customerDataModel.setCustomerId(customerEntity.getCustomerId());
				customerDataModel.setEmail(customerEntity.getEmail());
				customerDataModel.setPinCode(customerEntity.getPinCode());
				customerDataModel.setState(customerEntity.getState());
				customerDataModel.setStreet(customerEntity.getStreet());
				customerDataModel.setCustomerName(customerEntity.getCustomerName());
				ticketAndCustomerDataModel.add(new TicketAndCustomerDataModel(ticketEntities.get(i).getId(),
						ticketEntities.get(i).getCallType(), ticketEntities.get(i).getBrand(),
						ticketEntities.get(i).getCategory(), ticketEntities.get(i).getSubCategory(),
						ticketEntities.get(i).getModel(), ticketEntities.get(i).getSerialNumber(),
						ticketEntities.get(i).getWarranty(), ticketEntities.get(i).getVisitTime(),
						ticketEntities.get(i).getVisitDate(), ticketEntities.get(i).getDealerName(),
						ticketEntities.get(i).getDescription(), ticketEntities.get(i).getStatus(),
						ticketEntities.get(i).getLastupdatedon(), ticketEntities.get(i).getTicketId(),
						ticketEntities.get(i).getCustomerId(), customerDataModel));
			}
		}

		Pageable cityPageble = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "city"));
		Page<TicketAndCustomerDataModel> page = new PageImpl<TicketAndCustomerDataModel>(ticketAndCustomerDataModel,
				pageable, pageable.getPageSize());
		return page;
	}

	/**
	 * service to get ticket based on ticket Id, no other service dependency
	 */
	@SuppressWarnings("null")
	@Override
	public TicketDataModelAndCustomerDataModels getByTicketIdAndCustomer(String ticketId)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception {
		TicketDataModel ticketDataModel = new TicketDataModel();
		TicketDataModelAndCustomerDataModels ticketDataModelAndCustomerDataModels = new TicketDataModelAndCustomerDataModels();
		TicketEntity ticketEntity = iTicketRepository.findByTickId(ticketId);

		if (ticketEntity == null) {
			throw new ResourceNotFoundException("Ticket data not available");
		}

		CustomerEntity lstCustomerEntities = iCustomerRepository.findByCustId(ticketEntity.getCustomerId());
		if (lstCustomerEntities != null) {

			ticketDataModelAndCustomerDataModels.setCustomerName(lstCustomerEntities.getCustomerName());
			ticketDataModelAndCustomerDataModels.setAddress1(lstCustomerEntities.getAddress1());
			ticketDataModelAndCustomerDataModels.setAddress2(lstCustomerEntities.getAddress2());
			ticketDataModelAndCustomerDataModels.setCity(lstCustomerEntities.getCity());
			ticketDataModelAndCustomerDataModels.setState(lstCustomerEntities.getState());
			ticketDataModelAndCustomerDataModels.setStreet(lstCustomerEntities.getStreet());
			ticketDataModelAndCustomerDataModels.setPinCode(lstCustomerEntities.getPinCode());
			ticketDataModelAndCustomerDataModels.setContactNumber(lstCustomerEntities.getContactNumber());
			ticketDataModelAndCustomerDataModels.setEmail(lstCustomerEntities.getEmail());
			ticketDataModelAndCustomerDataModels.setAlternateContact(lstCustomerEntities.getAlternateContact());

		} else {
			ticketDataModelAndCustomerDataModels.setAddress1("");
			ticketDataModelAndCustomerDataModels.setAddress2("");
			ticketDataModelAndCustomerDataModels.setAlternateContact("");
			ticketDataModelAndCustomerDataModels.setCity("");
			ticketDataModelAndCustomerDataModels.setContactNumber("");
			ticketDataModelAndCustomerDataModels.setCustomerId("");
			ticketDataModelAndCustomerDataModels.setCustomerName("");
			ticketDataModelAndCustomerDataModels.setEmail("");
			ticketDataModelAndCustomerDataModels.setPinCode("");
			ticketDataModelAndCustomerDataModels.setState("");
			ticketDataModelAndCustomerDataModels.setStreet("");

		}
		ticketDataModelAndCustomerDataModels.setBrand(ticketEntity.getBrand());
		ticketDataModelAndCustomerDataModels.setCallType(ticketEntity.getCallType());
		ticketDataModelAndCustomerDataModels.setCategory(ticketEntity.getCategory());
		ticketDataModelAndCustomerDataModels.setSubCategory(ticketEntity.getSubCategory());
		ticketDataModelAndCustomerDataModels.setModel(ticketEntity.getModel());
		ticketDataModelAndCustomerDataModels.setDescription(ticketEntity.getDescription());
		ticketDataModelAndCustomerDataModels.setSerialNumber(ticketEntity.getSerialNumber());
		ticketDataModelAndCustomerDataModels.setVisitDate(ticketEntity.getVisitDate());
		ticketDataModelAndCustomerDataModels.setVisitTime(ticketEntity.getVisitTime());
		ticketDataModelAndCustomerDataModels.setWarranty(ticketEntity.getWarranty());
		ticketDataModelAndCustomerDataModels.setTicketId(ticketEntity.getTicketId());
		ticketDataModelAndCustomerDataModels.setDealerName(ticketEntity.getDealerName());
		ticketDataModelAndCustomerDataModels.setStatus(ticketEntity.getStatus());
		ticketDataModelAndCustomerDataModels.setLastupdatedon(ticketEntity.getLastupdatedon());
		ticketDataModelAndCustomerDataModels.setUserId(ticketEntity.getUserId());
		ticketDataModelAndCustomerDataModels.setCustomerId(ticketEntity.getCustomerId());
		ticketDataModelAndCustomerDataModels.setProductId(ticketEntity.getProductId());

		return ticketDataModelAndCustomerDataModels;
	}

	/**
	 * service call to get tickets based on characters no other service dependency
	 */

	public Page<CustomerEntity> getAllCityBasedOnCharacter(String city)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "city"));
		Page<CustomerEntity> page = iCustomerRepository.findByCity(pageable, city);
		return page;
	}

	public TicketDataModel updateWaranty(String ticketId, TicketDataModel ticketDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		TicketEntity ticketEntity = iTicketRepository.findByTicketId(ticketId);
		if (ticketEntity != null) {
			ticketEntity.setWarranty(ticketDataModel.getWarranty());
			iTicketRepository.save(ticketEntity);
			return ticketDataModel;
		} else {
			throw new ResourceNotFoundException("TicketId is not present.");
		}
	}

}