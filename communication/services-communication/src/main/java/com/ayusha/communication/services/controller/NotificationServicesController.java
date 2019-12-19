package com.ayusha.communication.services.controller;

import javax.management.NotificationEmitter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayusha.communication.data.models.CommunicationModel;
import com.ayusha.communication.service.INotificationService;
import com.ayusha.communication.services.repository.INotificationRepository;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.login.commons.Response;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServiceRequestProcessor;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

/**
 * This is the service api interface. This controls the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 2019-05-03
 */
@RestController
@RequestMapping("/communication/notification")
public class NotificationServicesController extends ServiceRequestProcessor {

	public static final String ACCOUNT_SID = "AC776f26d56f8c79d0d6f5b527ee4918f7";
	public static final String AUTH_TOKEN = "f7ecb5d41f099ecb5f59605b12b69940";
	public static final String TWILIO_NUMBER = "+16176741431";

	/** Logger **/
	private static Logger LOG = LogManager.getLogger(NotificationServicesController.class);

	/** repository **/
	@Autowired
	private INotificationService iNotificationService;

	/**
	 * reposity
	 */
	@Autowired
	private INotificationRepository iNotificationRepository;

	/** send sms **/
	public void sendSMS() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		PhoneNumber to = new PhoneNumber("+91 94814 61543");
		PhoneNumber from = new PhoneNumber(TWILIO_NUMBER);

		MessageCreator creater = Message.creator(to, from, "Message from Spring Boot Application");
		creater.create();
	}

	/**
	 * @method getValidation
	 * @param jobCode
	 * @param otp
	 * @return communicationModel
	 */

	@RequestMapping(value = "validat", method = RequestMethod.GET)
	public String getValidation(@RequestParam("jobCode") String jobCode, @RequestParam("otp") int otp,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		CommunicationModel communicationModel = iNotificationService.validationOtp(jobCode, otp);
		return JSONConverter.convertPOJOToString(communicationModel);
	}

	/**
	 * @param sendOtp
	 * @param communicationModel
	 * @return communcationDataModel
	 * @throws Exception
	 */
//	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
//	public CommunicationModel sendOtp(@RequestBody CommunicationModel communicationModel) throws Exception {
//		boolean resultCust = iNotificationService.checkPhoneNumberExists(communicationModel.getPhone());
//		// System.out.println("in before if");
//		if (resultCust == false) {
//			return iNotificationService.save(communicationModel);
//
//		}
//		return null;
//	}

	/*
	 * @RequestMapping(value = "/sendOtp", method = RequestMethod.POST) public
	 * CommunicationModel sendOtp(@RequestBody CommunicationModel
	 * communicationModel) throws Exception { boolean resultCust =
	 * iNotificationService.checkPhoneNumberExists(communicationModel.getPhone());
	 * // System.out.println("in before if"); if (resultCust == false) { return
	 * iNotificationService.save(communicationModel);
	 * 
	 * } return null; }
	 */

	/**
	 * @method validationOtpDemo1
	 * @param jobCode
	 * @param otp
	 * @return validationOtp
	 */
	/*
	 * @RequestMapping(value = "validatedemo", method = RequestMethod.GET) public
	 * Response validationOtpDemo1(@RequestParam("jobCode") String
	 * jobCode, @RequestParam("otp") String otp) throws
	 * DataPersistenceOperationException, InvalidServiceRequestException,
	 * ResourceNotFoundException, Exception { return
	 * iNotificationService.validationOtp(jobCode, otp);
	 * 
	 * }
	 */

	/*
	 * usefull
	 */

	@RequestMapping(value = "/sendOtp", method = RequestMethod.POST)
	public String sendOtp(@RequestBody String body, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws DataPersistenceOperationException, Exception,
			InvalidServiceRequestException, ResourceNotFoundException {

		CommunicationModel communicationModel = (CommunicationModel) JSONConverter.convertStringToPOJO(body,
				CommunicationModel.class);
		CommunicationModel response = iNotificationService.sendOtp(communicationModel);
		return JSONConverter.convertPOJOToString(response);
	}

	/*
	 * usefull get validation
	 */
	@RequestMapping(value = "/otpValidation", method = RequestMethod.GET)
	public Response validation(@RequestParam("otp") String otp, @RequestParam("id") long id,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws DataPersistenceOperationException, Exception, InvalidServiceRequestException,
			ResourceNotFoundException {
		return iNotificationService.validationOtp(otp, id);
	}

}
