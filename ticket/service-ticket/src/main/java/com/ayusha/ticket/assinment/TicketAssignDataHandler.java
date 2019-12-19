package com.ayusha.ticket.assinment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.client.RestTemplate;

import com.ayusha.http.client.ServicesClient;
import com.ayusha.http.client.models.ServiceResponse;
import com.ayusha.http.constants.HTTPConstants;
import com.ayusha.json.utils.JSONConverter;
import com.ayusha.services.common.exceptions.DataPersistenceOperationException;
import com.ayusha.services.common.exceptions.InvalidServiceRequestException;
import com.ayusha.services.common.exceptions.ResourceNotFoundException;
import com.ayusha.services.common.exceptions.ServicesDataException;
import com.ayusha.ticket.data.models.OtpDataModel;
import com.ayusha.ticket.data.models.TicketDataModel;
import com.ayusha.ticket.services.constants.TicketServiceConstants;
import com.google.protobuf.TextFormat.ParseException;

/**
 * 
 * @author Finch Date :01-Aug-2019 Ticket Service Methods
 *
 */
public class TicketAssignDataHandler {

	/** ticketDataModel **/
	private TicketDataModel ticketDataModel = null;

	/** servicesClient **/
	private ServicesClient servicesClient = null;

	/**
	 * default constructor
	 */
	public TicketAssignDataHandler() {
		servicesClient = new ServicesClient();
		servicesClient.setBaseURL(ServicesClient.JOB_BASE_URL);
	}

	/**
	 * @method assignTicket
	 * @param ticketDataModel
	 * @return TicketDataModel
	 * 
	 */
	public TicketDataModel assignTicket(TicketDataModel ticketDataModel) throws ServicesDataException, ParseException {
		ServiceResponse serviceResponse = null;
		ServicesDataException servicesDataException = null;
		String tktService = TicketServiceConstants.ASSIGN_TICKET_DETAILS_SERVICE_NAME;
		serviceResponse = servicesClient.execute(tktService, JSONConverter.convertPOJOToString(ticketDataModel),
				HTTPConstants.POST);
		RestTemplate restTemplate = new RestTemplate();
		OtpDataModel otpDataModel = new OtpDataModel();
		if (serviceResponse.getResponseCode().equalsIgnoreCase(HTTPConstants.HTTP_OK)) {
			String pattern = "hh:mm:ss a";
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			String dt = null;

//			Date date;
//			try {
//				date = dateFormat.parse(ticketDataModel.getVisitTime());
//				dt = dateFormat.format(date);
//			} catch (java.text.ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			// Date date=ticketDataModel.getVisitDate()+ticketDataModel.getVisitTime();
			otpDataModel.setStatus("Assign customer");
			otpDataModel.setPhone(ticketDataModel.getCustomerDataModel().getContactNumber());
			otpDataModel.setJobCode("");
			otpDataModel.setPhone(ticketDataModel.getCustomerDataModel().getContactNumber());
			otpDataModel.setEmail(ticketDataModel.getCustomerDataModel().getEmail());
			otpDataModel.setCustomerName(ticketDataModel.getCustomerDataModel().getCustomerName());
			// otpDataModel.setOtpExper(ticketDataModel.getVisitDate());
			otpDataModel.setLastUpdate(ticketDataModel.getVisitTime());
			/*
			 * String str="{"+"\"status\" :\"Assign customer\"," +
			 * "\"phone\" :"+ticketDataModel.getCustomerDataModel().getContactNumber()+","+
			 * "\"customerName\":"+ticketDataModel.getCustomerDataModel().getCustomerName()
			 * +"}";
			 */
			restTemplate.postForEntity("http://134.209.147.111:8094/communication/notification/sendOtp", otpDataModel,
					OtpDataModel.class);
		}

		try {
			return ((TicketDataModel) JSONConverter.convertStringToPOJO("" + serviceResponse.getResponse(),
					TicketDataModel.class));
		} catch (Exception e) {
			servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		}
	}

	public TicketDataModel jobAssignTicket(TicketDataModel ticketDataModel) throws DataPersistenceOperationException,
			Exception, InvalidServiceRequestException, ResourceNotFoundException {
		ServiceResponse serviceResponse = null;
		ServicesDataException servicesDataException = null;
		System.out.println("in outerrrrrrrrrrrr for loppppppppppppppppppppppp");

		String tktService = TicketServiceConstants.UPDATE_ASSIGN;
		System.out.println("======================== in outer method");
		serviceResponse = servicesClient.execute(tktService, JSONConverter.convertPOJOToString(ticketDataModel),
				HTTPConstants.POST);
		System.out.println("------------------------------------- job assign services");

		try {
			return ((TicketDataModel) JSONConverter.convertStringToPOJO("" + serviceResponse.getResponse(),
					TicketDataModel.class));
		} catch (Exception e) {
			servicesDataException = new ServicesDataException();
			servicesDataException.setErrorCode(HTTPConstants.INTERNAL_ERROR);
			servicesDataException.setErrorMessage(HTTPConstants.HTTP_INTERNAL_ERROR_MESSAGE);
			throw servicesDataException;
		}
	}
}
