package com.ayusha.user.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.NoContentException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.user.commons.CommonUtils;
import com.ayusha.user.commons.Response;
import com.ayusha.user.commons.StatusCode;
import com.ayusha.user.data.models.LocationsUsersDataModel;
import com.ayusha.user.data.models.UserDataModel;
import com.ayusha.user.data.models.UserEducationDetailsDataModels;
import com.ayusha.user.data.models.UserLocationsDataModel;
import com.ayusha.user.data.models.UserLoginDataModel;
import com.ayusha.user.services.entity.ConfirmationToken;
import com.ayusha.user.services.entity.UserEducationDetailsEntity;
import com.ayusha.user.services.entity.UserEntity;
import com.ayusha.user.services.entity.UserHistryEntity;
import com.ayusha.user.services.entity.UserLocationEntity;
import com.ayusha.user.services.repository.ConfirmationTokenRepository;
import com.ayusha.user.services.repository.IUserLocationRepository;
import com.ayusha.user.services.repository.IUserRepository;
import com.ayusha.user.services.repository.UserEducationDetailsRepositry;
import com.ayusha.user.services.repository.UserHistryRepositry;
import com.ayusha.user.specification.UserSpecification;
import com.ayusha.user.user.validations.UserDataValidation;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * 
 * @author Finch Date : 01-Aug-2019 User Service Methods
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
 *         5. Sorting: a. soring based on logged date,status,servicetype (ASC |
 *         DSC)
 */
@Service
public class UserService implements IUserService {

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(UserService.class);

	/** repository **/
	@Autowired
	private IUserRepository iUserRepository;

	/** userHistryRepositry **/
	@Autowired
	private UserHistryRepositry iUserHistryRepositry;

	/** IUserLocationRepository **/
	@Autowired
	private IUserLocationRepository iUserLocationRepository;

	/** emailSenderService **/
	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private UserEducationDetailsRepositry userEducationDetailsRepositry;

	/** confirmationTokenRepository **/
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	/** Password encoder **/
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	/**
	 * default constructor
	 */
	public UserService() {
		LOG.info("User Service Constructor");
	}

	// new date object
	Date myDate = new Date();

	SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy");
	SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

	// Format date to Strings
	String mdy = mdyFormat.format(myDate);
	String dmy = dmyFormat.format(myDate);

	/**
	 * service call to create user, no other service dependency
	 */
	public Response save(UserDataModel userDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ParseException {
		Response response = new Response();

		UserDataValidation userDataValidation = new UserDataValidation();
		userDataValidation.validate(userDataModel);
		userDataValidation.validationEducation(userDataModel);

		UserEntity userEntity = null;
		DateFormat dateFormat = new SimpleDateFormat("ddMMYY-");

		List<UserEducationDetailsDataModels> lstuserEducationDetailsDataModels = new ArrayList<UserEducationDetailsDataModels>();
		UserEducationDetailsDataModels userEducationDetailsDataModels = null;
		UserEducationDetailsEntity userEducationDetailsEntity = null;

		List<UserEducationDetailsDataModels> lstClientDetails = userDataModel.getUserEducationDetailsDataModels();

		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/YY-HH:mm:ss");
		Date date = new Date();

		UserLocationEntity userLocationEntity = new UserLocationEntity();

		userEntity = new UserEntity();

		// copy the properties from model to entity
//		BeanUtils.copyProperties(userDataModel, userEntity);

		userEntity.setAboutMe(userDataModel.getAboutMe());
		userEntity.setAddress(userDataModel.getAddress());
		userEntity.setAge(userDataModel.getAge());
		userEntity.setCity(userDataModel.getCity());

		// userDataModel.setCreatedOn(userEntity.getCreatedOn());
		userEntity.setDateOfBirth(userDataModel.getDateOfBirth());
		userEntity.setDateOfJoining(userDataModel.getDateOfJoining());
		userEntity.setEmail(userDataModel.getEmail());
		userEntity.setExpertiseLevel(userDataModel.getExpertiseLevel());
		userEntity.setFirstName(userDataModel.getFirstName());
		userEntity.setLastName(userDataModel.getLastName());
		userEntity.setGender(userDataModel.getGender());
		// userDataModel.setId(userEntity.getId());

		userEntity.setMiddleName(userDataModel.getMiddleName());
		userEntity.setPhoneNumber(userDataModel.getPhoneNumber());
		userEntity.setPinCode(userDataModel.getPinCode());
		userEntity.setRole(userDataModel.getRole());
		userEntity.setSalary(userDataModel.getSalary());
		userEntity.setSkills(userDataModel.getSkills());
		userEntity.setState(userDataModel.getState());
//		userDataModel.setUserId(userEntity.getUserId());

		System.out.println("================= " + userEntity.getAddress());

		userEntity.setCreatedOn(dateFormat1.format(date));

		// generate userId based on first name and last name
		userEntity.setUserId(userDataModel.getFirstName().toLowerCase() + userDataModel.getLastName().toLowerCase());

		userLocationEntity.setUserId(userEntity.getUserId());
		userLocationEntity.setLocationid(userDataModel.getPinCode());
		userLocationEntity.setServing("Y");
		userLocationEntity.setRole(userEntity.getRole());
		userLocationEntity.setEffectiveDate(dateFormat1.format(date));
		iUserLocationRepository.save(userLocationEntity);
		iUserRepository.save(userEntity);

		ConfirmationToken confirmationToken = new ConfirmationToken(userEntity);
		confirmationTokenRepository.save(confirmationToken);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userDataModel.getEmail());
		mailMessage.setSubject("Complete Your Password Reset for Ayushya!");
		mailMessage.setFrom("balajireddy064@gmail.com");
		mailMessage.setText("email text reset link");

		mailMessage.setText("To complete the password reset process, please click here: "
				+ "http://134.209.147.111:8095/confirm-reset?token=" + confirmationToken.getConfirmationToken());

		emailSenderService.sendEmail(mailMessage);
		response.setMessage("User registered succesfully");
		int size = 0;
		size = lstClientDetails.size();

		// loop over education details
		for (int i = 0; i < size; i++) {
			userEducationDetailsDataModels = new UserEducationDetailsDataModels();
			UserEducationDetailsEntity userEducationDetailsEntity2 = new UserEducationDetailsEntity();
			userEducationDetailsDataModels = lstClientDetails.get(i);
			userEducationDetailsEntity = new UserEducationDetailsEntity();
			if (userDataModel.getUserEducationDetailsDataModels() != null) {

				lstuserEducationDetailsDataModels = userDataModel.getUserEducationDetailsDataModels();
				Date passout = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
						.parse(userEducationDetailsDataModels.getPassOut());

				userEducationDetailsEntity2.setPassOut(String.valueOf(new SimpleDateFormat("yyyy").format(passout)));

				userEducationDetailsEntity2.setQualification(userEducationDetailsDataModels.getQualification());
				userEducationDetailsEntity2.setUserId(userEntity.getUserId());
			}
			userEducationDetailsRepositry.save(userEducationDetailsEntity2);
		}
		return response;
	}

	/**
	 * service call to save images, no other service dependency
	 */

	public Response saveImages(MultipartFile file, String userId)
			throws DataPersistenceOperationException, InvalidServiceRequestException, IOException {
		Response response = new Response();

		UserEntity userEntity = new UserEntity();
		byte[] byteArr = file.getBytes();

		InputStream inputStream = new ByteArrayInputStream(byteArr);

		userEntity.setImageUpload(byteArr);
		response.setMessage("image uploaded sucessfully...........");
		return response;
	}

	/**
	 * populate user details from entity to model
	 */
	private void populateUserEntity(UserEntity userEntity, UserDataModel userDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		userDataModel.setAboutMe(userEntity.getAboutMe());
		userDataModel.setAddress(userEntity.getAddress());
		userDataModel.setAge(userEntity.getAge());
		userDataModel.setCity(userEntity.getCity());

		userDataModel.setCreatedOn(userEntity.getCreatedOn());
		userDataModel.setDateOfBirth(userEntity.getDateOfBirth());
		userDataModel.setDateOfJoining(userEntity.getDateOfJoining());
		userDataModel.setEmail(userEntity.getEmail());
		userDataModel.setExpertiseLevel(userEntity.getExpertiseLevel());
		userDataModel.setFirstName(userEntity.getFirstName());
		userDataModel.setLastName(userEntity.getLastName());
		userDataModel.setGender(userEntity.getGender());
		userDataModel.setId(userEntity.getId());

		userDataModel.setMiddleName(userEntity.getMiddleName());
		userDataModel.setPhoneNumber(userEntity.getPhoneNumber());
		userDataModel.setPinCode(userEntity.getPinCode());
		userDataModel.setRole(userEntity.getRole());
		userDataModel.setSalary(userEntity.getSalary());
		userDataModel.setSkills(userEntity.getSkills());
		userDataModel.setState(userEntity.getState());
		userDataModel.setUserId(userEntity.getUserId());

	}

	/**
	 * populate user data from entity to model
	 */
	private void populateUserDataModel(UserEntity userEntity, UserDataModel userDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException {
		LOG.info(" inside populate user data model operation" + userEntity + "::::" + userDataModel);
		userDataModel.setAddress(userEntity.getAddress());
		userDataModel.setCity(userEntity.getCity());
		userDataModel.setState(userEntity.getState());

		userDataModel.setPinCode(userEntity.getPinCode());

		userDataModel.setEmail(userEntity.getEmail());
		userDataModel.setPhoneNumber(userEntity.getPhoneNumber());
		userDataModel.setFirstName(userEntity.getFirstName());
		userDataModel.setLastName(userEntity.getLastName());
		userDataModel.setMiddleName(userEntity.getMiddleName());
		userDataModel.setUserId(userEntity.getUserId());
		LOG.info("completed populate user data model operation");
	}

	/**
	 * service call to get user by user Id, no other service dependency
	 */

	public UserDataModel getUserByUserId(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		List<UserEducationDetailsDataModels> lstEducationDetailsDataModels = new ArrayList<UserEducationDetailsDataModels>();

		List<UserEducationDetailsEntity> lstBusinessClientDataModels = new ArrayList<>();

		UserDataModel userDataModel = new UserDataModel();
		UserEducationDetailsEntity userEducationDetailsEntity = null;

		List arr = new ArrayList<>();
		UserEntity userEntity = iUserRepository.findByUserId(userId);

		List<UserEducationDetailsEntity> lstClientContectEntities = userEducationDetailsRepositry.findByUserId(userId);

// check if user data is null
		if (userEntity == null) {
			throw new ResourceNotFoundException("userId is not present.");
		}
		UserEducationDetailsDataModels userEducationDetailsDataModels = null;
		userDataModel.setAboutMe(userEntity.getAboutMe());
		userDataModel.setAddress(userEntity.getAddress());
		userDataModel.setAge(userEntity.getAge());
		userDataModel.setCity(userEntity.getCity());
		userDataModel.setCreatedOn(userEntity.getCreatedOn());
		userDataModel.setDateOfBirth(userEntity.getDateOfBirth());
		userDataModel.setDateOfJoining(userEntity.getDateOfJoining());
		userDataModel.setEmail(userEntity.getEmail());
		userDataModel.setExpertiseLevel(userEntity.getExpertiseLevel());
		userDataModel.setFirstName(userEntity.getFirstName());
		userDataModel.setLastName(userEntity.getLastName());
		userDataModel.setGender(userEntity.getGender());
		userDataModel.setId(userEntity.getId());
		userDataModel.setMiddleName(userEntity.getMiddleName());
		userDataModel.setPhoneNumber(userEntity.getPhoneNumber());
		userDataModel.setPinCode(userEntity.getPinCode());
		userDataModel.setRole(userEntity.getRole());
		userDataModel.setSalary(userEntity.getSalary());
		userDataModel.setSkills(userEntity.getSkills());
		userDataModel.setState(userEntity.getState());
		userDataModel.setUserId(userEntity.getUserId());

		int size1 = 0;
		size1 = lstClientContectEntities.size();
		for (int index1 = 0; index1 < size1; index1++) {
			userEducationDetailsEntity = new UserEducationDetailsEntity();
			userEducationDetailsDataModels = new UserEducationDetailsDataModels();
			userEducationDetailsEntity = lstClientContectEntities.get(index1);

			userEducationDetailsDataModels.setQualification(userEducationDetailsEntity.getQualification());
			userEducationDetailsDataModels.setUserId(userEducationDetailsEntity.getUserId());
			userEducationDetailsDataModels.setPassOut(userEducationDetailsEntity.getPassOut());
			lstEducationDetailsDataModels.add(userEducationDetailsDataModels);

			userDataModel.setUserEducationDetailsDataModels(lstEducationDetailsDataModels);

		}
		return userDataModel;

	}

	/**
	 * service call to fetch user locations, no other service dependency
	 */
	public UserLocationsDataModel getLocations(String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		UserLocationsDataModel userLocationsDataModel = new UserLocationsDataModel();
		List<UserLocationEntity> lstUserLocations = iUserLocationRepository.findLocationsByUserId(userId, "Y");
		UserLocationEntity userLocationEntity = null;

		List<String> lstLocations = new ArrayList();

		int size = 0;
		size = lstUserLocations.size();

		for (int index = 0; index < size; index++) {
			userLocationEntity = lstUserLocations.get(index);
			lstLocations.add(userLocationEntity.getUserId());

		}
		userLocationsDataModel.setLstLocations(lstLocations);
		return userLocationsDataModel;
	}

	/**
	 * service call to get user serving location based on location id, no other
	 * service dependency
	 */
	public LocationsUsersDataModel getUsersServingLocation(String locationid) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		LocationsUsersDataModel locationsUsersDataModel = new LocationsUsersDataModel();
		LOG.info(" location id is:::::: " + locationid);
		List<UserLocationEntity> lstUserLocations = iUserLocationRepository.findUsersByLocations(locationid, "Y");

		UserLocationEntity userLocationEntity = null;
		List<String> lstLocations = new ArrayList();

		int size = 0;
		if (lstUserLocations != null) {
			size = lstUserLocations.size();

		}
		for (int index = 0; index < size; index++) {

			userLocationEntity = lstUserLocations.get(index);
			lstLocations.add(userLocationEntity.getUserId());
		}
		LOG.info(" user location data model:::: " + lstUserLocations + ":" + locationsUsersDataModel);
		locationsUsersDataModel.setLstUsers(lstLocations);

		if (userLocationEntity != null) {
			locationsUsersDataModel.setLocationId(userLocationEntity.getLocationId());
			locationsUsersDataModel.setRole(userLocationEntity.getRole());
		}
		return locationsUsersDataModel;
	}

	/**
	 * service call to update password based on email id, no other service
	 * dependency
	 */
	public UserDataModel updatepassword(UserDataModel userDataModel) {

		UserEntity existingUser = iUserRepository.findByEmail(userDataModel.getEmail());
		// check if email exist or not
		if (existingUser != null) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(existingUser.getEmail());
			mailMessage.setSubject("Complete Password Reset for Ayushya!");
			mailMessage.setFrom("balajireddy064@gmail.com");
			mailMessage.setText(
					"email text reset linhttps://github.com/avengers-code/Ayushya-phase-1-services-part-2.gitk");

			emailSenderService.sendEmail(mailMessage);

			existingUser.setPassword(encoder.encode(userDataModel.getPassword()));
			LOG.info("password reset successfull");
			iUserRepository.save(existingUser);

			return userDataModel;
		} else {
			return userDataModel;
		}
	}

	/**
	 * service call to loginService, no other service dependency
	 */
	public UserDataModel login(String email) throws ResourceNotFoundException, Exception {
		UserEntity userEntity = iUserRepository.findByEmail(email);
		UserDataModel userDataModel = new UserDataModel();
		if (userEntity != null) {

			BeanUtils.copyProperties(userEntity, userDataModel);
			return userDataModel;
		} else {
			throw new ResourceNotFoundException("Data is not present");
		}
	}

	/**
	 * service call to reset password based on email id, no other service dependency
	 */
	public UserDataModel resetPassword(String email, UserDataModel userDataModel) {
		UserEntity userEntity = iUserRepository.findByEmail(email);

		userEntity.setPassword(userDataModel.getPassword());
		iUserRepository.save(userEntity);
		return userDataModel;
	}

	/**
	 * check email exist or not
	 */
	public boolean checkEmailExist(String email) {
		UserEntity userEntity = null;
		userEntity = iUserRepository.findByEmail(email);
		if (userEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * service call to login, No other service dependency
	 */
	@Override
	public UserLoginDataModel login(UserDataModel body) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, NoContentException, Exception {

		UserLoginDataModel userLogin = new UserLoginDataModel();

		// date format
		DateFormat dateFormat = new SimpleDateFormat("ddMMYY-");
		DateFormat loggedDate = new SimpleDateFormat("ddMMYY-HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = new Date();

		UserDataModel userDataModel = new UserDataModel();
		BCryptPasswordEncoder decode = new BCryptPasswordEncoder();
		UserEntity emailId = iUserRepository.findUserByEmailId(body.getEmail());

		// check if email id is null
		if (emailId == null) {
			throw new ResourceNotFoundException("Invalid Email Id!");
		} else if (!decode.matches(body.getPassword(), emailId.getPassword())) {
			throw new ResourceNotFoundException("Invalid Password!");
		} else {
			String str = emailId.getFirstName();
			String output = str.substring(0, 1).toUpperCase() + str.substring(1);
			userLogin.setFirstName(output);
			userLogin.setUserId(emailId.getUserId());
			userLogin.setRole(emailId.getRole());
			iUserRepository.save(emailId);
		}
		return userLogin;
	}

	/**
	 * service call to get all user locations, no other service dependency
	 **/
	public List<UserLocationEntity> getAllUserLocation() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, NoContentException, Exception {

		List<UserLocationEntity> userLocationEntities = iUserLocationRepository.findAll();
		return userLocationEntities;
	}

	/**
	 * service call to serach users based on first name, last name or role no other
	 * service dependency
	 */
	@Override
	public Page<UserDataModel> searchDetails(UserSpecification userSpecification) {

		// pageable
		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);
		List<UserEntity> userEntities = iUserRepository.findAll(userSpecification);

		List<UserDataModel> lstuserDataModel = new ArrayList<UserDataModel>();

		UserDataModel userDataModel = null;

		UserEntity userEntity = null;
		int size = userEntities.size();

		for (int index = 0; index < size; index++) {

			List<UserEducationDetailsDataModels> userlst = new ArrayList<UserEducationDetailsDataModels>();
			userEntity = userEntities.get(index);
			userDataModel = new UserDataModel();

			userDataModel.setAboutMe(userEntity.getAboutMe());
			userDataModel.setAddress(userEntity.getAddress());
			userDataModel.setAge(userEntity.getAge());
			userDataModel.setCity(userEntity.getCity());
			userDataModel.setCreatedOn(userEntity.getCreatedOn());
			userDataModel.setDateOfBirth(userEntity.getDateOfBirth());
			userDataModel.setDateOfJoining(userEntity.getDateOfJoining());
			userDataModel.setEmail(userEntity.getEmail());
			userDataModel.setExpertiseLevel(userEntity.getExpertiseLevel());
			userDataModel.setFirstName(userEntity.getFirstName());
			userDataModel.setLastName(userEntity.getLastName());
			userDataModel.setGender(userEntity.getGender());
			userDataModel.setId(userEntity.getId());
			userDataModel.setMiddleName(userEntity.getMiddleName());
			userDataModel.setPhoneNumber(userEntity.getPhoneNumber());
			userDataModel.setPinCode(userEntity.getPinCode());
			userDataModel.setRole(userEntity.getRole());
			userDataModel.setSalary(userEntity.getSalary());
			userDataModel.setSkills(userEntity.getSkills());
			userDataModel.setState(userEntity.getState());
			userDataModel.setUserId(userEntity.getUserId());
			lstuserDataModel.add(userDataModel);

		}
		Page<UserDataModel> result = new PageImpl<UserDataModel>(lstuserDataModel);
		return result;
	}

	/**
	 * service call to get all users, no other service dependency
	 */
	@Override
	public String getAllUsersList() throws DataPersistenceOperationException, InvalidServiceRequestException,
			ResourceNotFoundException, NoContentException, Exception {
		String response = JSONConverter.convertPOJOToString(iUserRepository.findAll());
		return response;
	}

	/**
	 * send an email to account
	 */
	public Response emailSendToAcount(UserEntity user) {
		Response response = CommonUtils.getResponseObject("");
		UserEntity existingUser = iUserRepository.findByEmailIdIgnoreCase(user.getEmail());
		ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
		confirmationToken.setEmail(user.getEmail());
		confirmationTokenRepository.save(confirmationToken);

		// check if existing user is not null
		if (existingUser != null) {

			// create the email
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(existingUser.getEmail());

			mailMessage.setSubject("Complete Password Reset for Ayushya!");
			mailMessage.setFrom("balajireddy064@gmail.com");
			mailMessage.setText("To complete the password reset process, please click here: "
					+ "http://134.209.147.111:8062/confirm-reset?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			response.setStatus(StatusCode.SUCCESS.name());
			response.setMessage("email send to your account");
			return response;
		} else {
			response.setErrors(StatusCode.ERROR.name());
			response.setMessage("email dose not exist");
			// wrong password
			return response;
		}

	}

	/**
	 * service call to get all users, no other service dependency
	 */

	public Page<UserDataModel> getAllUsers(Pageable pageable) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		Pageable paging = PageRequest.of(0, Integer.MAX_VALUE);

		List<UserEntity> lstUserEntities = iUserRepository.findAllUser(pageable);

		// check if users are present
		if (lstUserEntities == null) {
			throw new ResourceNotFoundException("users are not present");
		}

		List array = new ArrayList<>();

		int size = 0;
		size = lstUserEntities.size();

		for (int index = 0; index < size; index++) {
			List<UserEducationDetailsDataModels> lsClientDetailsDataModels = new ArrayList<UserEducationDetailsDataModels>();
			UserEntity userEntity = null;
			UserDataModel userDataModel = null;

			UserEducationDetailsEntity userEducationDetailsEntity = null;
			UserEducationDetailsDataModels userEducationDetailsDataModels = null;

			userEntity = new UserEntity();

			userEntity = lstUserEntities.get(index);
			userDataModel = new UserDataModel();
			// copy the properties from userEntity to userDataModel
			BeanUtils.copyProperties(userEntity, userDataModel);

			List<UserEducationDetailsEntity> lstClientContectEntities = userEducationDetailsRepositry
					.findByUserId(userEntity.getUserId());

			int size1 = 0;
			size1 = lstClientContectEntities.size();

			userDataModel.setUserEducationDetailsDataModels(lsClientDetailsDataModels);

			for (int index1 = 0; index1 < size1; index1++) {
				userEducationDetailsEntity = new UserEducationDetailsEntity();
				userEducationDetailsDataModels = new UserEducationDetailsDataModels();

				userEducationDetailsEntity = lstClientContectEntities.get(index1);
				BeanUtils.copyProperties(userEducationDetailsEntity, userEducationDetailsDataModels);
				lsClientDetailsDataModels.add(userEducationDetailsDataModels);
			}
			array.add(userDataModel);
		}

		Page<UserDataModel> result = new PageImpl<UserDataModel>(array);

		return result;

	}

	/**
	 * service call to partially update user based on user Id, no other service
	 * dependency
	 */
	public UserDataModel updatePartially(UserDataModel userDataModel, String UserId)
			throws ResourceNotFoundException, InvalidServiceRequestException {

		UserEntity userEntity = iUserRepository.findByUserId(UserId);
		UserLocationEntity userLocationEntity = iUserLocationRepository.findByUserId(UserId);

		// check if userId present or not
		if (userEntity == null) {
			throw new ResourceNotFoundException("userId is not present.");
		}

		// date format
		DateTimeFormatter doj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime local = LocalDateTime.now();

		UserDataValidation userDataValidation = new UserDataValidation();
		userDataValidation.validationUpdate(userDataModel);

		// during update old record get inserted to history table
		UserHistryEntity userHistryEntity = new UserHistryEntity();
		userHistryEntity.setUserId(userEntity.getUserId());
		userHistryEntity.setFirstName(userEntity.getFirstName());
		userHistryEntity.setLastName(userEntity.getLastName());
		userHistryEntity.setMiddleName(userEntity.getLastName());
		userHistryEntity.setAboutMe(userEntity.getAboutMe());
		userHistryEntity.setAddress(userEntity.getAddress());
		userHistryEntity.setAge(userEntity.getAge());
		userHistryEntity.setCity(userEntity.getCity());
		userHistryEntity.setState(userEntity.getState());
		userHistryEntity.setDateOfBirth(userDataModel.getDateOfBirth());
		userHistryEntity.setDateOfJoining(userDataModel.getDateOfJoining());
		userHistryEntity.setLastUpdatedOn(local.format(doj));
		userHistryEntity.setSkills(userEntity.getSkills());
		userHistryEntity.setSalary(userEntity.getSalary());
		userHistryEntity.setRole(userEntity.getRole());
		userHistryEntity.setExpertiseLevel(userEntity.getExpertiseLevel());
		userHistryEntity.setPhoneNumber(userEntity.getPhoneNumber());
		userHistryEntity.setPinCode(userEntity.getPinCode());
		userHistryEntity.setGennder(userEntity.getGender());
		iUserHistryRepositry.save(userHistryEntity);

		// inserts newly updated record to user table
		userEntity.setAddress(userDataModel.getAddress());
		userEntity.setCity(userDataModel.getCity());
		userEntity.setState(userDataModel.getState());
		userEntity.setPinCode(userDataModel.getPinCode());
		userEntity.setPhoneNumber(userDataModel.getPhoneNumber());
		userEntity.setDateOfJoining(userDataModel.getDateOfJoining());
		userEntity.setDateOfBirth(userDataModel.getDateOfBirth());
		userEntity.setSkills(userDataModel.getSkills());
		userEntity.setRole(userDataModel.getRole());
		userEntity.setSalary(userDataModel.getSalary());
		userEntity.setAboutMe(userDataModel.getAboutMe());
		userEntity.setGender(userDataModel.getGender());
		userEntity.setAge(userDataModel.getAge());
		userEntity.setExpertiseLevel(userDataModel.getExpertiseLevel());
		userLocationEntity.setLocationid(userDataModel.getPinCode());
		userLocationEntity.setRole(userDataModel.getRole());
		iUserLocationRepository.save(userLocationEntity);

		List<UserEducationDetailsEntity> lsClientContectEntities = userEducationDetailsRepositry.findByUserId(UserId);
		UserEducationDetailsEntity list;

		List<UserEducationDetailsDataModels> userEducation = userDataModel.getUserEducationDetailsDataModels();

		for (int i = 0; i < lsClientContectEntities.size(); i++) {
			list = userEducationDetailsRepositry.findIndividualClientId(lsClientContectEntities.get(i).getId());

			list.setUserId(UserId);
			list.setPassOut(userDataModel.getUserEducationDetailsDataModels().get(i).getPassOut());
			list.setQualification(userDataModel.getUserEducationDetailsDataModels().get(i).getQualification());
			userEducationDetailsRepositry.save(list);
		}
		return userDataModel;
	}

	/**
	 * get Demo
	 */
	public List<UserDataModel> getAllUsersDemo() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		List<UserEntity> lstUserEntities = iUserRepository.findAll();

// check if users are present
		if (lstUserEntities == null) {
			throw new ResourceNotFoundException("users are not present");
		}

		List array = new ArrayList<>();

		int size = 0;
		size = lstUserEntities.size();

		for (int index = 0; index < size; index++) {
			List<UserEducationDetailsDataModels> lsClientDetailsDataModels = new ArrayList<UserEducationDetailsDataModels>();
			UserEntity userEntity = null;
			UserDataModel userDataModel = null;

			UserEducationDetailsEntity userEducationDetailsEntity = null;
			UserEducationDetailsDataModels userEducationDetailsDataModels = null;

			userEntity = new UserEntity();

			userEntity = lstUserEntities.get(index);
			userDataModel = new UserDataModel();
			// copy the properties from userEntity to userDataModel
			BeanUtils.copyProperties(userEntity, userDataModel);

			List<UserEducationDetailsEntity> lstClientContectEntities = userEducationDetailsRepositry
					.findByUserId(userEntity.getUserId());

			int size1 = 0;
			size1 = lstClientContectEntities.size();

			userDataModel.setUserEducationDetailsDataModels(lsClientDetailsDataModels);

			for (int index1 = 0; index1 < size1; index1++) {
				userEducationDetailsEntity = new UserEducationDetailsEntity();
				userEducationDetailsDataModels = new UserEducationDetailsDataModels();

				userEducationDetailsEntity = lstClientContectEntities.get(index1);
				BeanUtils.copyProperties(userEducationDetailsEntity, userEducationDetailsDataModels);
				lsClientDetailsDataModels.add(userEducationDetailsDataModels);
			}
			array.add(userDataModel);
		}

		return array;

	}

}
