package com.ayusha.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ayusha.login.commons.CommonUtils;
import com.ayusha.login.commons.Response;
import com.ayusha.login.commons.StatusCode;
import com.ayusha.login.entity.ConfirmationToken;
import com.ayusha.login.entity.UserLogin;
import com.ayusha.login.repositry.ConfirmationTokenRepository;
import com.ayusha.login.repositry.UserLoginRepository;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.user.data.models.UserDataModel;

@Service
public class UserLoginService implements IUserLoginService {
	/** userRepositry **/
	@Autowired
	private UserLoginRepository userRepositry;
	/** emailSenderService **/
	@Autowired
	private EmailSenderService emailSenderService;
	/** password security **/
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	/** confirmationTokenRepository **/
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	/** restTemplate **/
	@Autowired
	RestTemplate restTemplate;

	/**
	 * @method emailSendTOAcount
	 * @param user
	 * @return reponse
	 */
	public Response emailSendToAcount(UserLogin user) {
		Response response = CommonUtils.getResponseObject("");
		UserLogin existingUser = userRepositry.findByEmailIdIgnoreCase(user.getEmailId());
		UserDataModel userDataModel = restTemplate.getForObject(
				"http://service-user:8095/users/user/userlogin?email=" + user.getEmailId(), UserDataModel.class);
		ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
		confirmationTokenRepository.save(confirmationToken);

		if (userDataModel != null) {

			// create the email
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(userDataModel.getEmail());

			mailMessage.setSubject("Complete Password Reset!");
			mailMessage.setFrom("balajireddy064@gmail.com");
			mailMessage.setText("To complete the password reset process, please click here: "
					+ "http://134.209.147.111:8063/confirm-reset?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			response.setStatus(StatusCode.SUCCESS.name());
			response.setMessage("email send to your account");
			return response;
		} else {
			response.setErrors(StatusCode.ERROR.name());
			response.setMessage("email dose not exist");
			return response;
		}

	}

	/**
	 * @method update password
	 * @param UserLogin
	 * @return response
	 */
	@Override
	public Response updatepassword(UserLogin user) {
		Response response = CommonUtils.getResponseObject("");

		UserDataModel userDataModel = new UserDataModel();
		userDataModel.setPassword(encoder.encode(user.getPassword()));

		UserDataModel userDataModel1 = restTemplate.postForObject(
				"http:134.209.147.111//service-user/update?email=" + user.getEmailId(), userDataModel,
				UserDataModel.class);
		response.setMessage("password is update");
		return response;
	}

	/**
	 * @method loginByUser
	 * @return response
	 */
	// @SuppressWarnings("unused")
	public Response loginByUser(UserLogin user)
			throws DataPersistenceOperationException, InvalidServiceRequestException, ResourceNotFoundException {
		// Response response = CommonUtils.getResponseObject("");
		Response response = new Response();

		UserDataModel userDataModel = restTemplate.getForObject(
				"http://service-user:8095/users/user/userlogin?email=" + user.getEmailId(), UserDataModel.class);

		if (userDataModel != null) {
			if (user.getEmailId().equals(userDataModel.getEmail())
					&& encoder.matches(user.getPassword(), userDataModel.getPassword())) {

				response.setStatus(StatusCode.SUCCESS.name());
				response.setFirstName(userDataModel.getFirstName());
				response.setUserId(userDataModel.getUserId());
				response.setRole(userDataModel.getRole());
				return response;
			} else {
				throw new ResourceNotFoundException("password invalid");

			}
		} else {
			throw new ResourceNotFoundException("email dose not Exist!");
		}

	}

	/**
	 * @method UserLogin
	 * @return response
	 * @param UserLo
	 */

	public Response loginByAndr(UserLogin user) {
		Response response = CommonUtils.getResponseObject("");
		UserDataModel userDataModel = restTemplate.getForObject(
				"http:134.209.147.111//service-user/userlogin?email=" + user.getEmailId(), UserDataModel.class);

		if (user.getEmailId().equals(userDataModel.getEmail())
				&& encoder.matches(user.getPassword(), userDataModel.getPassword())) {
			response.setStatus(userDataModel.getFirstName());
			response.setMessage("well come to login " + userDataModel.getFirstName());
			return response;
		} else {
			response.setErrors(StatusCode.ERROR.name());
			response.setMessage("password  or email invalid");
			return response;
		}
	}

}
