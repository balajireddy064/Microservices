package com.ayusha.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ayusha.login.commons.Response;
import com.ayusha.login.entity.ConfirmationToken;
import com.ayusha.login.entity.UserLogin;
import com.ayusha.login.repositry.ConfirmationTokenRepository;
import com.ayusha.login.repositry.UserLoginRepository;
import com.ayusha.login.service.EmailSenderService;
import com.ayusha.login.service.IUserLoginService;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;

@RestController
@CrossOrigin
public class UserLoginController {
	/** userRepositry **/
	@Autowired
	private UserLoginRepository userRepository;
	/** confirmationTokenRepository **/
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	/** emailSenderService **/
	@Autowired
	private EmailSenderService emailSenderService;
	/** userService **/
	@Autowired
	private IUserLoginService userService;
	/**
	 * password-encoding-with-spring-security/
	 */
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	/**
	 * @method displayLogin
	 * @param modelAndView
	 * @param user
	 * @return view page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(ModelAndView modelAndView, UserLogin user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * @method loginByWeb
	 * @param user
	 * @return response
	 */
	@ResponseBody
	@RequestMapping(value = "/loginAyushya", headers = { "content-type=application/json" }, method = RequestMethod.POST)
	public Response loginByWeb(@RequestBody UserLogin user)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		System.out.println("controller method ");
		return userService.loginByUser(user);
	}

	/**
	 * @method emailLinkSendAccount
	 * @param user
	 * @return response
	 */
	@RequestMapping(value = "/emailSend", method = RequestMethod.POST)
	public Response emailLinkSendAccount(@RequestBody UserLogin user) {
		Response response = userService.emailSendToAcount(user);
		return response;
	}

	/**
	 * @method validateResetToken
	 * @param user
	 * @param confirmationToken
	 * @return update password
	 */
	@RequestMapping(value = "/confirm-reset", method = { RequestMethod.GET, RequestMethod.POST })
	public Response validateResetToken(ModelAndView modelAndView, @RequestBody UserLogin user,
			@RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		Response response = new Response();
		if (token != null) {
			return userService.updatepassword(user);

		} else {
			response.getMessage();
			response.setMessage("not found");
			return response;
		}

	}

	/**
	 * @method loginByAndr
	 * @param user
	 * @return response
	 */
	@RequestMapping(value = "/loginByAndr", method = RequestMethod.POST)
	public Response loginByAndr(@RequestBody UserLogin user) {
		System.out.println("login demo");
		System.out.println("this method");
		Response response = userService.loginByAndr(user);
		return response;
	}

}
