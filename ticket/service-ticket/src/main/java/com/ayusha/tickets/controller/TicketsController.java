package com.ayusha.tickets.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.products.data.models.ProductDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.assinment.TicketAssignDataHandler;
import com.ayusha.ticket.data.models.CustomerDataModel;
import com.ayusha.ticket.data.models.TicketAndCustomerDataModel;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.ticket.data.models.TicketDataModelUpdate;
import com.ayusha.ticket.services.constants.TicketServiceConstants;
import com.ayusha.tickets.entity.CustomerEntity;
import com.ayusha.tickets.entity.TicketEntity;
import com.ayusha.tickets.repository.ICustomerRepository;
import com.ayusha.tickets.repository.ITicketRepository;
import com.ayusha.tickets.service.CustomerService;
import com.ayusha.tickets.service.ITicketService;
import com.ayusha.tickets.service.TicketService;
import com.ayusha.tickets.specification.UserSpecification;
import com.ayusha.user.commons.Response;
import com.ayusha.user.data.models.LocationsUsersDataModel;
import com.ayusha.user.data.models.UserDataModel;
import com.ayusha.user.data.models.UserLocationsDataModel;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
import com.google.protobuf.TextFormat.ParseException;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 01-Aug-2019
 */
@RestController
@CrossOrigin
@RequestMapping("/tickets/ticket")
public class TicketsController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(TicketsController.class);

	/** ticket Services **/
	@Autowired
	private TicketService ticketService = null;

	@Autowired
	private ITicketRepository iticketRep;
	/** customerService **/

	@Autowired
	private ICustomerRepository ICustomerRepository;
	@Autowired
	private CustomerService customerService = null;

	@Autowired
	private ITicketService iTicketService;

	/** ping **/
	@RequestMapping("/test")
	public String ping() {
		LOG.info("Successfully triggered Ping operation");
		return "Successfully deployed  Services";
	}

	/**
	 * add ticket
	 */

	@PostMapping("/add")
	public Response add(@RequestBody TicketDataModel ticketDataModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		// response body
		Response response = new Response();
		LOG.info("Successfully triggered add ticket operation " + ticketDataModel);

		TicketDataModel ticketDataModelResponse = iTicketService.add(ticketDataModel);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime now = LocalDateTime.now();

		RestTemplate restTemplate = new RestTemplate();
		UserDataModel userDataModel = new UserDataModel();
		String customerDataModel = ticketDataModel.getCustomerDataModel().getPinCode();

		// find pin code from user location service
		LocationsUsersDataModel userLocationsUsersDataModel = restTemplate.getForObject(
				TicketServiceConstants.GET_USER_PINCODE + customerDataModel, LocationsUsersDataModel.class);

		// check if pin code is present
		// assign job if pin code is present
		if (userLocationsUsersDataModel.getLocationId() != null) {
			boolean bol = false;
			try {
				bol = assignuserToTiket(userLocationsUsersDataModel, ticketDataModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (bol == false) {
				response.setTimestamp(dtf.format(now));
				response.setMessage("Ticket successfully created and Job is assigned");
				response.setStatus("200");
				return response;
			} else {
				response.setMessage("Ticket successfully created and Job is not  assigned");
				return response;
			}

		} else {
			response.setTimestamp(dtf.format(now));
			response.setMessage("Ticket sucesfully created but Job is not assigned");
			response.setStatus("200");
			return response;
		}

	}

	/**
	 * create bulk tickets and assign job
	 */
	public void addBulk(List<TicketDataModel> lstTicketDataModel) throws Exception {

		int iCount = 0;
		TicketDataModel ticketDataModel = null;
		if (lstTicketDataModel != null) {
			iCount = lstTicketDataModel.size();
		}

		for (int index = 0; index < iCount; index++) {
			ticketDataModel = lstTicketDataModel.get(index);

			// response body
			Response response = new Response();
			LOG.info("Successfully triggered add ticket operation " + ticketDataModel);

			TicketDataModel ticketDataModelResponse = iTicketService.add(ticketDataModel);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			LocalDateTime now = LocalDateTime.now();

			RestTemplate restTemplate = new RestTemplate();
			UserDataModel userDataModel = new UserDataModel();
			String customerDataModel = ticketDataModel.getCustomerDataModel().getPinCode();

			// find pin code from user location service
			LocationsUsersDataModel userLocationsUsersDataModel = restTemplate.getForObject(
					TicketServiceConstants.GET_USER_PINCODE + customerDataModel, LocationsUsersDataModel.class);

			LOG.info("Successfully triggered add ticket operation " + userLocationsUsersDataModel.getLocationId());

			try {
				// check if pin code is present
				// assign job if pin code is present
				if (userLocationsUsersDataModel.getLocationId() != null) {
					boolean bol = false;
					try {
						bol = assignuserToTiket(userLocationsUsersDataModel, ticketDataModel);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} catch (Exception e) {
				throw e;

			}
		}
	}

	/**
	 * assign job based on role
	 */
	private boolean assignuserToTiket(LocationsUsersDataModel locationsUsersDataModel,
			TicketDataModel ticketDataModelResponse) throws ParseException, ServicesDataException {

		int iUsersCount = 0;
		List<String> filteredUsersList = locationsUsersDataModel.getLstUsers();
		UserLocationsDataModel userLocationDataModel = null;
		LOG.info("Successfully triggered add ticket operation----------------------------- "
				+ locationsUsersDataModel.getRole());

		// check if user list is 0
		if (filteredUsersList != null) {
			iUsersCount = filteredUsersList.size();
		}

		for (int index = 0; index < iUsersCount; index++) {

			if (locationsUsersDataModel != null
					&& locationsUsersDataModel.getRole().equalsIgnoreCase("Service Engineer")) {
				TicketAssignDataHandler ticketAssignDataHandler = new TicketAssignDataHandler();
				ticketAssignDataHandler.assignTicket(ticketDataModelResponse);

				ticketDataModelResponse.setUserId(userLocationDataModel.getUserId());
				return false;
			}

		}
		return true;
	}

	/**
	 * update ticket based on ticket id
	 */

	@PutMapping("/updateBasedOnTicketId")
	public ResponseEntity<?> updateTicketBasedOnId(@RequestParam("ticketId") String ticketId,
			@RequestBody String requestBody, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)

			throws DataPersistenceOperationException, InvalidServiceRequestException, Exception {

		LOG.info("Successfully triggered update ticket operation " + requestBody);

		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);
		TicketDataModel ticketDataModel = (TicketDataModel) JSONConverter.convertStringToPOJO(requestBody,
				TicketDataModel.class);
		ResponseEntity<?> response = iTicketService.updateTicketBasedOnTicketId(ticketId, ticketDataModel);

		LOG.info("Successfully completed update ticket detailsoperation");
		return response;

	}

	/**
	 * update ticket status
	 */
	@PutMapping("/updatepartialByTicketId")
	public String updateTicketByTicketId(@RequestParam("ticketId") String ticketId, @RequestBody String requestBody,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {

		LOG.info("Successfully triggered update ticket operation");
		TicketDataModelUpdate ticketUpdateDataModel = null;
		String strRequestBody = preProcess(requestBody, httpServletRequest, httpServletResponse);

		TicketDataModel ticketDataModel = null;

		ticketUpdateDataModel = (TicketDataModelUpdate) JSONConverter.convertStringToPOJO(requestBody,
				TicketDataModelUpdate.class);

		LOG.info(" TICKET IS HERE << TICKET >>" + JSONConverter.convertPOJOToString(ticketUpdateDataModel));

		LOG.info("Successfully completed update ticket operation");
		strRequestBody = postProcess(requestBody, httpServletRequest, httpServletResponse);

		return JSONConverter.convertPOJOToString(iTicketService.updatePartlyBy(ticketId, ticketUpdateDataModel));
	}

	/**
	 *
	 * get AllCustomers
	 */
	@GetMapping("/get/customer")
	public String getCustomer(@RequestParam("customerId") String customerId) throws DataPersistenceOperationException,
			Exception, InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("Successfully GET TICKETS");
		CustomerDataModel customerEntity = customerService.getCustomerByCustomerCode(customerId);
		String serviceResponse = JSONConverter.convertPOJOToString(customerEntity);
		return serviceResponse;
	}

	/**
	 * get By ticketId
	 */
	@GetMapping("/getTicketDetails")
	public String getJobDetails(@RequestParam("ticketId") String ticketId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		TicketEntity ticketEntity = iTicketService.getTicketDetails(ticketId);
		TicketAndCustomerDataModel customerDetail = new TicketAndCustomerDataModel();
		List<TicketAndCustomerDataModel> ticketAndCustomerDataModel = new ArrayList<TicketAndCustomerDataModel>();
		ProductDataModel productDataModel = new ProductDataModel();

		CustomerDataModel customerEntity = customerService.getCustomerByCustomerCode(ticketEntity.getCustomerId());
		customerDetail.setCustomerDataModel(customerEntity);
		String serviceRes = JSONConverter.convertPOJOToString(ticketAndCustomerDataModel);
		return serviceRes;
	}

	/**
	 * get AllTickets
	 */
	@GetMapping("/getAllTickets")
	public Page<TicketDataModel> getAllTickets(Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		LOG.info("get all tickets");
		return iTicketService.getTickets(pageable);
	}

	/**
	 * get tickets by user id
	 */

	@GetMapping("/getByUserId")
	public List<TicketDataModel> getByUserId(@RequestParam("userId") String userId)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		return ticketService.getByUserId(userId);
	}

	/**
	 * get Tickets by city
	 */
	@RequestMapping(value = "/getAllTickets/city/{city_filter}", method = RequestMethod.GET)
	public Page<TicketAndCustomerDataModel> getCities(@PathVariable String city_filter, Pageable pageable,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {

		return iTicketService.getCities(city_filter, pageable);
	}

	/**
	 * bulk ticket upload
	 */
	@PostMapping("readFromExcel")
	public String readFromExcelWithKnownObject(@RequestParam("file") MultipartFile multipartFile)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		LocalDate today = LocalDate.now();

		List<TicketDataModel> lstTickets = new ArrayList();

		// get file that needs to be mapped into object
		if (multipartFile != null) {
			InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
			// get workbook and sheet
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			List<TicketDataModel> ticketList = new ArrayList<>();
			// iterate through rows
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				// skip heading row.
				if (currentRow.getRowNum() == 0) {

					continue;
				}
				// mapped to example object
				String tempValue = getCellValueAsString(currentRow.getCell(0));
				if (tempValue != null && tempValue.trim().length() > 0) {
					TicketDataModel ticketDataModel = new TicketDataModel();

					ticketDataModel.setCallType(getCellValueAsString(currentRow.getCell(0)));
					ticketDataModel.setDescription(getCellValueAsString(currentRow.getCell(1)));
					ticketDataModel.setSerialNumber(getCellValueAsString(currentRow.getCell(2)));
					ticketDataModel.setCategory(getCellValueAsString(currentRow.getCell(3)));
					ticketDataModel.setSubCategory(getCellValueAsString(currentRow.getCell(4)));
					ticketDataModel.setBrand(getCellValueAsString(currentRow.getCell(5)));
					ticketDataModel.setModel(getCellValueAsString(currentRow.getCell(6)));
					ticketDataModel.setDealerName(getCellValueAsString(currentRow.getCell(7)));
					ticketDataModel.setWarranty(getCellValueAsString(currentRow.getCell(8)));
					String date = dtf.format(now);
					ticketDataModel.setVisitDate(new Date());
					ticketDataModel.setVisitTime(dtf.format(now));
					CustomerDataModel customerDataModel = new CustomerDataModel();

					customerDataModel.setCustomerName(getCellValueAsString(currentRow.getCell(9)));
					customerDataModel.setAddress1(getCellValueAsString(currentRow.getCell(10)));
					customerDataModel.setAddress2(getCellValueAsString(currentRow.getCell(11)));
					customerDataModel.setCity(getCellValueAsString(currentRow.getCell(12)));
					customerDataModel.setState(getCellValueAsString(currentRow.getCell(13)));
					customerDataModel.setStreet(getCellValueAsString(currentRow.getCell(14)));
					customerDataModel.setPinCode(getCellValueAsString(currentRow.getCell(15)));
					customerDataModel.setContactNumber(getCellValueAsString(currentRow.getCell(16)));
					customerDataModel.setEmail(getCellValueAsString(currentRow.getCell(17)));
					customerDataModel.setAlternateContact(getCellValueAsString(currentRow.getCell(18)));

					ticketDataModel.setCustomerDataModel(customerDataModel);
					ticketList.add(ticketDataModel);

					lstTickets.add(ticketDataModel);

				}

			}

			addBulk(lstTickets);

		}
		return "ticket added sucessfully...............";
	}

	// read each cell value and map to db
	public static String getCellValueAsString(Cell cell) {
		String strCellValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCellValue = cell.toString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					Long longValue = value.longValue();
					strCellValue = new String(longValue.toString());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
				break;
			case Cell.CELL_TYPE_BLANK:
				strCellValue = null;
				break;
			}
		}
		return strCellValue;
	}

	/**
	 * get tickets by visit date
	 */
	@GetMapping("/getAllTickets/visitDate/{visitDate}")
	public Page<TicketDataModel> getTicketsBasedOnVisitDate(
			@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") @PathVariable("visitDate") Date visitDate,
			Pageable pageable) throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("Successfully triggered visitDate ticket operation ");

		return iTicketService.getByDate(visitDate, pageable);
	}

	/**
	 * get by ticket id
	 */
	@GetMapping("/getByTicketId")
	public String getByTicketIdAndCustomer(@RequestParam("ticketId") String ticketId)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException,
			Exception {
		LOG.info("get ticket by id");
		return JSONConverter.convertPOJOToString(iTicketService.getByTicketIdAndCustomer(ticketId));
	}

	/**
	 * get By City
	 */
	@GetMapping(value = "/city/{city_filter}")
	public Page<CustomerEntity> searchByCity(String city)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		return ticketService.getAllCityBasedOnCharacter(city);
	}

	@PutMapping("updateWarranty")
	public TicketDataModel updateWaranty(@RequestParam("ticketId") String ticketId,
			@RequestBody TicketDataModel ticketDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		return ticketService.updateWaranty(ticketId, ticketDataModel);
	}

}