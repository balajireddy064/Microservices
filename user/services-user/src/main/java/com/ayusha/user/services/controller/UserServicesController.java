package com.ayusha.user.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.NoContentException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.ayusha.user.commons.Response;
import com.ayusha.user.data.models.LocationsUsersDataModel;
import com.ayusha.user.data.models.UserDataModel;
import com.ayusha.user.service.IUserService;
import com.ayusha.user.services.entity.ConfirmationToken;
import com.ayusha.user.services.entity.UserEntity;
import com.ayusha.user.services.entity.UserLocationEntity;
import com.ayusha.user.services.repository.ConfirmationTokenRepository;
import com.ayusha.user.services.repository.IUserLocationRepository;
import com.ayusha.user.services.repository.IUserRepository;
import com.ayusha.user.specification.UserSpecification;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 01-Aug-2019 User Service Methods
 */
@RestController
@CrossOrigin
public class UserServicesController extends ServiceRequestProcessor {

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(UserServicesController.class);

	/** iUserService **/
	@Autowired
	private IUserService iUserService;

	/** iUserRepositry **/
	@Autowired
	private IUserRepository iUserRepositry;

	/** iUserLocationRepository **/
	@Autowired
	private IUserLocationRepository iUserLocationRepository;

	/** confirmationTokenRepository **/
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	/** iUserRepository **/
	@Autowired
	private IUserRepository iUserRepository;

	/** password encoder **/
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	/**
	 * user registration
	 */
	@RequestMapping(value = "/users/user/add", method = RequestMethod.POST)
	public Response addUser(@RequestBody UserDataModel userDatModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		Response response = new Response();
		LOG.info("entered user creation - start");

		// check if email exist or not

		boolean resultCust = checkEmailExist(userDatModel.getEmail());

		if (resultCust == false) {
			iUserService.save(userDatModel);
			LOG.info("successfully completed user creation operation ");
			response.setMessage("Employee registered succesfully");
			return response;
		} else {
			throw new InvalidServiceRequestException("Email address already exists!");
		}
	}

	// check if user exists for email
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
	 * get all users
	 */
	@RequestMapping(value = "/users/user/getAll", method = RequestMethod.GET)
	public Page<UserDataModel> findAllUsers(Pageable pageable, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered get all users - start");
		return iUserService.getAllUsers(pageable);
	}

	/**
	 * find user by user Id
	 */
	@RequestMapping(value = "/users/user/userId", method = RequestMethod.GET)
	public String findUserByUserId(@RequestParam("userId") String userId, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {
		LOG.info("entered user by user id operation - start");
		return JSONConverter.convertPOJOToString(iUserService.getUserByUserId(userId));
	}

	/**
	 * find user serving location
	 */
	@RequestMapping(value = "/users/user/locations", method = RequestMethod.GET)
	public LocationsUsersDataModel findUsersServingLocations(@RequestParam("locationid") String locationid,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered user location finding - start " + locationid);
		LocationsUsersDataModel uldm = iUserService.getUsersServingLocation(locationid);
		return iUserService.getUsersServingLocation(locationid);
	}

	/**
	 * validate reset token
	 */
	@RequestMapping(value = "/confirm-reset", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		// check if token is present or not
		if (token != null) {
			UserEntity user = iUserRepository.findByEmailIgnoreCase(token.getUserEntity().getEmail());
			iUserRepository.save(user);
			modelAndView.addObject("user", user);
			modelAndView.addObject("email", user.getEmail());
			modelAndView.setViewName("resetPassword");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

	/**
	 * reset user password
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public ModelAndView resetUserPassword(ModelAndView modelAndView, UserEntity user) {

		if (user.getEmail() != null) {
			UserEntity tokenUser = iUserRepository.findByEmailIgnoreCase(user.getEmail());
			tokenUser.setPassword(encoder.encode(user.getPassword()));
			iUserRepository.save(tokenUser);
			modelAndView.addObject("message",
					"Password successfully reset. You can now login with the new credentials.");
			modelAndView.setViewName("successResetPassword");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}

	/**
	 * user login
	 */
	@RequestMapping(value = "users/user/userlogin", method = RequestMethod.GET)
	public UserDataModel login(@RequestParam("email") String email) throws ResourceNotFoundException, Exception {
		return iUserService.login(email);
	}

	/**
	 * reset password
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public UserDataModel resetPassword(@RequestParam("email") String email, @RequestBody UserDataModel userDataModel) {
		return iUserService.resetPassword(email, userDataModel);
	}

	/**
	 * user login
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginByAndr(@RequestBody String body, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException, NoContentException {
		UserDataModel userDataModel = null;
		userDataModel = (UserDataModel) JSONConverter.convertStringToPOJO(body, UserDataModel.class);
		String response = JSONConverter.convertPOJOToString(iUserService.login(userDataModel));

		return response;
	}

	/**
	 * get all user locations
	 */
	@RequestMapping(value = "/users/user/getAllUserLocation", method = RequestMethod.GET)
	public List<UserLocationEntity> getAllUserLocation() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, NoContentException, Exception {
		return iUserService.getAllUserLocation();
	}

	/**
	 * search user based on firstName, lastName, Role
	 */
	@RequestMapping(value = "/userSearch", method = RequestMethod.GET)
	public Page<UserDataModel> searchBy(@RequestParam("userSearch") String userSearch) {
		// call to user specification class
		UserSpecification userSpecification = new UserSpecification(userSearch);
		return iUserService.searchDetails(userSpecification);

	}

	/**
	 * get all users
	 */
	@RequestMapping(value = "/users/user/getAllUsers", method = RequestMethod.GET)
	public String findAllUserList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		LOG.info("entered get all users finding - start");
		return iUserService.getAllUsersList();
	}

	/**
	 * send email to an account
	 */
	@RequestMapping(value = "/emailSend", method = RequestMethod.POST)
	public Response emailLinkSendAccount(@RequestBody UserEntity user) {
		Response response = iUserService.emailSendToAcount(user);
		return response;
	}

	/**
	 * confirm reset password
	 */
	@RequestMapping(value = "/confirm-reset-reset", method = { RequestMethod.GET, RequestMethod.POST })
	public Response resetUserPassword(@RequestParam("token") String confirmationToken, @RequestBody UserEntity user) {
		Response response = new Response();
		LOG.info("user email::::" + user.getEmail());
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			if (user.getEmail() != null) {
				UserEntity tokenUser = iUserRepository.findUserByEmailId(user.getEmail());
				tokenUser.setPassword(encoder.encode(user.getPassword()));
				iUserRepository.save(tokenUser);
				response.setMessage("password updated");
				return response;
			} else {
				response.setMessage("password is not updated ");
				return response;
			}
		} else {
			response.setMessage("token is not present");
			return response;
		}

	}

	/**
	 * update user
	 */
	@PutMapping("users/user/updateUser")
	public UserDataModel updatePartially(@RequestBody UserDataModel userDataModel,
			@RequestParam("userId") String userId) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		return iUserService.updatePartially(userDataModel, userId);
	}

	/**
	 * DemoGet ALl
	 */
	@RequestMapping(value = "/users/user/getAllDemo", method = RequestMethod.GET)
	public List<UserDataModel> getAllUsersDemo() throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		return iUserService.getAllUsersDemo();
	}
}
