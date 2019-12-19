package com.ayusha.communication.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ayusha.communication.core.NotificationOperationException;
import com.ayusha.communication.core.NotificationSender;
import com.ayusha.communication.data.models.CommunicationModel;
import com.ayusha.communication.service.validations.ConstantPropertise;
import com.ayusha.communication.services.entity.CommunicationEntity;
import com.ayusha.communication.services.repository.INotificationRepository;
import com.ayusha.login.commons.CommonUtils;
import com.ayusha.login.commons.Response;
import com.ayusha.payments.data.models.EstimateDataModel;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.custom.logger.logging.Logger;
import com.custom.logger.manager.LogManager;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

/**
 * 
 * @author author1 Date : 01-Aug-2019 Ticket Model class
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
public class NotificationService implements INotificationService {

	public static final String ACCOUNT_SID = "AC723cdae599a356a2faa14579a8daf56a";
//	public static final String AUTH_TOKEN = "084cb696401bb91214ca16f276cc52fc";
	public static final String AUTH_TOKEN = "4967b5dbdb3610aabad472af68fe9840";
	public static final String TWILIO_NUMBER = "+1 251 333 7110";

//	public static final String ACCOUNT_SID = "AC723cdae599a356a2faa14579a8daf56a";
//	public static final String AUTH_TOKEN = "084cb696401bb91214ca16f276cc52fc";
//	public static final String TWILIO_NUMBER = "+16176741431";

	static final long ONE_MINUTE_IN_MILLIS = 120000;

	/** LOGGER **/
	private static Logger LOG = LogManager.getLogger(NotificationService.class);

	/** emailSenderService **/
	@Autowired
	private EmailSenderService emailSenderService;

	/** repository **/
	@Autowired
	private INotificationRepository iNotificationRepository;
	/** restTemplate **/
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * default constructor
	 */
	public NotificationService() {
		LOG.info("Ticket Service Constructor");
	}

	/**
	 * @method save
	 * @param notificationDataModel
	 * @return notificationDataModel
	 */

//	public CommunicationModel save(CommunicationModel notificationDataModel)
//			throws DataPersistenceOperationException, InvalidServiceRequestException, NotificationOperationException {
//		// TODO Auto-generated method stub
//
////		EstimateDataModel estimateDataModel = restTemplate.getForObject(
////				"http:services-4.finchtech.in//service-payments/payments/estimate/get?jobCode=" + notificationDataModel.getJobCode(),
////				EstimateDataModel.class);
//
////		EstimateDataModel estimateDataModel = restTemplate.getForObject(
////				"localhost:8096/payments/estimate/get?jobCode=" + notificationDataModel.getJobCode(),
////				EstimateDataModel.class);
//
//		HashMap<String, String> hashMapMessageProps = null;
//		HashMap<String, String> hashMapEmailProps = null;
//		hashMapMessageProps = new HashMap<>();
//		hashMapEmailProps = new HashMap<>();
//		NotificationSender notificationSender = new NotificationSender();
//
//		CommunicationEntity communicationEntity = new CommunicationEntity();
//
//		Random rnd = new Random();
//		int otpNumber = 100000 + rnd.nextInt(900000);
//		String number = "+91 " + notificationDataModel.getPhone();
//		String email = notificationDataModel.getEmail();
//		String message = "your one time otp " + otpNumber;
//		String otp = String.valueOf(otpNumber);
//
//		Calendar date = Calendar.getInstance();
//		long t = date.getTimeInMillis();
//		Date afterAddingTenMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
//		communicationEntity.setOtpExper(afterAddingTenMins);
//
//		communicationEntity.setStatus(notificationDataModel.getStatus());
//		communicationEntity.setPhone(notificationDataModel.getPhone());
//		communicationEntity.setJobCode(notificationDataModel.getJobCode());
//		communicationEntity.setEmail(notificationDataModel.getEmail());
//
//		if (notificationDataModel.getStatus().equals("open")) {
//			communicationEntity.setOtp(otp);
//			sendSMS(number, message);
//			// to generate mail object
//			hashMapEmailProps.put("TO", notificationDataModel.getEmail());
//			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AUSHA");
//			hashMapEmailProps.put("SUBJECT", "ayusha");
//			hashMapEmailProps.put("$OTP", otp);
//			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_ticketopened");
//
//		}
//
//		else if (notificationDataModel.getStatus().equals("invoice_generated")) {
//			communicationEntity.setOtp(otp);
//			sendSMS(number, "GREETINGS FROM AUSHA please send me otp " + otpNumber + " togenerate invoice_generated");
//			// email generate to customer
//			hashMapEmailProps.put("TO", notificationDataModel.getEmail());
//			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AUSHA");
//			hashMapEmailProps.put("$OTP", "please send me otp " + otpNumber + " togenerate invoice_generated");
//			hashMapEmailProps.put("SUBJECT", "ayusha");
//			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_invoicegenerated");
//
//		} else if (notificationDataModel.getStatus().equals("estimated")) {
//			communicationEntity.setOtp(otp);
////			sendSMS(number, "GREETINGS FROM AYUSHA please share the otp " + otpNumber + " to service  engineer "
////					+ estimateDataModel.getGrandTotal());
//
//			hashMapEmailProps.put("TO", notificationDataModel.getEmail());
//			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AUSHA ");
//			hashMapEmailProps.put("$OTP", "please send me otp " + otpNumber + " togenerate invoice_generated ");
//			// hashMapEmailProps.put("$ESTIMATE", " " + estimateDataModel.getGrandTotal());
//			// hashMapEmailProps.put("SUBJECT", "ayusha" +
//			// estimateDataModel.getGrandTotal());
//			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_estimateapproval");
//
//		} else if (notificationDataModel.getStatus().equals("customer_feedback")) {
//			sendSMS(number, "GREETINGS FROM AUSHA please share the otp " + otpNumber
//					+ " to generate service engineer feedback ");
//			communicationEntity.setOtp(otp);
//		} else if (notificationDataModel.getStatus().equals("service_eng")) {
//			sendSMS(number,
//					"GREETINGS FROM AUSHA please share the otp " + otpNumber + " to generate customer  feedback");
//		}
//		iNotificationRepository.save(communicationEntity);
//
//		return notificationDataModel;
//
//	}

	public CommunicationModel save(CommunicationModel notificationDataModel)
			throws DataPersistenceOperationException, InvalidServiceRequestException, NotificationOperationException {
// TODO Auto-generated method stub

		HashMap<String, String> hashMapMessageProps = null;
		HashMap<String, String> hashMapEmailProps = null;
		hashMapMessageProps = new HashMap<>();
		hashMapEmailProps = new HashMap<>();
		NotificationSender notificationSender = new NotificationSender();

		CommunicationEntity communicationEntity = new CommunicationEntity();

		Random rnd = new Random();
		int otpNumber = 100000 + rnd.nextInt(900000);
		String number = "+91 " + notificationDataModel.getPhone();
		String email = notificationDataModel.getEmail();
		String message = "your one time otp " + otpNumber;
		String otp = String.valueOf(otpNumber);

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date afterAddingTenMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
		communicationEntity.setOtpExpiry(afterAddingTenMins);

		communicationEntity.setStatus(notificationDataModel.getStatus());
		communicationEntity.setPhone(notificationDataModel.getPhone());
		communicationEntity.setJobCode(notificationDataModel.getJobCode());
		communicationEntity.setEmail(notificationDataModel.getEmail());

		if (notificationDataModel.getStatus().equals("open")) {
			communicationEntity.setOtp(otp);
			sendSMS(number, message);
// to generate mail object
			hashMapEmailProps.put("TO", notificationDataModel.getEmail());
			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AUSHA");
			hashMapEmailProps.put("SUBJECT", "ayusha");
			hashMapEmailProps.put("$OTP", otp);
			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_ticketopened");

		}

		else if (notificationDataModel.getStatus().equals("invoice_generated")) {
			communicationEntity.setOtp(otp);
			sendSMS(number, "GREETINGS FROM AUSHA please send me otp " + otpNumber + " togenerate invoice_generated");
// email generate to customer
			hashMapEmailProps.put("TO", notificationDataModel.getEmail());
			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AUSHA");
			hashMapEmailProps.put("$OTP", "please send me otp " + otpNumber + " togenerate invoice_generated");
			hashMapEmailProps.put("SUBJECT", "ayusha");
			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_invoicegenerated");

		} else if (notificationDataModel.getStatus().equalsIgnoreCase("estimated")) {
			RestTemplate restTemplate = new RestTemplate();
			EstimateDataModel estimateDataModel = restTemplate.getForObject(
					"http://localhost:8096/payments/estimate/get?jobCode=" + notificationDataModel.getJobCode(),
					EstimateDataModel.class);

			communicationEntity.setOtp(otp);
			sendSMS(number, "GREETINGS FROM AYUSHA please share the otp " + otpNumber + " to service  engineer "
					+ estimateDataModel.getGrandTotal());

			hashMapEmailProps.put("TO", notificationDataModel.getEmail());
			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AUSHA ");
			hashMapEmailProps.put("$OTP", "please send me otp " + otpNumber + " togenerate invoice_generated ");
			hashMapEmailProps.put("$ESTIMATE", " " + estimateDataModel.getGrandTotal());
			hashMapEmailProps.put("SUBJECT", "ayusha" + estimateDataModel.getGrandTotal());
			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_estimateapproval");

		} else if (notificationDataModel.getStatus().equals("customer_feedback")) {
			sendSMS(number, "GREETINGS FROM AUSHA please share the otp " + otpNumber
					+ " to generate service engineer feedback ");
			communicationEntity.setOtp(otp);
		} else if (notificationDataModel.getStatus().equals("service_eng")) {
			sendSMS(number,
					"GREETINGS FROM AUSHA please share the otp " + otpNumber + " to generate customer  feedback");
		}
		iNotificationRepository.save(communicationEntity);

		return notificationDataModel;

	}

	public void sendSMS(String tonumber, String message) {

		LOG.info(tonumber);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		PhoneNumber to = new PhoneNumber(tonumber);
		PhoneNumber from = new PhoneNumber(TWILIO_NUMBER);

		MessageCreator creater = Message.creator(to, from, message);
		creater.create();
	}

	@Override
	public CommunicationModel validationOtp(String jobCode, int otp) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		// CommunicationEntity a=iNotificationRepository.findValidation(jobCode, otp);
//		CommunicationModel communicationModel=new CommunicationModel();
//		communicationModel.setId(a.getId());
//		communicationModel.setPhone(a.getPhone());
//		communicationModel.setJobCode(a.getJobCode());
//		communicationModel.setStatus(a.getStatus());
//		communicationModel.setOtp(a.getOtp());
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @method validationOtp
	 * @param jobCode
	 * @param otp
	 * @return response
	 */

	public Response validationOtp(String otp, long id) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {
		Response response = CommonUtils.getResponseObject("");

		Calendar date = Calendar.getInstance();
		long otpExperDate = date.getTimeInMillis();
		Date afterAddingTenMins = new Date(otpExperDate);
		BCryptPasswordEncoder decodeOtp = new BCryptPasswordEncoder();

		boolean communicationEntity1 = checkotpvalidate(otp);

		// CommunicationEntity communicationEntity =
		// iNotificationRepository.findValidation(jobCode, otp);
		CommunicationEntity c = iNotificationRepository.findByOtpId(id);

		System.out.println("response body" + c.getCustomerName());

		if (afterAddingTenMins.before(c.getOtpExpiry()) || afterAddingTenMins.equals(c.getOtpExpiry())) {
			if (decodeOtp.matches(otp, c.getOtp())) {
				response.setStatusCode("200");
				response.setStatus("200");
				response.setMessage("otp validated successfully...");
			} else {
				response.setStatusCode("404");
				response.setStatus("404");
				response.setMessage("otp invalid");
			}

		} else {
			response.setStatus("400");
			response.setStatusCode("400");
			response.setErrors(400);
			response.setMessage("otp expire");

		}
		return response;
	}

	/**
	 * @method checkPhoneNumberExists
	 * @check this method otp insert or not if otp is insert already deleted the otp
	 *        and insert new otp
	 * @return false
	 */
	public boolean checkPhoneNumberExists(String phone) {
		CommunicationEntity communicationEntity = null;
		communicationEntity = iNotificationRepository.findByPhone(phone);
		if (communicationEntity == null) {
			return false;
		} else {
			iNotificationRepository.delete(communicationEntity);
			return false;
		}

	}

	/**
	 * @param checkotpvalidate
	 * @param otp
	 * @return true/false
	 * @check checkotpvalidate
	 */
//	public boolean checkotpvalidate(String otp) {
//		CommunicationEntity communicationEntity = null;
//		communicationEntity = iNotificationRepository.findByOtp(otp);
//		if (communicationEntity == null) {
//			return false;
//		} else {
//			return true;
//		}
//	}

	// Demo

	public boolean checkotpvalidate(String otp) {
		CommunicationEntity communicationEntity = null;
		communicationEntity = iNotificationRepository.findByOtp(otp);
		if (communicationEntity == null) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("null")
	public CommunicationModel sendOtp(CommunicationModel communicationModel) throws DataPersistenceOperationException,
			InvalidServiceRequestException, ResourceNotFoundException, Exception {

		CommunicationEntity response = null;
		// TODO Auto-generated method stub

		HashMap<String, String> hashMapMessageProps = null;
		HashMap<String, String> hashMapEmailProps = null;
		hashMapMessageProps = new HashMap<>();
		hashMapEmailProps = new HashMap<>();
		NotificationSender notificationSender = new NotificationSender();
		// expire date
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date afterAddingTenMins = new Date(t + (1 * ONE_MINUTE_IN_MILLIS));
		Date lastUpdate = new Date();
		// random otp
		Random rnd = new Random();
		int otpNumber = 100000 + rnd.nextInt(900000);
		String number = "+91 " + communicationModel.getPhone();
		String email = communicationModel.getEmail();
		String message = "your one time otp " + otpNumber;
		String otp = String.valueOf(otpNumber);
		CommunicationEntity communicationEntity = new CommunicationEntity();
		CommunicationModel res = new CommunicationModel();

		communicationEntity.setCustomerName(communicationModel.getCustomerName());
		communicationEntity.setEmail(communicationModel.getEmail());
		communicationEntity.setJobCode(communicationModel.getJobCode());
		communicationEntity.setOtpExpiry(afterAddingTenMins);
		communicationEntity.setPhone(communicationModel.getPhone());
		communicationEntity.setStatus(communicationModel.getStatus());
		communicationEntity.setLastUpdate(lastUpdate);

		BCryptPasswordEncoder encodeOtp = new BCryptPasswordEncoder();

		switch (communicationModel.getStatus()) {
		case "Customer Avail":
			String otpNum = String.valueOf(otpNumber);
			communicationEntity.setOtp(encodeOtp.encode(otp));
			sendSMS(number, message);
			hashMapEmailProps.put("TO", communicationModel.getEmail());
			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AYUSHYA");
			hashMapEmailProps.put("SUBJECT", "ayushya");
			hashMapEmailProps.put("$OTP", otp);
			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_ticketopened");
			response = iNotificationRepository.save(communicationEntity);
			res.setId(response.getId());
			res.setStatus(response.getStatus());
			break;
		case "Estimated":
			RestTemplate restTemplate = new RestTemplate();
			EstimateDataModel estimateDataModel = restTemplate.getForObject(
					ConstantPropertise.GET_ESTIMATE_BY_JOBCODE + communicationModel.getJobCode(),
					EstimateDataModel.class);

			communicationEntity.setOtp(encodeOtp.encode(otp));
			sendSMS(number, "GREETINGS FROM AYUSHYA please share the otp " + otpNumber + " to service  engineer "
					+ estimateDataModel.getGrandTotal());

			hashMapEmailProps.put("TO", communicationModel.getEmail());
			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AYUSHYA ");
			hashMapEmailProps.put("$OTP", "please send me otp " + otpNumber + " togenerate invoice_generated ");
			hashMapEmailProps.put("$ESTIMATE", " " + estimateDataModel.getGrandTotal());
			hashMapEmailProps.put("SUBJECT", "ayushya" + estimateDataModel.getGrandTotal());
			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_estimateapproval");
			response = iNotificationRepository.save(communicationEntity);
			res.setId(response.getId());
			res.setStatus(response.getStatus());
			break;
		case "Customer FeedBack":
			communicationEntity.setOtp(encodeOtp.encode(otp));
			sendSMS(number, "GREETINGS FROM AYUSHYA please share the otp " + otpNumber
					+ " to generate service engineer feedback ");
			response = iNotificationRepository.save(communicationEntity);
			res.setId(response.getId());
			res.setStatus(response.getStatus());
			break;
		case "Invoice generated":
			communicationEntity.setOtp(encodeOtp.encode(otp));
			sendSMS(number, "GREETINGS FROM AYUSHYA please send me otp " + otpNumber + " togenerate invoice_generated");
			hashMapEmailProps.put("TO", communicationModel.getEmail());
			hashMapEmailProps.put("$serviceRequest", "GREETINGS FROM AYUSHYA");
			hashMapEmailProps.put("$OTP", "please send me otp " + otpNumber + " togenerate invoice_generated");
			hashMapEmailProps.put("SUBJECT", "ayushya");
			notificationSender.notify(hashMapEmailProps, "email", "emailTicketLoggedFormatter_invoicegenerated");
			res.setId(0);
			res.setStatus("Invoice generated");
//			response.setId(0);
//			response.setStatus("Invoce generated");
			break;
		case "Assign customer":
			sendSMS(number, "A service request has been created and customer service agent "
					+ communicationModel.getCustomerName() + " Contact " + communicationModel.getPhone()
					+ " at your location on " + communicationModel.getOtpExpiry() + communicationModel.getLastUpdate());
//			response.setId(0);
//			response.setStatus("Assign customer");
			res.setId(0);
			res.setStatus("Assign customer");

			break;

		case "Reschedule":
			sendSMS(number, "GREETINGS FROM AYUSHYA  " + "A service request has been Raised for Reschedule ");

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo("cshiva@gmail.com");
			mailMessage.setSubject("GREETINGS FROM AYUSHYA");
			mailMessage.setFrom("cshiva@gmail.com");
			mailMessage.setText("A service request has been Raised for Reschedule ");
			emailSenderService.sendEmail(mailMessage);
			// response.setId(0);
//			response.setStatus("Reschedule");
			res.setId(0);
			res.setStatus("Reschedule");
			break;

		case "Return Parts":
			sendSMS(number, "serviced parts has been returned from " + communicationModel.getCustomerName() + " JobId "
					+ communicationModel.getJobCode());
			SimpleMailMessage mailMessage2 = new SimpleMailMessage();
			mailMessage2.setTo("cshiva@gmail.com");
			mailMessage2.setSubject("GREETINGS FROM AYUSHYA");
			mailMessage2.setFrom("cshiva@gmail.com");
			mailMessage2.setText("serviced parts has been returned from " + communicationModel.getCustomerName()
					+ " JobId " + communicationModel.getJobCode());
			emailSenderService.sendEmail(mailMessage2);
//			response.setId(0);
//			response.setStatus("Return Parts");
			res.setId(0);
			res.setStatus("Return Parts");
			break;
		default:
			break;
		}
		return res;
	}
}
